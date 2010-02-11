package gwt.test.services

import javax.persistence.Persistence
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager
import javax.jdo.JDOEnhancer
import javax.jdo.JDOHelper

class ComponentContext() {	
	private val threadLocal = new ThreadLocal[EntityManager]
	private var entityManagerFactory: EntityManagerFactory = null
	private val lock: AnyRef = new Object
 
	def this(emfName: String, enhanceEntities: boolean) = {	  
	  this()
	  lock.synchronized {
		  if(enhanceEntities) {
			  val enhancer = JDOHelper.getEnhancer()
			  enhancer.setVerbose(true);
			  enhancer.addPersistenceUnit("jpa");
			  enhancer.enhance();	  
		  }
		  entityManagerFactory = Persistence.createEntityManagerFactory(emfName)		  
	  }
	}
 
	def getEntityManager() : EntityManager = {   		
		if(threadLocal.get == null) {			
			threadLocal.set(entityManagerFactory.createEntityManager())
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
