package gwt.test.entities.mongo

import com.osinka.mongodb.shape._
import com.osinka.mongodb._
import com.mongodb._
import org.bson.types._

case class Symbol() extends BasicDBObject with MongoObject {	
	var symbol: String = _ 
	var name: String = _ 
	var value: Long = _
}

object Symbol extends MongoObjectShape[Symbol] with FunctionalShape[Symbol] {
	lazy val name = Field.scalar("name", _.name, (x: Symbol, v: String) => x.name = v)
    lazy val symbol = Field.scalar("symbol", _.symbol, (x: Symbol, v: String) => x.symbol = v)
    lazy val value = Field.scalar("value", _.value, (x: Symbol, v: Long) => x.value = v)
    override lazy val * = List(name, symbol, value)
    override def factory(dbo: DBObject) = Some(new Symbol)
//    override def factory(dbo: DBObject) = for{name(n) <- Some(dbo)     	 
//    	symbol(s) <- Some(dbo) 
//    	value(v) <- Some(dbo)} yield new Symbol(s, n, v)
}