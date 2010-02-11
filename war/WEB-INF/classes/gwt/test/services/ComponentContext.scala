package gwt.test.services

import javax.persistence.Persistence
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager
import javax.jdo.JDOEnhancer
import javax.jdo.JDOHelper

class ComponentContext() {	
	private val threadLocal = new ThreadLocal[Option[EntityManager]]
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
		  threadLocal.set(None)
	  }
	}
 
	def getEntityManager() : EntityManager = {   		
		if(threadLocal.get == null || threadLocal.get == None) {			
			threadLocal.set(Some(entityManagerFactory.createEntityManager()))
			threadLocal.get.get  			
		} else {
			threadLocal.get.get
		}
	}
	
	def closeEntityManager() : Unit = { 
		if((threadLocal.get != null || threadLocal.get != None) && threadLocal.get.get.isOpen) threadLocal.get.get.close()
		threadLocal.set(None)
	}
}
