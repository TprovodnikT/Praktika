package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import Data.DBPerson;
import Data.DatabaseList;
import Interfaces.CreateDatabaseInterface;
import View.DatabaseView;

;

public class CreateDatabase implements CreateDatabaseInterface {
	Connection con = null;
	Statement stm;
	List<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		// new CreateDatabase().dropTables();
		// new CreateDatabase().createTables();
//		 new DatabaseList().setRandomDataToList();
//		 new CreateDatabase().insertFromStaticListIntoDB();
//		new CreateDatabase().viewTable();
		try {
			System.out.println(new CreateDatabase().getNextSequenceValue());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewTable() {
		try {
			con = DriverManager.getConnection(DB_URL);
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from stock_holders");
			System.out.println("View Table:");
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.print(rs.getString(2));
				System.out.print(rs.getString(3));
				System.out.print(rs.getInt(4));
				System.out.print(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> createTables() {
		// try {
		// con = DriverManager.getConnection(DB_URL);
		// stm = con.createStatement();
		// stm.executeUpdate("CREATE TABLE stock_holders(id int not null)");
		// // +
		// //
		// ", name varchar(255), surname varchar(255), factory decimal(1,0), phone varchar(13), simpleStockItem decimal(10), personalStockItem decimal(10), libertyStockItem decimal(10), adress varchar(255), areaCode varchar(30), personalCodeOrRegistraionNumber varchar(12), bankCode varchar(1000), citizen varchar(25), primary key(id));");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// return null;
		return sqlRun(SQL_CREATE_TABLE);
	}

	@Override
	public List<String> dropTables() {
		return sqlRun(SQL_DROP_TABLE);
	}

	public void insertFromDBIntoStaticList() {
		try {
			con = DriverManager.getConnection(DB_URL);
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from stock_holders");
			while (rs.next()) {
				// System.out.println(rs.getInt(1));
				// System.out.print(rs.getString(2));
				// System.out.print(rs.getString(3));
				// System.out.print(rs.getInt(4));
				// System.out.print(rs.getString(5));
				isDbListNull();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String surname = rs.getString(3);
				int factory = rs.getInt(4);
				String phone = rs.getString(5);
				int simpleStockItem = rs.getInt(6);
				int personalStockItem = rs.getInt(7);
				int libertyStockItem = rs.getInt(8);
				String adress = rs.getString(9);
				String areaCode = rs.getString(10);
				String personalCodeOrRegistraionNumber = rs.getString(11);
				String bankCode = rs.getString(12);
				Boolean citizen = rs.getBoolean(13);
				DatabaseList.listOfStockHolders.add(new DBPerson(id, name,
						surname, factory, phone,
						personalCodeOrRegistraionNumber, simpleStockItem,
						libertyStockItem, personalStockItem, adress, areaCode,
						bankCode, citizen));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertFromStaticListIntoDB() {
		for (DBPerson dbPerson : DatabaseList.listOfStockHolders) {
			insertIntoDatabase(dbPerson);
		}
	}

	public String insertIntoDatabase(DBPerson dbPerson) {
		try {
			con = DriverManager.getConnection(DB_URL);
			stm = con.createStatement();
			getNextSequenceValue();
			stm.executeUpdate("insert into stock_holders" + "(id," + "name,"
					+ "surname," + "factory," + "phone," + "simpleStockItem,"
					+ "personalStockItem," + "libertyStockItem," + "adress,"
					+ "areaCode," + "personalCodeOrRegistraionNumber,"
					+ "bankCode," + "citizen)" + " values("
					// + dbPerson.getId()
					+ "NEXT VALUE FOR sequence_for_id" + "," + "'"
					+ dbPerson.getName()
					+ "',"
					+ "'"
					+ dbPerson.getSurname()
					+ "',"
					+ dbPerson.getFactory()
					+ ","
					+ "'"
					+ dbPerson.getPhone()
					+ "',"
					+ dbPerson.getSimpleStockItem()
					+ ","
					+ dbPerson.getPersonalStockItem()
					+ ","
					+ dbPerson.getLibertyStockItem()
					+ ","
					+ "'"
					+ dbPerson.getAdress()
					+ "',"
					+ "'"
					+ dbPerson.getAreaCode()
					+ "',"
					+ "'"
					+ dbPerson.getPersonalCodeOrRegistraionNumber()
					+ "',"
					+ "'"
					+ dbPerson.getBankCode()
					+ "',"
					+ dbPerson.isCitizenOrNot() + ")");
		} catch (SQLException e) {
			return DatabaseView.FAIL;
		}
		return DatabaseView.OK;
	}

	protected int getNextSequenceValue() throws SQLException {
		con=DriverManager.getConnection(DB_URL);
		stm=con.createStatement();
		ResultSet rs=stm.executeQuery("SELECT NEXT VALUE FOR sequence_for_id");
		return rs.getInt(1);
	}

	private void isDbListNull() {
		if (DatabaseList.listOfStockHolders == null) {
			DatabaseList.listOfStockHolders = new ArrayList<DBPerson>();
		}
	}

	public String changeToInactive(int index) {
		try {
			con = DriverManager.getConnection(DB_URL);
			stm = con.createStatement();
			System.out.println(index);
			String sql = "UPDATE stock_holders "
					+ "SET simpleStockItem=0,personalStockItem=0, libertyStockItem=0 "
					+ "WHERE id=" + index + "";
			stm.executeUpdate(sql.toUpperCase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Nav tāda akcionāra vai nav pieslēguma pie Datu Bāzes.";
		}
		return "OK";
	}

	// public List<String> insertTestData() {
	// return sqlRun(SQL_INSERT_DATA);
	// try {
	// con = DriverManager.getConnection(DB_URL);
	// stm = con.createStatement();
	// ResultSet rs = stm.executeQuery("select * from products");
	// while(rs.next()){
	// int products_id = rs.getInt(1);
	// String productName = rs.getString(2);
	// BigDecimal price=rs.getBigDecimal(3);
	// String description=rs.getString(4);
	// System.out.println("Product : "+products_id+": "+productName+" "+price+" "+description);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
	public List<String> sqlRun(String msg) {
		try {
			con = DriverManager.getConnection(DB_URL);
			stm = con.createStatement();
			List<String> createSQLs = Utils.parseSQL(msg);
			for (String sql : createSQLs) {
				stm.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
			list.add(e.getMessage());
			return list;
		} catch (Exception e) {
			list.add(e.getMessage());
			return list;
		}
		return list;
	}

	// public static void main(String[] args) {
	// CreateDatabase DB = new CreateDatabase();
	// DB.createTables();
	// DB.insertFromStaticListIntoDB();
	// System.out.println("DBList " + DatabaseList.listOfStockHolders);
	//
	// }

}
