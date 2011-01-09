package gwt.test.services

import gwt.test.components._

class MongoComponentFactory(val context: ComponentContext) extends StockDBMongoServiceComponentImpl with StockImporterMongoComponentImpl with SymbolImporterMongoComponentImpl {
	
  val stockImporter = ManagedComponentFactory.createComponent[StockImporter](  
			classOf[StockImporter],  
			new ManagedComponentProxy(new StockImporterImpl(context),context)  
			with LoggingInterceptor
			with MongoTransactionInterceptor)
 
  val stockDBService = ManagedComponentFactory.createComponent[StockDBService](  
			classOf[StockDBService],  
			new ManagedComponentProxy(new StockDBServiceImpl(context),context)  
			with LoggingInterceptor
			with MongoTransactionInterceptor)
  
  val symbolImporter = ManagedComponentFactory.createComponent[SymbolImporter](  
			classOf[SymbolImporter],  
			new ManagedComponentProxy(new SymbolImporterImpl(context),context)  
			with LoggingInterceptor
			with MongoTransactionInterceptor)
}