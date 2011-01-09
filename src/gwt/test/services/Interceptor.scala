/*This file is part of GWTScalaCharting

GWTScalaCharting is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.

GWTScalaCharting is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>. */

package gwt.test.services
 
trait Interceptor {
  protected def matches(annotationClass: java.lang.Class[T] forSome {type T <: java.lang.annotation.Annotation}, invocation: Invocation): Boolean = {        
//    val invocationClass = Class.forName(invocation.target.getClass.getName)
//    val invocationMethod = invocationClass.getDeclaredMethod(invocation.method.getName, invocation.method.getParameterTypes:_*)
//    invocationClass.isAnnotationPresent(annotationClass) || 
//    invocationMethod.isAnnotationPresent(annotationClass) ||
//    false
    invocation.method.isAnnotationPresent(annotationClass) ||
    invocation.target.getClass.isAnnotationPresent(annotationClass) ||
    false   
//    true
  }
 
  def invoke(invocation: Invocation): AnyRef
}