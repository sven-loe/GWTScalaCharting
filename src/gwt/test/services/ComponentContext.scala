package gwt.test.services

import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager
import javax.jdo.JDOEnhancer
import javax.jdo.JDOHelper

class ComponentContext(val entityManagerFactory: EntityManagerFactory) {	
	private val threadLocal = new ThreadLocal[Option[EntityManager]]
	
	def this(entityManagerFactory: EntityManagerFactory, enhanceEntities: boolean) = {
	  this(entityManagerFactory)
	  if(enhanceEntities) {
		  val enhancer = JDOHelper.getEnhancer()
		  enhancer.setVerbose(true);
		  enhancer.addPersistenceUnit("jpa");
		  enhancer.enhance();
	  }
	}
 
	def getEntityManager() : EntityManager = {   		
		if(threadLocal.get == None) {			
			threadLocal.set(Some(entityManagerFactory.createEntityManager()))
			threadLocal.get.get  			
		} else {
			threadLocal.get.get
		}
	}
	
	def closeEntityManager() : Unit = { 
		if(threadLocal.get != None && threadLocal.get.get.isOpen) threadLocal.get.get.close()
		threadLocal.set(None)
	}
}
