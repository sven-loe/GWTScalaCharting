package gwt.test.services

trait MongoTransactionInterceptor extends Interceptor {
	val mongoTaAnnotation = classOf[gwt.test.annotations.MongoTransaction]
	val context: ComponentContext
	
	abstract override def invoke(invocation: Invocation): AnyRef = {       
      if (matches(mongoTaAnnotation , invocation)) {
        val db = context.getStockQuotesDB
    	println("=====> RequestStart")
        db.requestStart()
        val result = super.invoke(invocation)
        db.requestDone()
        println("=====> RequestDone")
        result
      } else super.invoke(invocation)
	}
}