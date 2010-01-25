package gwt.test.services

trait LoggingInterceptor extends Interceptor {
    val logAnnotation = classOf[gwt.test.annotations.Logging]
 
    abstract override def invoke(invocation: Invocation): AnyRef = 
      if (matches(logAnnotation , invocation)) {
        println("=====> Enter: " + invocation.method.getName + " @ " + invocation.target.getClass.getName)
        val result = super.invoke(invocation)
        println("=====> Exit: " + invocation.method.getName + " @ " + invocation.target.getClass.getName)
        result
      } else super.invoke(invocation)
}  