package Data;

public class DBPerson {
	public static final String JAUDA_WORKERS="1";
	public static final String JAUDA_ADMINISTRATION="2";
	public static final String JAUDA_FREE="3";
	public static final String JAUDA_ITD="4";
	public static final String JURIDICAL="5";
	private int id;
	private String name;
	private String surname;
	private int factory;
	private String phone;
	// parastas akcijas
	private int simpleStockItem;
	// privilegetas akcijas
	private int libertyStockItem;
	// personalas akcijas
	private int personalStockItem;
	private String adress;
	private String personalCodeOrRegistraionNumber;
	private String areaCode;
	private String bankCode;
	private boolean citizenOrNot;

	// fiziska persona vai ne

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getFactory() {
		return factory;
	}

	public void setFactory(int factory) {
		this.factory = factory;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSimpleStockItem() {
		return simpleStockItem;
	}

	public void setSimpleStockItem(int simpleStockItem) {
		this.simpleStockItem = simpleStockItem;
	}

	public int getLibertyStockItem() {
		return libertyStockItem;
	}

	public void setLibertyStockItem(int libertyStockItem) {
		this.libertyStockItem = libertyStockItem;
	}

	public int getPersonalStockItem() {
		return personalStockItem;
	}

	public void setPersonalStockItem(int personalStockItem) {
		this.personalStockItem = personalStockItem;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPersonalCodeOrRegistraionNumber() {
		return personalCodeOrRegistraionNumber;
	}

	public void setPersonalCodeOrRegistraionNumber(
			String personalCodeOrRegistraionNumber) {
		this.personalCodeOrRegistraionNumber = personalCodeOrRegistraionNumber;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public boolean isCitizenOrNot() {
		return citizenOrNot;
	}

	public void setCitizenOrNot(boolean citizenOrNot) {
		this.citizenOrNot = citizenOrNot;
	}

	public int getAllStocks() {
		int sum = simpleStockItem + libertyStockItem + personalStockItem;
		return sum;
	}

	public DBPerson(int id, String name, String surname, int factory,
			String phone, String personalCode, int simpleStockItem,
			int libertyStockItem, int personalStockItem, String adress,
			String areaCode, String bankCode, boolean citizenOrNot) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.factory = factory;
		this.phone = phone;
		this.personalCodeOrRegistraionNumber = personalCode;
		this.simpleStockItem = simpleStockItem;
		this.libertyStockItem = libertyStockItem;
		this.personalStockItem = personalStockItem;
		this.adress = adress;
		this.areaCode = areaCode;
		this.bankCode = bankCode;
		this.citizenOrNot = citizenOrNot;
	}
}
