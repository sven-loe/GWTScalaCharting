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
import gwt.test.annotations.Logging
import gwt.test.annotations.Transaction
 
trait StockDBServiceComponent {
	val stockDBService: StockDBService
 	
	trait StockDBService {	  
	  def getCurrentStockQuote(symbol: String) : gwt.test.client.StockQuote
   
	  def getStockHistory(symbol: String) : List[gwt.test.client.StockQuote]
   
	  def getSymbols() : java.util.List[gwt.test.client.Symbol]
	}
}
