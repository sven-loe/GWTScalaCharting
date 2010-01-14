package gwt.test.services

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

trait JpaUtil {	  
  
  def getEntityManager() : EntityManager
  
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
