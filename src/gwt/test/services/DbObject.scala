package gwt.test.services

import javax.persistence.Persistence;

object DbObject extends StockImporterComponentImpl {
	val stockImporter = new StockImporterImpl(Persistence.createEntityManagerFactory("jpa"))
}
