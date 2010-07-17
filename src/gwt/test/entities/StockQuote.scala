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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import scala.reflect.BeanProperty
import java.util.Date
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
 
@Entity
@serializable
@SequenceGenerator(name="StockQuoteSeq", sequenceName="SQSequence")
class StockQuote { 
  
  @BeanProperty 
  @Id @GeneratedValue(generator="StockQuoteSeq")  
  var id: Long = _
  @BeanProperty
  var currency: String = _
  @BeanProperty
  var time: Date = _
  @BeanProperty
  var last: Long = _
  @BeanProperty
  var dayHigh: Long = _
  @BeanProperty
  var dayLow: Long = _
  @BeanProperty
  var volume: Long = _
  @BeanProperty
  var adjLast: Long = _
  @BeanProperty
  var dayOfYear: Long = _    
  @BeanProperty
  @ManyToOne 
  @JoinColumn(name="symbol_id")
  var symbol: Symbol = _
  
}
