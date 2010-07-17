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

package gwt.test.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany
import javax.persistence.Id
import javax.persistence.GeneratedValue

import scala.reflect.BeanProperty;
 
@Entity
@serializable
@SequenceGenerator(name="SymbolSeq", sequenceName="SymSequence")
class Symbol { 
 
	@Id @GeneratedValue(generator="SymbolSeq")
	@BeanProperty
	var id: Long  = _
	@BeanProperty
	var symbol: String = _
	@BeanProperty
	var name: String = _
	@BeanProperty
	var value: Long = _
 	@BeanProperty 	 	
 	@OneToMany(cascade=Array(CascadeType.REMOVE), mappedBy="symbol", targetEntity=classOf[StockQuote])
 	var stockQuotes: java.util.Collection[StockQuote] = new java.util.ArrayList[StockQuote];
 	
}
