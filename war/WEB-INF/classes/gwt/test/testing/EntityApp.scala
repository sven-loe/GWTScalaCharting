package gwt.test.testing

import gwt.test.components._
import gwt.test.services._
import gwt.test.entities._

object EntityApp {
  
  val dbObject = DbObject
  
  def main(args : Array[String]) : Unit = {
    val symbols = dbObject.factory.symbolImporter.importSymbols
    dbObject.factory.symbolImporter.storeSymbols(symbols)
    println("SymbolImporter Test done")
  }
}
