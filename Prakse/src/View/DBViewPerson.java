package View;
public class DBViewPerson {
	int code;
	String name;
	String surname;
	int factory;
	// parastas akcijas
	int simpleStockItem;
	// privilegetas akcijas
	int libertyStockItem;
	// personalas akcijas
	int personalStockItem;

	public DBViewPerson(int code, String name, String surname, int factory,
			int simpleStockItem, int libertyStockItem, int personalStockItem) {
		this.code = code;
		this.name = name;
		this.surname = surname;
		this.factory = factory;
		this.simpleStockItem = simpleStockItem;
		this.libertyStockItem = libertyStockItem;
		this.personalStockItem = personalStockItem;
	}
}
