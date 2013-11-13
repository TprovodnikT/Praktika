package Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseList {
	public static List<DBPerson> listOfStockHolders;

	// Random data
	// ===============================
	public String setBankCode(int factory) {
		Random randomGenerator = new Random();
		String bankCode = "";
		if (factory == 1 || factory == 3) {
			int lucky = randomGenerator.nextInt(2);
			if (lucky == 1) {
				for (int i = 0; i < 20; i++) {
					if (i < 2) {
						bankCode += String.valueOf(Character
								.toChars(randomGenerator.nextInt(25) + 65));
					} else if (i >= 2 && i < 4)
						bankCode += randomGenerator.nextInt(10);
					else if (i >= 4 && i < 8)
						bankCode += String.valueOf(Character
								.toChars(randomGenerator.nextInt(25) + 65));
					else if (i >= 8 && i < 14)
						bankCode += randomGenerator.nextInt(10);
					else if (i == 14)
						bankCode += String.valueOf(Character
								.toChars(randomGenerator.nextInt(25) + 65));
					else if (i > 14 && i < 20)
						bankCode += randomGenerator.nextInt(10);
				}
			} else {
				bankCode = "";
			}
		} else {
			for (int i = 0; i < 20; i++) {
				if (i < 2) {
					bankCode += String.valueOf(Character
							.toChars(randomGenerator.nextInt(25) + 65));
				} else if (i >= 2 && i < 4)
					bankCode += randomGenerator.nextInt(10);
				else if (i >= 4 && i < 8)
					bankCode += String.valueOf(Character
							.toChars(randomGenerator.nextInt(25) + 65));
				else if (i >= 8 && i < 14)
					bankCode += randomGenerator.nextInt(10);
				else if (i == 14)
					bankCode += String.valueOf(Character
							.toChars(randomGenerator.nextInt(25) + 65));
				else if (i > 14 && i < 20)
					bankCode += randomGenerator.nextInt(10);
			}
		}

		return bankCode;

	}

	public String setPersonalCode(int factory) {
		Random randomGenerator = new Random();
		String personalCodeOrRegistraionNumber;
		if (factory != 5) {
			String sDay, sMonth, sYear;

			int day = randomGenerator.nextInt(31);
			if (day < 10) {
				sDay = "0" + day;
			} else {
				sDay = String.valueOf(day);
			}
			int month = randomGenerator.nextInt(12);
			if (month < 10) {
				sMonth = "0" + month;
			} else {
				sMonth = String.valueOf(month);
			}
			int year = randomGenerator.nextInt(99);
			if (year < 10) {
				sYear = "0" + year;
			} else {
				sYear = String.valueOf(year);
			}
			int secondPart = randomGenerator.nextInt(89999) + 10000;
			personalCodeOrRegistraionNumber = sDay + sMonth + sYear + "-"
					+ secondPart;
			return personalCodeOrRegistraionNumber;
		} else {
			int registrationCode = randomGenerator.nextInt(899999999) + 100000000;
			personalCodeOrRegistraionNumber = String.valueOf(registrationCode);
			registrationCode = randomGenerator.nextInt(10);
			personalCodeOrRegistraionNumber += String.valueOf(registrationCode);
			return personalCodeOrRegistraionNumber;
		}

	}

	public boolean setCitizenSetIndividual() {
		boolean citizen;
		Random randomGenerator = new Random();
		int i = randomGenerator.nextInt(2);
		if (i == 1) {
			citizen = true;
		} else
			citizen = false;
		return citizen;
	}

	// ===============================
	public void setRandomDataToList() {
		DatabaseList.listOfStockHolders=new ArrayList<DBPerson>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 100; i++) {
			int code = i;
			String phone = String.valueOf("+"
					+ (randomGenerator.nextInt(899) + 100) + " "
					+ (randomGenerator.nextInt(89999999) + 10000000));
			String name = String.valueOf(Character.toChars(i + 65));
			String surname = String.valueOf(Character.toChars(i + 66));
			int factory = (int) randomGenerator.nextInt(5) + 1;
			String personalCode = setPersonalCode(factory);
			int simpleStockItem = randomGenerator.nextInt(1000);
			int libertyStockItem = randomGenerator.nextInt(1000);
			int personalStockItem = randomGenerator.nextInt(1000);
			String adress = String.valueOf(i + 7);
			String areaCode = String.valueOf(i + 8);
			String bankCode = setBankCode(factory);
			boolean citizenOrNot = setCitizenSetIndividual();
			DatabaseList.listOfStockHolders
					.add(new DBPerson(code, name, surname, factory, phone,
							personalCode, simpleStockItem, libertyStockItem,
							personalStockItem, adress, areaCode, bankCode,
							citizenOrNot));
		}
	}
	
	public void clearListOfStockHolders(){
		listOfStockHolders.clear();
	}

}
