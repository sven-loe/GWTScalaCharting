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

import gwt.test.entities.jpa._
import scala.io.Source
import java.util.Calendar
import java.net.URL
  
trait StockImporterComponent {

  val stockImporter: StockImporter
  
  trait StockImporter {
    
    def importStockHistory(symbol: String) : List[StockQuote] =  {	  
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
			  quote.time = cal.getTime;
			  quote.dayOfYear = cal.get(Calendar.DAY_OF_YEAR)
			  quote.symbol = sym;			  
			  quotes = quote :: quotes;
		  }
	  })	  
	  return quotes;
	}

	def storeStockHistory(quotes: List[StockQuote]) : Long
 
	def updateStockHistory(quotes: List[StockQuote]) : Long
  }
   
}
