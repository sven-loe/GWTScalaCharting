package gwt.test.services

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

trait JpaUtil {
	
  var emf: EntityManagerFactory = null
  
  def getEntityManager() : EntityManager = {
	emf.createEntityManager;  
  }
  
  	def transaction(em: EntityManager,op: EntityManager => Unit) {	  
	  val tx = em.getTransaction;
	  try{
		  tx.begin;
		  op(em);		  
		  tx.commit;
	  } catch {
	    case e => 
	      tx.rollback;	      
	      throw e;       
	  }	  
	}
}
