import java.util.Date;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

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
					+ "9.  checkType();\n"
					+ "10. inputBusinessType();\n"
					+ "11. getDate();\n"
					+ "12. getExitDate(Date entryDate, int minutesInStore);\n\n"
					+ "DB_Connection.java\n\n"
					+ "13. getConnection();\n");
			int ch;
			do {
				ch = SCNR.nextInt();
				if (ch <= 0 || ch >= 14) {
					System.out.println("Προσπαθήστε ξανά");
				}
			} while (ch <= 0 || ch >= 14);
		
			switch (ch) {
				case 1:
					double floorArea = Interface.floorAreaInput();
					System.out.println("Τα τετραγωνικά μέτρα που δώσατε είναι: " + floorArea);
					break;
				case 2:
					String firstName = Interface.firstNameInput();
					System.out.println("Το όνομα που δώσατε: " + firstName);
					break;
				case 3:
					String lastName = Interface.lastNameInput();
					System.out.println("Το όνομα που δώσατε: " + lastName);
					break;
				case 4:
					String email = Interface.emailInput();
					System.out.println("Το email που δώσατε: " + email);
					break;
				case 5:
					long phoneNum = Interface.custPhoneNum();
					System.out.println("Ο αριθμός τηλεφώνου που δώσατε: " + phoneNum);
					break;
				case 6:
					Mask maskType = Interface.maskType();
					System.out.println("Ο τύπος μάσκας που δώσατε: " + maskType);
					break;
				case 7:
					Exertion exertionType = Interface.exertionType();
					System.out.println("Το είδος δραστηριότητας καταστήματος που δώσατε: " + exertionType);
					break;
				case 8:
					Age ageGroup = Interface.ageGroup();
					System.out.println("Η κατηγορία ηληκίας που δώσατε: " + ageGroup);
					break;
				case 9:
					Check checkType = Interface.checkType();
					System.out.println("Η κατηγορία Check(In/Out) που δώσατε: " + checkType);
					break;
				case 10:
					BusinessType businessType = Interface.inputBusinessType();
					System.out.println("Η κατηγορία καταστήματος που δώσατε: " + businessType);
					break;
				case 11:
					Date date = Interface.getDate();
					System.out.println("Η ημερομηνία και ώρα που καταγράφθηκε: " + date);
					break;
				case 12:
					System.out.println("Δώστε τα λεπτά που ο πελάτης ήταν στο κατάστημα: ");
					int minutesInStore = SCNR.nextInt();
					Date exitDate = Interface.getExitDate(Interface.getDate(), minutesInStore);
					System.out.println("Η ημερομηνία και ώρα που καταγράφθηκε: " + exitDate);
					break;
				case 13:
					System.out.println("Trying to establish connection with MySQL Database:\n");
					try {
						DB_Connection.getConnection();
						System.out.println("CONNECTION SUCCESSFUL");
					} catch (Exception e) {
						System.out.println("AN ERROR HAS OCCURED:");
						e.printStackTrace();
					}
					break;
			}
			System.out.println("Do you want to try another method?\n"
					+ "Type yes/no");
			String choice = null;
			do {
				choice = SCNR.next();
				if (choice.contentEquals("yes") || choice.contentEquals("no")) {
					if (choice.contentEquals("yes")) {
						exit = false;
					} else {
						exit = true;
					}
				} else {
					System.out.println("Wrong input. Must be yes or no. Yours was: " + choice);
					choice = null;
				}
			} while (choice == null);
		} while (exit == false);
		SCNR.close();
	}
}
