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