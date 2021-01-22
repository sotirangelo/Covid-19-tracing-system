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
		String userID = null;
		String businessID = null;
		String email = null;
		String password = null;
		long phoneNum = 0;
		Person user = null;
		Business business = null;
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
					+ "23. ~Removed~ createTable(BusinessID);\n\n"
					+ "24. checkIn(String BusinessID, String UserID, Date Date, Mask MaskType);\n"
					+ "25. checkOut(String BusinessID, String UserID, Date Date);\n"
					+ "26. getRecords(BusinessID);\n\n"
					+ "27. editUserEmail(String, String);\n"
					+ "28. editUserPhoneNumber(String, long);\n"
					+ "29. editUserAgeCategory(String, Age);\n"
					+ "30. editUserPassword(String, String);\n"
					+ "31. editBusinessEmail(String, String);\n"
					+ "32. editBusinessPassword(String, String);\n"
					+ "33. editBusinessSpace(String, double);\n"
					+ "34. editBusinessType(String, BusinessType);\n"
					+ "35. editBusinessVentilationType(String, AER);\n"
					+ "36. editLastRecordsUserID(String, String);\n\n"
					+ "37. businessesVisited(String);\n"
					+ "38. getPersonsRecord(Person, Business);\n");					
			int ch;
			do {
				ch = SCNR.nextInt();
				if (ch <= 0 || ch >= 40) {
					System.out.println("Προσπαθήστε ξανά");
				}
			} while (ch <= 0 || ch >= 40);
		
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
					email = Interface.emailInput();
					System.out.println("Το email που δώσατε: " + email + "\n");
					break;
				case 5:
					phoneNum = Interface.custPhoneNum();
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
					userID = Interface.userIDInput(0);
					try {
						Person per = DB_Access.findUser(userID);
						System.out.println(per.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 18:
					System.out.println("Authenticate User by providing your UserID and your Password");
					userID = Interface.userIDInput(0);
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
					business = new Business(Interface.userIDInput(1), Interface.emailInput(),
							Interface.createPassword(), Interface.businessNameInput(),
							Interface.floorAreaInput(), Interface.inputBusinessType(), Interface.ventilationType());
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
					businessID = Interface.userIDInput(1);
					try {
						Business bsness = DB_Access.findBusiness(businessID);
						System.out.println(bsness.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 22:
					System.out.println("Authenticate Business by providing your BusinessID and your Password");
					businessID = Interface.userIDInput(1);
					System.out.println("Type your password: ");
					password = SCNR.next();
					try {
						Business busn = DB_Access.authenticateBusiness(businessID, password);
						System.out.println(busn.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 23:
					break;
				case 24:
					businessID = Interface.userIDInput(1);
					userID = Interface.userIDInput(0);
					Mask mask = Interface.maskType();
					java.util.Date utilDate = Interface.getDate();
					System.out.println("\nTrying to insert CheckIn into Database:");
					try {
						DB_Access.checkIn(businessID, userID, utilDate, mask);
						System.out.println("CHECK IN SUCCESSFULL\n");
					} catch (Exception e) {
					}
					break;
				case 25:
					businessID = Interface.userIDInput(1);
					userID = Interface.userIDInput(0);
					java.util.Date utilDate1 = Interface.getDate();
					java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
					System.out.println("\nTrying to insert CheckOut into Database:");
					try {
						DB_Access.checkOut(businessID, userID, sqlDate1);
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 26:
					businessID = Interface.userIDInput(1);
					try {
						ArrayList<Record> recordsList = DB_Access.getRecords(businessID);
						for (Record rec : recordsList) {
							System.out.println(rec.toString());
						}
						System.out.println(" ");
					} catch (Exception e) {
					}
					break;
				case 27:
					userID = Interface.userIDInput(0);
					email = Interface.emailInput();
					try {
						user = DB_Access.findUser(userID);
						System.out.println("Old Email: " + user.getEmail());
						DB_Access.editUserEmail(userID, email);
						user = DB_Access.findUser(userID);
						System.out.println("New Email: " + user.getEmail());
					} catch (Exception e) {
					}
					break;
				case 28:
					userID = Interface.userIDInput(0);
					phoneNum = Interface.custPhoneNum();
					try {
						user = DB_Access.findUser(userID);
						System.out.println("Old Phone Number: " + user.getPhoneNumber());
						DB_Access.editUserPhoneNumber(userID, phoneNum);
						user = DB_Access.findUser(userID);
						System.out.println("New Phone Number: " + user.getPhoneNumber());
					} catch (Exception e) {
					}
					break;
				case 29:
					userID = Interface.userIDInput(0);
					Age age = Interface.ageGroup();
					try {
						user = DB_Access.findUser(userID);
						System.out.println("Old Age Group: " + user.getAgeCategory());
						DB_Access.editUserAgeCategory(userID, age);
						user = DB_Access.findUser(userID);
						System.out.println("New Age Group: " + user.getAgeCategory());
					} catch (Exception e) {
					}
					break;
				case 30:
					userID = Interface.userIDInput(0);
					password = Interface.createPassword();
					try {
						user = DB_Access.findUser(userID);
						System.out.println("Old Password: " + user.getPassword());
						DB_Access.editUserPassword(userID, password);
						user = DB_Access.findUser(userID);
						System.out.println("New Password: " + user.getPassword());
					} catch (Exception e) {
					}
					break;
				case 31:
					businessID = Interface.userIDInput(1);
					email = Interface.emailInput();
					try {
						business = DB_Access.findBusiness(businessID);
						System.out.println("Old Email: " + business.getEmail());
						DB_Access.editBusinessEmail(businessID, email);
						business = DB_Access.findBusiness(businessID);
						System.out.println("New Email: " + business.getEmail());
					} catch (Exception e) {
					}
					break;
				case 32:
					businessID = Interface.userIDInput(1);
					password = Interface.createPassword();
					try {
						business = DB_Access.findBusiness(businessID);
						System.out.println("Old Password: " + business.getPassword());
						DB_Access.editBusinessPassword(businessID, password);
						business = DB_Access.findBusiness(businessID);
						System.out.println("New Password: " + business.getPassword());
					} catch (Exception e) {
					}
					break;
				case 33:
					businessID = Interface.userIDInput(1);
					double space = Interface.floorAreaInput();
					try {
						business = DB_Access.findBusiness(businessID);
						System.out.println("Old Floor Area: " + business.getSpace());
						DB_Access.editBusinessSpace(businessID, space);
						business = DB_Access.findBusiness(businessID);
						System.out.println("New Floor Area: " + business.getSpace());
					} catch (Exception e) {
					}
					break;
				case 34:
					businessID = Interface.userIDInput(1);
					BusinessType businesstype = Interface.inputBusinessType();
					try {
						business = DB_Access.findBusiness(businessID);
						System.out.println("Old Business Type: " + business.getBusinessType());
						DB_Access.editBusinessType(businessID, businesstype);
						business = DB_Access.findBusiness(businessID);
						System.out.println("New Business Type: " + business.getBusinessType());
					} catch (Exception e) {
					}
					break;
				case 35:
					businessID = Interface.userIDInput(1);
					AER ventilation = Interface.ventilationType();
					try {
						business = DB_Access.findBusiness(businessID);
						System.out.println("Old Ventilation Type: " + business.getVentilation());
						DB_Access.editBusinessVentilationType(businessID, ventilation);
						business = DB_Access.findBusiness(businessID);
						System.out.println("New Ventilation Type: " + business.getVentilation());
					} catch (Exception e) {
					}
					break;
				case 36:
					businessID = Interface.userIDInput(1);
					userID = Interface.userIDInput(0);
					try {
						System.out.println("\nTrying to edit last Record of given businessID:");
						DB_Access.editLastRecordsUserID(businessID, userID);
					} catch (Exception e) {
					}
					break;
				case 37:
					userID = Interface.userIDInput(0);
					try {
						ArrayList<Business> listX = DB_Access.businessesVisited(userID);
						int length = listX.size();
						System.out.println("Number of BuSsinesses visited: " + length);
						System.out.println("Businesses Visited by " + DB_Access.findUser(userID).getLastName() + ":");
						for (Business businessX : listX) {
							System.out.println(businessX.toString());
						}
					} catch (Exception e) {
					}
					break;
				case 38:
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
