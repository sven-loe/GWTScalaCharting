package gwt.test.entities

import javax.persistence.Entity;
import scala.reflect.BeanProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@serializable
class Symbol {	
	@Id @GeneratedValue
	@BeanProperty
	var id: Long  = _
	@BeanProperty
	var symbol: String = _
	@BeanProperty
	var name: String = _
	@BeanProperty
	var value: Long = _
 	@BeanProperty 	 	
 	@OneToMany{val cascade=Array(CascadeType.REMOVE), val mappedBy="symbol", val targetEntity=classOf[StockQuote]}
 	var stockQuotes: java.util.Collection[StockQuote] = new java.util.ArrayList[StockQuote];
} 
