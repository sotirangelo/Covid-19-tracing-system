/*
 * Main
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Presentation testing class.
 * Temporary class, meant to be used in testing and presenting our work thus far.
 *
 * @version 0.1 30 Nov 2020
 * @author Sotiris
 * @author
 */
public class Main {
	/** Temporary Scanner Field */
	static Scanner input = new Scanner(System.in);
	/** Name array */
	private static String[] names = {"Nishat Tyson","Warren Blankenship","Lennox Fraser","Sahra Stubbs","Robson Kidd","Sebastian Patton","Lilly-Mae Boyce","Luca Lin","Eva Firth","Liyah Dean","Qasim Mckinney","Jago Sexton","Marvin Ashley","Dan Snider","Dylan Witt","Walter Barr","Abiha Cline","Bruce Bloom","Kierran Marin","Leja Webb","Rufus Holt","Kelly Hale","Jac Pratt","Maia Hobbs","Tamera Novak","Christopher Duran","Timur Cunningham","Marguerite Lester","Norman Frost","Jesus Milner","Isobelle Farrell","Bessie Hammond","Emelie Mueller","Sania Mckay","Mahek Shelton","Codey Martins","Garin Mccarthy","Mustafa James","Bobby Richard","Colm Richardson"};
	/** Age array */
	private static int[] ageArray = {58,62,67,74,21,45,28,53,52,55,79,24,18,59,72,69,61,44,65,40,33,78,53,29,31,49,74,44,49,43,76,60,74,20,69,29,18,79,75,57};
	/** Email array */
	private static String[] emailTypes = {"@gmail.com", "@yahoo.com", "@yahoo.gr", "aueb.gr"};
	/** IDs array */
	private static String[] IDArray = {"11348769","71991378","51386048","75317950","51446950","03820569","33801413","55712048","57962236","41704022","86016157","88973302","16973115","43021262","04379412","34426988","94961276","89655789","81141403","21040527","14783422","56940248","33991010","45505100","57233054","76344125","43398894","61865082","87024759","95967039","23504093","23902919","04407493","40132769","50927888","82742930","33462402","20724408","31640261","74841963"};
	/** Entry Hours array */
	private static int[] entryHour = {18,19,19,20,18,18,19,20,18,19,18,20,18,20,20,18,19,19,18,18,20,19,20,19,19,20,18,18,19,19,19,19,18,19,20,18,20,20,19,19};
	/** Entry Minutes array */
	private static int[] entryMinute = {55,55,38,19,31,20,19,32,37,46,12,42,5,11,43,52,23,32,44,20,6,23,55,44,13,4,12,45,14,54,21,58,38,40,53,11,18,14,52,15};
	/** Exit Hours array */
	private static int[] exitHour = {20,19,20,20,19,21,19,20,20,19,21,20,20,20,21,20,20,20,19,20,18,19,20,18,19,19,20,21,19,20,20,20,18,20,20,21,18,19,20,18};
	/** Exit Minutes array */
	private static int[] exitMinute = {0,27,30,27,39,0,23,52,0,0,0,0,0,0,0,35,22,0,0,0,17,0,0,18,0,44,32,0,0,18,28,27,15,25,12,0,37,0,39,27};

	/** Generate Email String.
	 * Static method that uses name String to generate an email.
	 *
	 * @param name String representing Person's name
	 * @return email String representing Person's email
	 */
	private static String createEmail(String name) {
		int n = new Random().nextInt(emailTypes.length);
		String[] emailName = name.split(" ", 2);
		return 	(emailName[0].toLowerCase() + emailTypes[n]);
	}

