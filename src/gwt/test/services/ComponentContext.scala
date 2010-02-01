package gwt.test.services

import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager

class ComponentContext(val entityManagerFactory: EntityManagerFactory) {	
	private val threadLocal = new ThreadLocal[Option[EntityManager]]
	
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
