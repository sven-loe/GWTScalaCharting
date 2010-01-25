package gwt.test.services

import javax.persistence.EntityManagerFactory
import javax.persistence.EntityManager

class ComponentContext(val entityManagerFactory: EntityManagerFactory) {
	private var entityManager: Option[EntityManager] = None
	
	def getEntityManager() : EntityManager = {   
		if(entityManager == None) {			
			entityManager = Some(entityManagerFactory.createEntityManager())
			entityManager.get  			
		} else {
			entityManager.get
		}
	}
	
	def closeEntityManager() : Unit = { 
		entityManager.get.close()
		entityManager = None
 }
}
