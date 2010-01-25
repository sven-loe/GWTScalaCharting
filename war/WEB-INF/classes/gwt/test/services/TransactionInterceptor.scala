package gwt.test.services

import javax.persistence.EntityManager

trait TransactionInterceptor extends Interceptor {
    val taAnnotation = classOf[gwt.test.annotations.Transaction]
    val context: ComponentContext
    
    abstract override def invoke(invocation: Invocation): AnyRef = 
      if (matches(taAnnotation, invocation)) {
        println("=====> TX begin")
        val tx = context.getEntityManager().getTransaction
        try {
          tx.begin
          val result = super.invoke(invocation)
          println("=====> TX commit")
          tx.commit
          context.closeEntityManager()
          result     
        } catch {
          case e: Exception => 
            println("=====> TX rollback ")
            tx.rollback
            context.closeEntityManager()
            throw e;
        } 
      } else super.invoke(invocation)
} 