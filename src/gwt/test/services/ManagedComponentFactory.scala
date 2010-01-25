package gwt.test.services

import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.lang.reflect.InvocationHandler
import javax.persistence.EntityManager


object ManagedComponentFactory {
  def createComponent[T](intf: Class[T] forSome {type T}, proxy: ManagedComponentProxy): T =
    Proxy.newProxyInstance(
      proxy.target.getClass.getClassLoader,
      Array(intf),
      proxy).asInstanceOf[T]
}
 
class ManagedComponentProxy(val target: AnyRef, val entityManager: EntityManager) extends InvocationHandler {
  def invoke(proxy: AnyRef, m: Method, args: Array[AnyRef]): AnyRef = invoke(Invocation(m, args, target))
  def invoke(invocation: Invocation): AnyRef = invocation.invoke
}