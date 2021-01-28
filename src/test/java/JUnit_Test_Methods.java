import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.junit.Test;

import data.*;
import data.Record;
/**
 * This class consists exclusively of static methods and fields.
 * Its purpose is to test most of the methods in this project using JUnit.
 *
 * @version 0.1 27 Dec 2020
 * @author Ioannis
 * 
 */

public class JUnit_Test_Methods {

	@Test
	public void test() {
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
					+ "9.  findNewUserID();\n"
					+ "10. inputBusinessType();\n"
					+ "11. getDate();\n"
					+ "12. getExitDate(Date entryDate, int minutesInStore);\n"
					+ "13. createPassword();\n\n"
					+ "database.Connection.java\n\n"
					+ "14. getConnection();\n\n"
					+ "database.Access.java\n\n"
					+ "15. register(Person person);\n"
					+ "16. getUsers();\n"
					+ "17. findUser(UserID);\n"
					+ "18. authenticateUser(String UserID, String password);\n\n"
					+ "19. register(Business business);\n"
					+ "20. getBusinesses();\n"
					+ "21. findBusiness(BusinessID);\n"
					+ "22. authenticateBusiness(String BusinessID, String password);\n"
					+ "23. findNewBusinessID();\n\n"
					+ "24. checkIn(String BusinessID, String UserID, Date Date, Mask MaskType);\n"
					+ "25. checkOut(String BusinessID, String UserID, Date Date);\n"
					+ "26. getRecords(BusinessID);\n\n"
					+ "27. editUserEmail(String, String);\n"
					+ "28. editUserPhoneNumber(String, long);\n"
					+ "29. editUserAgeCategory(String, Age);\n"
					+ "30. editUserPassword(String, String);\n\n"
					+ "31. editBusinessEmail(String, String);\n"
					+ "32. editBusinessPassword(String, String);\n"
					+ "33. editBusinessSpace(String, double);\n"
					+ "34. editBusinessType(String, BusinessType);\n"
					+ "35. editBusinessVentilationType(String, AER);\n"
					+ "36. editLastRecordsUserID(String, String);\n\n"
					+ "37. businessesVisited(String);\n"
					+ "38. getPersonsRecord(Person, Business);\n"					
					+ "39. contactTracing(InfectedPerson person);\n\n"
					+ "40. registerTracingUser(String, String, String, String, long);\n"
					+ "41. authenticateTracingUser(String, String);\n\n");
			int ch;
			do {
				ch = SCNR.nextInt();
				if (ch <= 0 || ch >= 42) {
					System.out.println("Προσπαθήστε ξανά");
				}
			} while (ch <= 0 || ch >= 42);
		
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
					System.out.println("Trying to get new userID:");
					userID = database.Access.findNewUserID();
					System.out.println("New UserID: " + userID);
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
						database.Connect.getConnection();
						System.out.println("CONNECTION SUCCESSFUL\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED:");
						e.printStackTrace();
					}
					break;
				case 15:
					System.out.println("First, create a Person object:");
					Person person = new Person(database.Access.findNewUserID(), Interface.firstNameInput(),
							Interface.lastNameInput(), Interface.emailInput(), Interface.custPhoneNum(),
							Interface.ageGroup(), Interface.createPassword());
					try {
						database.Access.register(person);
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED DURING 'database.Access.register(person)' :");
						e.printStackTrace();
					}
					break;
				case 16:
					System.out.println("Trying to fetch Person objects from Database:");
					try {
						ArrayList<Person> users = database.Access.getUsers();
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
						Person per = database.Access.findUser(userID);
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
						Person pers = database.Access.authenticateUser(userID, pass);
						System.out.println(pers.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 19:
					System.out.println("First, create a Business object:");
					business = new Business(database.Access.findNewBusinessID(), Interface.emailInput(),
							Interface.createPassword(), Interface.businessNameInput(),
							Interface.floorAreaInput(), Interface.heigthInput(), Interface.inputBusinessType(), Interface.ventilationType());
					try {
						database.Access.register(business);
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED DURING 'database.Access.register(business)' :");
						e.printStackTrace();
					}
					break;
				case 20:
					System.out.println("Trying to fetch Business objects from Database:");
					try {
						ArrayList<Business> businesses = database.Access.getBusinesses();
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
						Business bsness = database.Access.findBusiness(businessID);
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
						Business busn = database.Access.authenticateBusiness(businessID, password);
						System.out.println(busn.toString() + "\n");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 23:
					System.out.println("Trying to get new businessID:");
					businessID = database.Access.findNewBusinessID();
					System.out.println("New BusinessID: " + businessID);
					break;
				case 24:
					businessID = Interface.userIDInput(1);
					userID = Interface.userIDInput(0);
					Mask mask = Interface.maskType();
					java.util.Date utilDate = Interface.getDate();
					System.out.println("\nTrying to insert CheckIn into Database:");
					try {
						database.Access.checkIn(businessID, userID, utilDate, mask);
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
						database.Access.checkOut(businessID, userID, sqlDate1);
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED");
						e.printStackTrace();
					}
					break;
				case 26:
					businessID = Interface.userIDInput(1);
					try {
						ArrayList<Record> recordsList = database.Access.getRecords(businessID);
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
						user = database.Access.findUser(userID);
						System.out.println("Old Email: " + user.getEmail());
						database.Access.editUserEmail(userID, email);
						user = database.Access.findUser(userID);
						System.out.println("New Email: " + user.getEmail());
					} catch (Exception e) {
					}
					break;
				case 28:
					userID = Interface.userIDInput(0);
					phoneNum = Interface.custPhoneNum();
					try {
						user = database.Access.findUser(userID);
						System.out.println("Old Phone Number: " + user.getPhoneNumber());
						database.Access.editUserPhoneNumber(userID, phoneNum);
						user = database.Access.findUser(userID);
						System.out.println("New Phone Number: " + user.getPhoneNumber());
					} catch (Exception e) {
					}
					break;
				case 29:
					userID = Interface.userIDInput(0);
					Age age = Interface.ageGroup();
					try {
						user = database.Access.findUser(userID);
						System.out.println("Old Age Group: " + user.getAgeCategory());
						database.Access.editUserAgeCategory(userID, age);
						user = database.Access.findUser(userID);
						System.out.println("New Age Group: " + user.getAgeCategory());
					} catch (Exception e) {
					}
					break;
				case 30:
					userID = Interface.userIDInput(0);
					password = Interface.createPassword();
					try {
						database.Access.editUserPassword(userID, password);
					} catch (Exception e) {
					}
					break;
				case 31:
					businessID = Interface.userIDInput(1);
					email = Interface.emailInput();
					try {
						business = database.Access.findBusiness(businessID);
						System.out.println("Old Email: " + business.getEmail());
						database.Access.editBusinessEmail(businessID, email);
						business = database.Access.findBusiness(businessID);
						System.out.println("New Email: " + business.getEmail());
					} catch (Exception e) {
					}
					break;
				case 32:
					businessID = Interface.userIDInput(1);
					password = Interface.createPassword();
					try {
						business = database.Access.findBusiness(businessID);
						System.out.println("Old Password: " + business.getPassword());
						database.Access.editBusinessPassword(businessID, password);
						business = database.Access.findBusiness(businessID);
						System.out.println("New Password: " + business.getPassword());
					} catch (Exception e) {
					}
					break;
				case 33:
					businessID = Interface.userIDInput(1);
					double space = Interface.floorAreaInput();
					try {
						business = database.Access.findBusiness(businessID);
						System.out.println("Old Floor Area: " + business.getSpace());
						database.Access.editBusinessSpace(businessID, space);
						business = database.Access.findBusiness(businessID);
						System.out.println("New Floor Area: " + business.getSpace());
					} catch (Exception e) {
					}
					break;
				case 34:
					businessID = Interface.userIDInput(1);
					BusinessType businesstype = Interface.inputBusinessType();
					try {
						business = database.Access.findBusiness(businessID);
						System.out.println("Old Business Type: " + business.getBusinessType());
						database.Access.editBusinessType(businessID, businesstype);
						business = database.Access.findBusiness(businessID);
						System.out.println("New Business Type: " + business.getBusinessType());
					} catch (Exception e) {
					}
					break;
				case 35:
					businessID = Interface.userIDInput(1);
					AER ventilation = Interface.ventilationType();
					try {
						business = database.Access.findBusiness(businessID);
						System.out.println("Old Ventilation Type: " + business.getVentilation());
						database.Access.editBusinessVentilationType(businessID, ventilation);
						business = database.Access.findBusiness(businessID);
						System.out.println("New Ventilation Type: " + business.getVentilation());
					} catch (Exception e) {
					}
					break;
				case 36:
					businessID = Interface.userIDInput(1);
					userID = Interface.userIDInput(0);
					try {
						System.out.println("\nTrying to edit last Record of given businessID:");
						database.Access.editLastRecordsUserID(businessID, userID);
					} catch (Exception e) {
					}
					break;
				case 37:
					userID = Interface.userIDInput(0);
					try {
						ArrayList<Business> listX = database.Access.businessesVisited(userID);
						int length = listX.size();
						System.out.println("Number of BuSsinesses visited: " + length);
						System.out.println("Businesses Visited by " + database.Access.findUser(userID).getLastName() + ":");
						for (Business businessX : listX) {
							System.out.println(businessX.toString());
						}
					} catch (Exception e) {
					}
					break;
				case 38:
					break;
				case 39://XXX fix this
					SCNR.nextLine();
					System.out.println("Enter userID, to create InfectedPerson:");
					String x = SCNR.nextLine();
					InfectedPerson y = new InfectedPerson(database.Access.findUser(x), 1);
					System.out.println("Found user: " + y.getFirstName() + " with id: " + x);
					database.Access.addInfected(y);
					System.out.println("Searching for infected");
					ArrayList<InfectedPerson> inf = DataAnalysis.contactTracing(y);
					System.out.println("Search complete");
					System.out.println("List of possibly infected:");
					for (InfectedPerson p : inf) {
						System.out.println(p);
					}
					break;
				case 40:
					password = Interface.createPassword();
					String firstname = Interface.firstNameInput();
					String lastname = Interface.lastNameInput();
					email = Interface.emailInput();
					phoneNum = Interface.custPhoneNum();
					try {
						database.Access.registerTracingUser(password, firstname, lastname, email, phoneNum);
					} catch (Exception e) { }
					break;
				case 41:
					email = Interface.emailInput();
					password = SCNR.next();
					try {
						boolean isVer = database.Access.authenticateTracingUser(email, password);
						System.out.println("User Verified Status: " + isVer);
					} catch (Exception e) { }
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

