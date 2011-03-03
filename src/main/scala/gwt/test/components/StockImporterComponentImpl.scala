/*This file is part of GWTScalaCharting

GWTScalaCharting is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.

GWTScalaCharting is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>. */

package gwt.test.components

import java.net.URL;
import java.util.Calendar;
import javax.persistence.EntityManager
import scala.io.Source;
import gwt.test.entities.jpa._
import gwt.test.annotations.Logging
import gwt.test.annotations.LogLevel
import gwt.test.services.ComponentContext
import gwt.test.persistence.SQuery

trait StockImporterComponentImpl extends StockImporterComponent {
	 
	@Logging  
	class StockImporterImpl(val context: ComponentContext) extends StockImporter with JpaUtil { 	
 
	override def storeStockHistory(quotes: List[StockQuote]) : Long = {
	  val em = context.getEntityManager
	  var quoteCount = 0L
	  if(!quotes.isEmpty) {
	    var symbol = quotes.head.symbol
//	    transaction(em,em => {
//	    	val oldStockQuotes = em.createQuery("select sq from StockQuote sq where sq.symbol.symbol=:symbol").setParameter("symbol",symbol.symbol).getResultList()
//	    	val oldQuotes = List.fromArray(oldStockQuotes.toArray)
//	    	oldQuotes.foreach(quote => em.remove(quote))
            var tx = em.getTransaction
            try{
            	tx.begin
            	val sQuery = new SQuery(em.createQuery("select sym from Symbol sym where sym.symbol=:symbol").setParameter("symbol", symbol.symbol))
            	symbol = sQuery.getSingleResult[Symbol]            	
            	tx.commit
            } catch {
              case ex: Exception => {
                println("No Symbol " + symbol.symbol + " found.");
                tx.rollback                
              }
            }
            if(symbol.id == null) {
	            tx = em.getTransaction
	            try{
		            tx.begin
		            em.persist(symbol)
		            tx.commit
	            } catch {
	              case ex: Exception => {
	                println("Persist symbol failed.");
	                tx.rollback
	                throw ex
	              }
	            }
            } 
            println("Number of Quotes: "+quotes.length)
            tx = em.getTransaction
            try{
	            tx.begin
	            quotes.foreach(quote => {
	              quote.symbol = symbol
	              em.persist(quote); 	              
	              quoteCount +=1;
	              if(quoteCount % 1000 == 0) {
	                tx.commit
	                println("Number "+quoteCount);
	                tx = em.getTransaction
	                tx.begin                
	              }
	            })
	            tx.commit
            } catch {
              case ex: Exception => {
                println("Persist stockquotes failed.");
                tx.rollback
                throw ex
              }
            }
//	    })
	  }
	  em.close
	  println("QuoteCount: "+quoteCount+" after commit.")
	  return quoteCount
	}
 
//	override def storeStockHistory(quotes: List[StockQuote]) : Long = {	  
//	  var sym: Symbol = null;
//	  val em = context.getEntityManager()
//	  if(!quotes.isEmpty) {
//	    sym = quotes.head.symbol;
//	    transaction(em, em => em.createQuery("delete from Symbol s where s.symbol = :symbol").setParameter("symbol", sym.symbol).executeUpdate);
//	    sym = quotes.head.symbol;
//	    transaction(em, em => em.persist(sym));	    
//	  }
//	  transaction(em, em => {
//        val oldQuotes = em.createQuery("select count(sq) from StockQuote sq, Symbol s where sq.symbol = s and s.symbol = :symbol").setParameter("symbol", sym.symbol).getSingleResult.asInstanceOf[Long];
//	    if(oldQuotes > 0) {
//	      val updates = em.createQuery("delete from StockQuote sq where sq.symbol = :symbol").setParameter("symbol", sym).executeUpdate;
//	    }
//      });
//	  var quoteCount: Long = 0;
//	  transaction(em, em => {	  
//	    quotes.foreach(quote => {em.persist(quote); quoteCount += 1;});
//	  });
//	  em.close;
//	  return quoteCount;
//	}
 
	override def updateStockHistory(quotes: List[StockQuote]) : Long = {	  
	  var newest: Calendar = null; 
	  var quoteCount: Long = 0;
	  val em = context.getEntityManager()
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
	    val myQuotes = quotes.filter(quote => quote.time.after(newest.getTime));
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
