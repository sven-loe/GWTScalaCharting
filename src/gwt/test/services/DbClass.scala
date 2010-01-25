package gwt.test.services

import javax.persistence.Persistence;
import gwt.test.components._
 
class DbClass extends StockImporterComponentImpl {  		
	private val context = new ComponentContext(Persistence.createEntityManagerFactory("jpa"))
	val stockImporter = ManagedComponentFactory.createComponent[StockImporter](  
			classOf[StockImporter],  
			new ManagedComponentProxy(new StockImporterImpl(context),context)  
			with LoggingInterceptor
			with TransactionInterceptor)  
} 
