package gwt.test.components

import javax.persistence.EntityManager

trait JpaUtil {	  
   
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
