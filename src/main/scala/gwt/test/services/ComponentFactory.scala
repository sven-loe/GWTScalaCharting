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

package gwt.test.services

import gwt.test.components._

class ComponentFactory(val context: ComponentContext) extends StockImporterComponentImpl with StockDBServiceComponentImpl with SymbolImporterComponentImpl {
  
  val stockImporter = ManagedComponentFactory.createComponent[StockImporter](  
			classOf[StockImporter],  
			new ManagedComponentProxy(new StockImporterImpl(context),context)  
			with LoggingInterceptor
			with TransactionInterceptor)
 
  val stockDBService = ManagedComponentFactory.createComponent[StockDBService](  
			classOf[StockDBService],  
			new ManagedComponentProxy(new StockDBServiceImpl(context),context)  
			with LoggingInterceptor
			with TransactionInterceptor)
  
  val symbolImporter = ManagedComponentFactory.createComponent[SymbolImporter](  
			classOf[SymbolImporter],  
			new ManagedComponentProxy(new SymbolImporterImpl(context),context)  
			with LoggingInterceptor
			with TransactionInterceptor)
 
}