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

import gwt.test.entities._

trait StockImporterComponent {

  val stockImporter: StockImporter
  
  trait StockImporter {
    def importStockHistory(symbol: String) : List[StockQuote]
 
	def storeStockHistory(quotes: List[StockQuote]) : Long
 
	def updateStockHistory(quotes: List[StockQuote]) : Long
  }
   
}
