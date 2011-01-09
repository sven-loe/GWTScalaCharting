package gwt.test.services

import javax.persistence.Persistence
import javax.persistence.EntityManager

object EntityManagerFactory { 
	val emf = Persistence.createEntityManagerFactory("jpa")
	def getEntityManager() : EntityManager = {
	  emf.createEntityManager
	}
}
