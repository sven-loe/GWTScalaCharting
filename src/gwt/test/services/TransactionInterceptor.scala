package gwt.test.services

import javax.persistence.EntityManager

trait TransactionInterceptor extends Interceptor {
    val taAnnotation = classOf[gwt.test.annotations.Transaction]
    val entityManager: EntityManager
    
    abstract override def invoke(invocation: Invocation): AnyRef = 
      if (matches(taAnnotation, invocation)) {
        println("=====> TX begin")
        val tx = entityManager.getTransaction
        try {
          tx.begin
          val result = super.invoke(invocation)
          println("=====> TX commit")
          tx.commit
          result     
        } catch {
          case e: Exception => 
            println("=====> TX rollback ")
            tx.rollback;	      
            throw e;
        } 
      } else super.invoke(invocation)
  }