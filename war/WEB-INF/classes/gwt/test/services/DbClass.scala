package gwt.test.services

import javax.persistence.Persistence;
import gwt.test.components._
 
class DbClass extends StockImporterComponentImpl {  	
	private val em = EntityManagerFactory.getEntityManager
	val stockImporter = ManagedComponentFactory.createComponent[StockImporter](  
			classOf[StockImporter],  
			new ManagedComponentProxy(new StockImporterImpl(em),em)  
			with LoggingInterceptor
			with TransactionInterceptor)  
}
