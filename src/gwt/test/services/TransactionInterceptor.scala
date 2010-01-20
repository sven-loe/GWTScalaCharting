package gwt.test.services

trait TransactionInterceptor extends Interceptor {
    val matchingAnnotation = classOf[gwt.test.annotations.Transaction]

    abstract override def invoke(invocation: Invocation): AnyRef = 
      if (matches(matchingAnnotation, invocation)) {
        println("=====> TX begin")
        try {
          val result = super.invoke(invocation)
          println("=====> TX commit")
          result     
        } catch {
          case e: Exception => 
            println("=====> TX rollback ")
        } 
      } else super.invoke(invocation)
  }