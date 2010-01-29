package gwt.test.components

import java.net.URL;
import java.util.Calendar;
import javax.persistence.EntityManager
import scala.io.Source;
import gwt.test.entities._
import gwt.test.annotations.Logging
import gwt.test.annotations.LogLevel

trait StockImporterComponentImpl extends StockImporterComponent {
	
	@Logging  
	class StockImporterImpl(val em: EntityManager) extends StockImporter with JpaUtil {  	 	  	  	  
  
	override def importStockHistory(symbol: String) : List[StockQuote] =  {	  
	  var quotes =  List[StockQuote]();
	  val cal = Calendar.getInstance;
	  val date = cal.get(Calendar.DAY_OF_MONTH).toString :: cal.get(Calendar.MONTH).toString :: cal.get(Calendar.YEAR).toString :: Nil;
	  var urlStr = "http://ichart.finance.yahoo.com/table.csv?s=%1$s&a=00&b=1&c=1960&d=%2$s&e=%3$s&f=%4$s&g=d&ignore=.csv";
	  urlStr = String.format(urlStr, symbol, date(0), date(1), date(2));
	  val myUrl = new URL(urlStr);	  
	  val source = Source.fromURL(myUrl);
	  val sym = new Symbol;	  
	  sym.symbol = symbol;   
	  source.getLines.foreach(line => {
		  if(line.indexOf("Open") == -1) {
			  val data = line.split(",");		  
			  val quote = new StockQuote;
			  quote.currency = "USD";
			  quote.dayHigh = java.lang.Long.parseLong(data(2).replace(".","").trim);
			  quote.dayLow = java.lang.Long.parseLong(data(3).replace(".","").trim);
			  quote.last = java.lang.Long.parseLong(data(4).replace(".","").trim);
			  quote.volume = java.lang.Long.parseLong(data(5).replace(".","").trim);
			  quote.adjLast = java.lang.Long.parseLong(data(6).replace(".","").trim);
			  val calData = data(0).split("-");
			  val cal = Calendar.getInstance;
			  cal.set(java.lang.Integer.parseInt(calData(0).trim), java.lang.Integer.parseInt(calData(1).trim)-1, java.lang.Integer.parseInt(calData(2).trim));
			  quote.time = cal;
			  quote.symbol = sym;			  
			  quotes = quote :: quotes;
		  }
	  })	  
	  return quotes;
	} 
 
	override def storeStockHistory(quotes: List[StockQuote]) : Long = {	  
	  var sym: Symbol = null;
	  if(!quotes.isEmpty) {
	    sym = quotes.head.symbol;
	    transaction(em, em => em.createQuery("delete from Symbol s where s.symbol = :symbol").setParameter("symbol", sym.symbol).executeUpdate);
	    sym = quotes.head.symbol;
	    transaction(em, em => em.persist(sym));	    
	  }
	  transaction(em, em => {
        val oldQuotes = em.createQuery("select count(sq) from StockQuote sq, Symbol s where sq.symbol = s and s.symbol = :symbol").setParameter("symbol", sym.symbol).getSingleResult.asInstanceOf[Long];
	    if(oldQuotes > 0) {
	      val updates = em.createQuery("delete from StockQuote sq where sq.symbol = :symbol").setParameter("symbol", sym).executeUpdate;
	    }
      });
	  var quoteCount: Long = 0;
	  transaction(em, em => {	  
	    quotes.foreach(quote => {em.persist(quote); quoteCount += 1;});
	  });
	  em.close;
	  return quoteCount;
	}
 
	override def updateStockHistory(quotes: List[StockQuote]) : Long = {	  
	  var newest: Calendar = null; 
	  var quoteCount: Long = 0;
	  if(!quotes.isEmpty) {
	    var sym = quotes.head.symbol;
	    transaction(em, em => {newest = em.createQuery("select max(sq.time) from StockQuote sq, Symbol s where sq.symbol = s and s.symbol = :symbol").setParameter("symbol",sym.symbol).getSingleResult.asInstanceOf[Calendar];	     
	        sym = em.createQuery("select s from Symbol s where s.symbol = :symbol").setParameter("symbol",sym.symbol).getSingleResult.asInstanceOf[Symbol];
        });	  
	    if(newest != null) { 
	      newest.add(Calendar.HOUR_OF_DAY,+1);
	    } else {
	    	newest = Calendar.getInstance;
	    	newest.set(Calendar.YEAR,1900);
	    }
	    val myQuotes = quotes.filter(quote => quote.time.after(newest));
	    if(sym == null) 
	    	sym = quotes.head.symbol;
	    myQuotes.foreach(quote => quote.symbol = sym);
		transaction(em, em => {	  
		  myQuotes.foreach(quote => {em.persist(quote); quoteCount += 1;});
		});
	  }
	  em.close;
	  return quoteCount;	  
	}	
	
}
  
}