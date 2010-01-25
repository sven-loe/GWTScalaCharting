package gwt.test.entities

import javax.persistence.Entity;
import scala.reflect.BeanProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
 
@Entity
@serializable
class StockQuote {
  
  @BeanProperty
  @Id @GeneratedValue  
  var id: Long = _
  @BeanProperty
  var currency: String = _
  @BeanProperty
  var time: Calendar = _
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
  @ManyToOne
  @JoinColumn{val name="symbol_id"}
  var symbol: Symbol = _
}
