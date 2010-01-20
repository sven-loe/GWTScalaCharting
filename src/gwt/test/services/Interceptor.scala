package gwt.test.services

trait Interceptor {
  protected def matches(annotationClass: java.lang.Class[T] forSome {type T <: java.lang.annotation.Annotation}, invocation: Invocation): Boolean = {
    invocation.method.isAnnotationPresent(annotationClass) ||
    invocation.target.getClass.isAnnotationPresent(annotationClass) ||
    false
  }

  def invoke(invocation: Invocation): AnyRef
}