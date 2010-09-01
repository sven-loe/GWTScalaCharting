/*This file is part of GWTScalaCharting */

package gwt.test.entities.jpa

import javax.persistence.Entity;
import scala.reflect.BeanProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
 
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
