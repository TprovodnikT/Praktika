package Interfaces;

import java.util.List;

public interface CreateDatabaseInterface {
	public String DB_URL="jdbc:derby:stockDB;create=true;";
	public String SQL_CREATE_TABLE = "sql/create_table.sql";
	public String SQL_DROP_TABLE = "sql/drop_table.sql";
	
	public List<String> createTables();
	
	
	public List<String> dropTables();
	
//	public List<String> insertTestData();
}
