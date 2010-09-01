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

package gwt.test.services

import javax.persistence.Persistence
import javax.persistence.FlushModeType
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager
import javax.jdo.JDOEnhancer
import javax.jdo.JDOHelper
import com.mongodb._
import gwt.test.entities.mongo._
import com.osinka.mongodb._
import com.osinka.mongodb.shape._
 
class ComponentContext() {	
	private val threadLocal = new ThreadLocal[EntityManager]
	private var entityManagerFactory: EntityManagerFactory = null
	private val lock: AnyRef = new Object
	private val mongo = new Mongo
	private val tlStockQuotesDB = new ThreadLocal[DB]	
 
	def this(emfName: String, enhanceEntities: Boolean) = {	  
	  this()
	  lock.synchronized {
		  if(enhanceEntities) {
			  val enhancer = JDOHelper.getEnhancer()
			  enhancer.setVerbose(true);
			  enhancer.addPersistenceUnit(emfName);
			  enhancer.enhance();	  
		  }
		  if(emfName != null) entityManagerFactory = Persistence.createEntityManagerFactory(emfName)		  		  
	  }
	}

	def getStockQuotesDB() : DB = {
		if(tlStockQuotesDB.get == null) {
		  val db = mongo.getDB("StockQuotesDB")
		  tlStockQuotesDB.set(db)
		  tlStockQuotesDB.get
		} else {
			tlStockQuotesDB.get
		}
	}
	
	def getSymCollection() : ShapedCollection[Symbol] = {
		val db = tlStockQuotesDB.get
		val dbCol2 = db.getCollection("Symbols")
		dbCol2.of(Symbol)		
	}
	
	def getSQCollection() : ShapedCollection[StockQuote] = {
		val db = tlStockQuotesDB.get
		val dbCol1 = db.getCollection("StockQuotes")
		dbCol1.of(StockQuote)
	}
	
	def getEntityManager() : EntityManager = {   		
		if(threadLocal.get == null) {			
			val em = entityManagerFactory.createEntityManager			
			threadLocal.set(em)
			threadLocal.get  			
		} else {
			threadLocal.get
		}
	}
	
	def closeEntityManager() : Unit = { 
		if(threadLocal.get != null && threadLocal.get.isOpen) threadLocal.get.close()
		threadLocal.set(null)
	}
}