	/** Generate Phone Number.
	 * Static method that generates a 10-digit number.
	 *
	 * @return phoneNumber Long representing 10-digit phone number
	 */
	private static long createPhoneNumber(){
		return ((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
	}
	
	private static Age convertAge(int age) {
		if (age >= 1 && age <= 18 ) {
			return Age.UNDERAGE;
		} else if (age <= 30) {
			return Age.EIGHTEEN;
		} else if (age <= 60) {
			return Age.THIRTY;
		} else {
			return Age.SIXTY;
		}
	}

	/** Fill Person ArrayList.
	 * Static method that creates Person objects and adds them to the static arraylist in DataAccess.java.
	 * This method needs pregenerated data to create Person objects. Pregenerated data is provided by existing arrays and methods.
	 */
	private static void generatePersons() {
		for ( int i = 0 ; i < names.length ; i++) {
			String[] name = names[i].split(" ", 2);
			int n = new Random().nextInt(ageArray.length);
			Person p = new Person(IDArray[i], name[0], name[1], createEmail(name[0]), createPhoneNumber(), convertAge(ageArray[n]) );
			DataAccess.allPersons.add(p);
		}	
	}

	/** Fill Business ArrayList.
	 * Static method that creates Business objects and adds them to the static arraylist in DataAccess.java.
	 */
	private static void generateBusinesses() {
		//TODO Fix Business.java
		DataAccess.allBusinesses.add(new Business("001", "Average Music Shop", 92.9, BusinessType.STORE));
		DataAccess.allBusinesses.add(new Business("002", "Average Pharmacy", 139.4, BusinessType.STORE));
		DataAccess.allBusinesses.add(new Business("003", "Average Office", 69.6, BusinessType.OFFICE));
	}
	/** Fill Records ArrayLists in Business objects.
	 * Static method that creates Record objects and adds them to the adjacent
	 * records arraylists in every pregenerated Business object.
	 */
	@SuppressWarnings("deprecation")
	public static void generateRecords() {

		for (Person p : DataAccess.allPersons) {
			int store = new Random().nextInt(3);
		      	String businessID = "001";
			switch (store) {
				case 0:
					businessID = "001";
					break;
				case 1:
					businessID = "002";
					break;
				case 2:
					businessID = "003";
					break;
			}
			int mask = new Random().nextInt(4);
			Mask maskType = Mask.NONE;
		switch (mask) {
			case 0:
				maskType = Mask.NONE;
				break;
			case 1:
				maskType = Mask.FABRIC;
				break;
			case 2:
				maskType = Mask.MEDICAL;
				break;
			case 3:
				maskType = Mask.RESPIRATOR;
				break;
		}
			int index = DataAccess.allPersons.indexOf(p);	
			Date entryTime = new Date(2020,3,29,entryHour[index],entryMinute[index]);
			Date exitTime = new Date(2020,3,29,exitHour[index],exitMinute[index]);
			Record r = new Record(businessID, p.getUserID(), maskType, entryTime, exitTime); 
			Business b = DataAccess.searchBusinessByID(businessID);
			b.records.add(r);
		}

	
	}

	/** Menu message.
	 * Static method, printing the menu each time it is called upon.
	 */
	private static void menu() {
		System.out.println("\n~~~~~~~~~~ Μενού Επιλογών ~~~~~~~~~~\n");
		System.out.println("1. Πελάτης\n");
		System.out.println("2. Επιχείρηση\n");
		System.out.println("3. ΕΟΔΥ\n");
		System.out.println("4. Έξοδος\n");
		System.out.print("Εισάγετε επιλογή [1-4] : ");
	}

	/** Exit Prompt.
	 * Static method that prompts the user to decide whether to exit the program or continue using it.
	 */
	private static void exitOrNot() {
		System.out.println("\n1. Έξοδος");
		System.out.println("2. Συνέχεια");
		System.out.print("Εισάγετε επιλογή [1-2] : ");
		int answer;
		do {
			answer = input.nextInt();
			if (answer != 1 && answer != 2)
				System.out.println("Παρακαλώ, εισάγετε μια έγκυρη επιλογή...");
		} while (answer != 1 && answer != 2);
		if ( answer == 1)
			System.exit(0);
		else {
			return;
		}

	}

	/**
	 * Create Person Object.
	 * Static method that creates a Person object, using Interface.java to prompt
	 * the user to enter the required information.
	 */
	private static void createNewPerson() {
		String ID = Interface.userIDInput();
		String firstName = Interface.firstNameInput();
		String lastName = Interface.lastNameInput();
		String email = Interface.emailInput();
		long phoneNumber = Interface.custPhoneNum();
		Age ageCategory = Interface.ageGroup();
		Person p = new Person(ID, firstName, lastName, email, phoneNumber, ageCategory);
		DataAccess.allPersons.add(p);	
		System.out.println("\nΕπιτυχής εγγραφή πελάτη");
		
	}
	/**
	 * Create Business Object.
	 * Static method that creates a Business Object, using Interface.java to prompt
	 * the user to enter the required information.
	 */
	private static void createNewBusiness() {
		System.out.println("\nΌνομα επιχείρησης:");
		String businessName = input.nextLine();
		String businessID = "004"; //TODO
		double floorArea = Interface.floorAreaInput();
		Business b = new Business(businessID, businessName, floorArea, Interface.inputBusinessType());
		DataAccess.allBusinesses.add(b);
		System.out.println("\nΕπιτυχής εγγραφή επιχείρησης");
	}
	
	/** Create Record Object.
	 * Static method that creates a Record object, using Interface.java
	 * to prompt the user to enter the required information.
	 */
	private static void createNewRecord() {	
		input.nextLine();
		String businessID;
		boolean flag = false;
		do  {
			System.out.println("\nID επιχείρησης:");
			businessID = input.nextLine();
			flag = DataAccess.validateBusinessID(businessID);
			if (flag == false) {
				System.out.println("\nΤο ID δεν βρέθηκε");
				exitOrNot();
			}
		} while(flag == false);
		String userID;
		flag = false;
        do  {
        	System.out.println("\nID πελάτη:");
        	userID = input.nextLine();
        	flag = DataAccess.validatePersonID(userID);
        	if (flag == false) {
        		System.out.println("\nΤο ID δεν βρέθηκε");
        		exitOrNot();
        	}
        } while(flag == false);
		Mask maskType = Interface.maskType();
		Date entryDate = Interface.getDate();
		System.out.println("\nΏρα διαμονής στο χώρο της επιχείρησης:");
		int time = input.nextInt();
		input.nextLine();
		Date exitDate = Interface.getExitDate(entryDate, time);
		Business b = DataAccess.searchBusinessByID(businessID);
		Record r = new Record(businessID, userID, maskType, entryDate, exitDate);
		b.records.add(r);
		System.out.println("\nΕπιτυχής καταγραφή επίσκεψης");
	}

	public static void createNewInfected() {
		String infectedID;
		boolean flag = false;
		do  {
			infectedID = Interface.userIDInput();
			System.out.println(infectedID);
			flag = DataAccess.validatePersonID(infectedID);
                        if (flag == false) {
                                System.out.println("\nΤο ID δεν βρέθηκε");
                                exitOrNot();
                        }
		} while (flag == false);
		Person tempPerson = DataAccess.searchPersonByID(infectedID);
		InfectedPerson tempInfected = new InfectedPerson(infectedID, tempPerson.getFirstName(), tempPerson.getLastName(), tempPerson.getEmail(), tempPerson.getPhoneNumber(), tempPerson.getAgeCategory()); 
		DataAccess.allInfected.add(tempInfected);
		System.out.println("\nΕπιτυχής καταγραφή κρούσματος.");
		return;
		
	}

	public static void showResults() {
		InfectedPerson infected = DataAccess.allInfected.get(DataAccess.allInfected.size()-1);
		String infectedID = infected.getUserID();
		ArrayList<InfectedPerson> moreInfected = DataAnalysis.infectionScores(infectedID);
		System.out.print(infected);
		System.out.println("\n~~~~~~~~~~ Πιθανά Κρούσματα ~~~~~~~~~~\n");
		Output.getOutput(moreInfected);
		System.out.println();
		return;		
	}

	/** Choice 1 method.
	 * Static method to be executed, if user selects the first menu option.
	 */
	private static void choice1() {
		System.out.println("\n1. Δημιουργία λογαριασμού");
                System.out.println("2. Καταγραφή Επίσκεψης");
		System.out.println("3. Επιστροφή στο αρχικό μενού");
                System.out.print("Εισάγετε επιλογή [1-3] : ");
                int answer;
                do {
                        answer = input.nextInt();       
                        if (answer < 1 && answer > 3)
                                System.out.println("Παρακαλώ, εισάγετε μια έγκυρη επιλογή...");
                } while (answer < 1 && answer > 3);
                input.hasNextLine();
		switch(answer) {
			case 1:
				createNewPerson();
				break;
			case 2:
				createNewRecord();
				break;
			case 3:
				return;
		}

	}	

	/** Choice 2 method.
         * Static method to be executed, if user selects the second menu option.
         */
        private static void choice2() {
                System.out.println("\n1. Δημιουργία λογαριασμού επιχείρησης");
                System.out.println("2. Επιστροφή στο αρχικό μενού");
                System.out.print("Εισάγετε επιλογή [1-2] : ");
                int answer;
                do {
                        answer = input.nextInt();
                        if (answer != 1 && answer != 2)
                                System.out.println("Παρακαλώ, εισάγετε μια έγκυρη επιλογή...");
                } while (answer != 1 && answer != 2);
                input.hasNextLine();
                switch(answer) {
                        case 1:
                                createNewBusiness();
                                break;
                        case 2:
				return;
		}

        }

	/** Choice 3 method.
         * Static method to be executed, if user selects the third menu option.
         */
        private static void choice3() {
                System.out.println("\n1. Είσοδος Κρούσματος");
                System.out.println("2. Ιχνηλάτηση");
		System.out.println("3. Επιστροφή στο αρχικό μενού");
                System.out.print("Εισάγετε επιλογή [1-3] : ");
                int answer;
                do {
                        answer = input.nextInt();
                        if (answer < 1 && answer > 3)
                                System.out.println("Παρακαλώ, εισάγετε μια έγκυρη επιλογή...");
                } while (answer < 1 && answer > 3);
                input.hasNextLine();
                switch(answer) {
                        case 1:
                                createNewInfected();
                                break;
                        case 2:
				showResults();
                                break;
			case 3:
				return;
                }

        }



	public static void main(String[] args) {
		//Pregenerated stuff
		generatePersons();
		generateBusinesses();
		generateRecords();
		while (true) {
			menu();
			int answer;
			do {
				answer = input.nextInt();
				input.hasNextLine();
				if (answer < 1 || answer > 4)
					System.out.println("Παρακαλώ, εισάγετε μια έγκυρη επιλογή...");
			} while (answer < 1 || answer > 4);
			switch(answer) {
				case 1:
					choice1();
					break;
				case 2:
					choice2();
					break;
				case 3:
					choice3();
					break;
				case 4:
					exitOrNot();
					break;
			}
		}

	}
}
