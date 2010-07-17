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

package gwt.test.testing

import org.scalatest.junit.JUnitSuite
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.mutable.ListBuffer
import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import javax.persistence.Persistence
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager
import javax.jdo.JDOEnhancer
import javax.jdo.JDOHelper
import gwt.test.entities._
import gwt.test.components.JpaUtil
import java.util.Date
import org.apache.log4j._
import gwt.test.components._
import gwt.test.services._
 

class EntityTest {
  val puName = "jpa"
//  val entityManagerFactory = Persistence.createEntityManagerFactory(puName)
  val logger = Logger.getRootLogger
  val context = new ComponentContext(puName, true)
  
  val dbObject = DbObject
  
  @Before def initialize() : Unit = {
    println("init")
//    val layout = new SimpleLayout();
//    val consoleAppender = new ConsoleAppender( layout );
//    logger.addAppender( consoleAppender );

    val enhancer = JDOHelper.getEnhancer()
	enhancer.setVerbose(true);
	enhancer.addPersistenceUnit(puName);
	enhancer.enhance();
  }
 
  @Test def symbolImporterTest() : Unit = {
    val symbols = dbObject.factory.symbolImporter.importSymbols
    dbObject.factory.symbolImporter.storeSymbols(symbols)
    println("SymbolImporter Test done")
  }
  
//  @Test def insertTest() {	
//    val entityManager = entityManagerFactory.createEntityManager()
//    transaction(entityManager, entityManager => {
//    	val stockQuote = new StockQuote
//    	stockQuote.adjLast = 1L
//    	stockQuote.currency = "USD"
//    	stockQuote.dayHigh = 3L
//    	stockQuote.dayLow = 2L
//    	stockQuote.last = 2L
//    	stockQuote.time = new Date()
//    	stockQuote.volume = 10L
//    	entityManager.persist(stockQuote)
//    })
//    println("done") 
//  }
 
//  @Test def stockImporterTest() {    
//	val stockQuotes = dbObject.factory.stockImporter.importStockHistory("ge")
//	dbObject.factory.stockImporter.storeStockHistory(stockQuotes)
//	println("StockImporter Test done")
//  }
     
  
  
}
