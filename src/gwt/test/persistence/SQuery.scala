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

package gwt.test.persistence

import javax.persistence.FlushModeType
import javax.persistence.Query
import java.util.Calendar
import javax.persistence.TemporalType
import java.util.Date
import scala.collection.jcl.ArrayList


class SQuery(query: Query) {
	def executeUpdate = query.executeUpdate
	def getResultList[T] : List[T]= {
	  val myStockQuotes = query.getResultList	     
      val stockQuotes = new ArrayList(new java.util.ArrayList[T](myStockQuotes.asInstanceOf[java.util.List[T]]))
      stockQuotes.toList
	}
	def getSingleResult[T] = query.getSingleResult.asInstanceOf[T]
	def setFirstResult(startPosition: Int)  = query.setFirstResult(startPosition: Int)
	def setFlushMode(flushMode: FlushModeType) = query.setFlushMode(flushMode: FlushModeType)
	def setHint(hintName: String, value: Object) = query.setHint(hintName: String, value: Object)
	def setMaxResults(maxResult: Int) = query.setMaxResults(maxResult: Int)
	def setParameter(position: Int, value: Calendar, temporalType: TemporalType) = query.setParameter(position: Int, value: Calendar, temporalType: TemporalType)
	def setParameter(position: Int, value: Date, temporalType: TemporalType) = query.setParameter(position: Int, value: Date, temporalType: TemporalType)
	def setParameter(position: Int, value: Object) = query.setParameter(position: Int, value: Object)
	def setParameter(name: String, value: Calendar, temporalType: TemporalType) = query.setParameter(name: String, value: Calendar, temporalType: TemporalType)
	def setParameter(name: String, value: Date, temporalType: TemporalType) = query.setParameter(name: String, value: Date, temporalType: TemporalType)
	def setParameter(name: String, value: Object) = query.setParameter(name: String, value: Object)
}
