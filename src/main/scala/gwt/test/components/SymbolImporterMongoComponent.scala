package gwt.test.components

import gwt.test.annotations._
import gwt.test.services.ComponentContext
import gwt.test.entities.mongo._


trait SymbolImporterMongoComponent extends SymbolImporterComponent {

  @MongoTransaction
  @Logging
  class SymbolImporterImpl(val context: ComponentContext) extends SymbolImporter with ObjectConverter {
		
	  override def storeSymbols(symbols: List[gwt.test.entities.jpa.Symbol]) : Long = {
		var number = 0
		val symCol = context.getSymCollection
		symbols.foreach(sym => {
			val symbolQuery = Symbol where (Symbol.symbol is sym.symbol)
			val symbolResult = symbolQuery in symCol
			if(!symbolResult.isEmpty) {
				val mySym = symbolResult.head
				mySym.symbol = sym.symbol
				mySym.name = sym.name 
				mySym.value = sym.value 
			} else {
				val mongoSym = getMongoSymbol(sym)
				symCol += mongoSym
			}
			number += 1
		})		
		number
	  }
	}
}