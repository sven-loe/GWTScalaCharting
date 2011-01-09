package gwt.test.entities.mongo

import com.osinka.mongodb._
import com.osinka.mongodb.shape._
import java.util.Date
import com.mongodb._
import org.bson.types._

case class StockQuote extends BasicDBObject with MongoObject {	
	var currency: String = _ 
	var time: Date = _ 
	var last: Long = _
	var dayHigh: Long =_ 
	var dayLow: Long = _ 
	var volume: Long = _ 
	var adjLast: Long = _ 
	var dayOfYear: Long = _ 	
	var symOID: ObjectId = _
}
		 

object StockQuote extends MongoObjectShape[StockQuote] with FunctionalShape[StockQuote] {
	lazy val currency = Field.scalar("currency", _.currency, (x: StockQuote, v: String) => x.currency= v)
	lazy val time = Field.scalar("date", _.time, (x: StockQuote, v: Date) => x.time = v)
	lazy val last = Field.scalar("last", _.last, (x: StockQuote, v: Long) => x.last = v)
	lazy val dayHigh = Field.scalar("dayHigh", _.dayHigh, (x: StockQuote, v: Long) => x.dayHigh = v)
	lazy val dayLow = Field.scalar("dayLow", _.dayLow, (x: StockQuote, v: Long) => x.dayLow = v)
	lazy val volume = Field.scalar("volume", _.volume, (x: StockQuote, v: Long) => x.volume = v)
	lazy val adjLast = Field.scalar("adjLast", _.adjLast, (x: StockQuote, v: Long) => x.adjLast = v)
	lazy val dayOfYear = Field.scalar("dayOfYear", _.dayOfYear, (x: StockQuote, v: Long) => x.dayOfYear = v)
//	lazy val symbol = Field.scalar("symbol", _.symbol, (x: StockQuote, v: DBRef) => x.symbol = v)
	lazy val symOID = Field.scalar("symOID", _.symOID, (x: StockQuote, v: ObjectId) => x.symOID = v)
	//object symbol extends EmbeddedField[Symbol]("symbol", _.symbol, Some((x: StockQuote, v: Symbol) => x.symbol = v)) with SymbolIn[StockQuote]

	
	override lazy val * = List(currency, time, last, dayHigh, dayLow, volume, adjLast, dayOfYear, symOID)
	override def factory(dbo: DBObject) = Some(new StockQuote)	

}