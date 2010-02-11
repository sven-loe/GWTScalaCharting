package gwt.test.services

import javax.persistence.Persistence;
import gwt.test.components._
 
object DbObject extends StockImporterComponentImpl with StockDBServiceComponentImpl {  		
	private val context = new ComponentContext(Persistence.createEntityManagerFactory("jpa"), true)
 
	val stockImporter = ManagedComponentFactory.createComponent[StockImporter](  
			classOf[StockImporter],  
			new ManagedComponentProxy(new StockImporterImpl(context),context)  
			with LoggingInterceptor
			with TransactionInterceptor)
 
	val stockDBService = ManagedComponentFactory.createComponent[StockDBService](  
			classOf[StockDBService],  
			new ManagedComponentProxy(new StockDBServiceImpl(context),context)  
			with LoggingInterceptor
			with TransactionInterceptor)
	
} 
