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
