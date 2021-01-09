/*
z * JUnit_Test_Methods
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * This class consists exclusively of static methods and fields.
 * Its purpose is to test most of the methods in this project using JUnit.
 *
 * @version 0.1 27 Dec 2020
 * @author Ioannis
 * 
 */

class JUnit_Test_Methods {

	@Test
	void test() {
		Scanner SCNR = new Scanner(System.in);
		boolean exit = false;
		do {
			System.out.println("Choose a method to Test:\n\n"
					+ "Interface.java\n\n"
					+ "1.  floorAreaInput();\n"
					+ "2.  firstNameInput();\n"
					+ "3.  lastNameInput();\n"
					+ "4.  emailInput();\n"
					+ "5.  custPhoneNum();\n"
					+ "6.  maskType();\n"
					+ "7.  exertionType();\n"
					+ "8.  ageGroup();\n"
					+ "9.  ~Removed~ checkType();\n"
					+ "10. inputBusinessType();\n"
					+ "11. getDate();\n"
					+ "12. getExitDate(Date entryDate, int minutesInStore);\n"
					+ "13. createPassword();\n\n"
					+ "DB_Connection.java\n\n"
					+ "14. getConnection();\n\n"
					+ "DB_Access\n\n"
					+ "15. register(Person person);\n"
					+ "16. getUsers();\n"
					+ "17. findUser(UserID);\n"
					+ "18. authenticateUser(String UserID, String password);\n\n"
					+ "19. register(Business business);\n"
					+ "20. getBusinesses();\n"
					+ "21. findBusiness(BusinessID);\n"
					+ "22. authenticateBusiness(String BusinessID, String password);\n"
					+ "23. createTable(BusinessID);\n\n"
					+ "24. checkIn(String BusinessID, String UserID, Date Date, Mask MaskType);\n"
					+ "25. checkOut(String BusinessID, String UserID, Date Date);\n"
					+ "26. getRecords(BusinessID);\n");
					
			int ch;
			do {
				ch = SCNR.nextInt();
				if (ch <= 0 || ch >= 27) {
					System.out.println("Προσπαθήστε ξανά");
				}
			} while (ch <= 0 || ch >= 27);
		
			switch (ch) {
				case 1:
					double floorArea = Interface.floorAreaInput();
					System.out.println("Τα τετραγωνικά μέτρα που δώσατε είναι: " + floorArea + "\n");
					break;
				case 2:
					String firstName = Interface.firstNameInput();
					System.out.println("Το όνομα που δώσατε: " + firstName + "\n");
					break;
				case 3:
					String lastName = Interface.lastNameInput();
					System.out.println("Το όνομα που δώσατε: " + lastName + "\n");
					break;
				case 4:
					String email = Interface.emailInput();
					System.out.println("Το email που δώσατε: " + email + "\n");
					break;
				case 5:
					long phoneNum = Interface.custPhoneNum();
					System.out.println("Ο αριθμός τηλεφώνου που δώσατε: " + phoneNum + "\n");
					break;
				case 6:
					Mask maskType = Interface.maskType();
					System.out.println("Ο τύπος μάσκας που δώσατε: " + maskType + "\n");
					break;
				case 7:
					Exertion exertionType = Interface.exertionType();
					System.out.println("Το είδος δραστηριότητας καταστήματος που δώσατε: " + exertionType + "\n");
					break;
				case 8:
					Age ageGroup = Interface.ageGroup();
					System.out.println("Η κατηγορία ηληκίας που δώσατε: " + ageGroup + "\n");
					break;
				case 9:
					break;
				case 10:
					BusinessType businessType = Interface.inputBusinessType();
					System.out.println("Η κατηγορία καταστήματος που δώσατε: " + businessType + "\n");
					break;
				case 11:
					Date date = Interface.getDate();
					System.out.println("Η ημερομηνία και ώρα που καταγράφθηκε: " + date + "\n");
					break;
				case 12:
					System.out.println("Δώστε τα λεπτά που ο πελάτης ήταν στο κατάστημα: ");
					int minutesInStore = SCNR.nextInt();
					Date exitDate = Interface.getExitDate(Interface.getDate(), minutesInStore);
					System.out.println("Η ημερομηνία και ώρα που καταγράφθηκε: " + exitDate + "\n");
					break;
				case 13:
					Interface.createPassword();
					break;
				case 14:
					System.out.println("Trying to establish connection with MySQL Database:\n");
					try {
						DB_Connection.getConnection();
						System.out.println("CONNECTION SUCCESSFUL\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED:");
						e.printStackTrace();
					}
					break;
				case 15:
					System.out.println("First, create a Person object:");
					Person person = new Person(Interface.userIDInput(0), Interface.firstNameInput(),
							Interface.lastNameInput(), Interface.emailInput(), Interface.custPhoneNum(),
							Interface.ageGroup(), Interface.createPassword());
					try {
						DB_Access.register(person);
						System.out.println("REGISTRATION SUCCESSFULL\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED DURING 'DB_Access.register(person)' :");
						e.printStackTrace();
					}
					break;
				case 16:
					System.out.println("Trying to fetch Person objects from Database:");
					try {
						ArrayList<Person> users = DB_Access.getUsers();
						int length = users.size();
						for (int i = 0; i < length; i++) {
							System.out.println(users.get(i).toString());
						}
						System.out.print("\n");
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					break;
				case 17:
					System.out.println("Give the UserID: ");
					String UserID = Interface.userIDInput(0);
					try {
						Person per = DB_Access.findUser(UserID);
						System.out.println(per.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 18:
					System.out.println("Authenticate User by providing your UserID and your Password");
					String userID = Interface.userIDInput(0);
					System.out.println("Type your password: ");
					String pass = SCNR.next();
					try {
						Person pers = DB_Access.authenticateUser(userID, pass);
						System.out.println(pers.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 19:
					System.out.println("First, create a Business object:");
					Business business = new Business(Interface.userIDInput(1), Interface.emailInput(),
							Interface.createPassword(), Interface.businessNameInput(),
							Interface.floorAreaInput(), Interface.inputBusinessType());
					try {
						DB_Access.register(business);
						System.out.println("REGISTRATION SUCCESSFULL\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED DURING 'DB_Access.register(business)' :");
						e.printStackTrace();
					}
					break;
				case 20:
					System.out.println("Trying to fetch Business objects from Database:");
					try {
						ArrayList<Business> businesses = DB_Access.getBusinesses();
						int length = businesses.size();
						for (int i = 0; i < length; i++) {
							System.out.println(businesses.get(i).toString());
						}
						System.out.print("\n");
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					break;
				case 21:
					System.out.println("Give the BusinessID: ");
					String BusinessID = Interface.userIDInput(1);
					try {
						Business bsness = DB_Access.findBusiness(BusinessID);
						System.out.println(bsness.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 22:
					System.out.println("Authenticate Business by providing your BusinessID and your Password");
					String businessID = Interface.userIDInput(1);
					System.out.println("Type your password: ");
					String password = SCNR.next();
					try {
						Business busn = DB_Access.authenticateBusiness(businessID, password);
						System.out.println(busn.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 23:
					String iD = Interface.userIDInput(1);
					try {
						DB_Access.createTable(iD);
						System.out.println("TABLE CREATION SUCCESSFULL\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 24:
					String businessiD = Interface.userIDInput(1);
					String useriD = Interface.userIDInput(0);
					Mask mask = Interface.maskType();
					java.util.Date utilDate = Interface.getDate();
					System.out.println("\nTrying to insert CheckIn into Database:");
					try {
						DB_Access.checkIn(businessiD, useriD, utilDate, mask);
						System.out.println("CHECK IN SUCCESSFULL\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 25:
					String businessID1 = Interface.userIDInput(1);
					String userID1 = Interface.userIDInput(0);
					java.util.Date utilDate1 = Interface.getDate();
					java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
					System.out.println("\nTrying to insert CheckOut into Database:");
					try {
						DB_Access.checkOut(businessID1, userID1, sqlDate1);
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 26:
					String businessID2 = Interface.userIDInput(1);
					try {
						ArrayList<Record> recordsList = DB_Access.getRecords(businessID2);
						for (Record rec : recordsList) {
							System.out.println(rec.toString());
						}
						System.out.println(" ");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
			}
			System.out.println("Do you want to try another method?\n"
					+ "Type yes/no");
			String choice = null;
			do {
				choice = SCNR.next();
				if (choice.contentEquals("yes")) {
					exit = false;
				} else if (choice.contentEquals("no")) {
					exit = true;
				} else {
					System.out.println("Wrong input. Must be yes or no. Yours was: " + choice);
					choice = null;
				}
			} while (choice == null);
		} while (exit == false);
		SCNR.close();
	}
}
