/*
 * Main
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.Scanner;
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
	private static String[] emailTypes = {"@gmail.com", "@yahoo.com", "@yahoo.gr", "aueb.gr"} 

	/** Generate Email String.
	 * Static method that uses name String to generate an email.
	 *
	 * @param name String representing Person's name
	 * @return email String representing Person's email
	 */
	private static String createEmail(String name) {
		int n = new Random().nextInt(names.length);
		String[] emailName = name.split(" ", 2);
		return 	(emailName[0].toLowerCase() + emailType[n]);
	}

	/** Generate Phone Number.
	 * Static method that generates a 10-digit number.
	 *
	 * @return phoneNumber Long representing 10-digit phone number
	 */
	private static long createPhoneNumber(){
		return ((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
	}

	/** Fill Person ArrayList.
	 * Static method that creates Person objects and adds them to the static arraylist in DataAccess.java.
	 * This method needs pregenerated data to create Person objects. Pregenerated data is provided by existing arrays and methods.
	 */
	private static void createPersons() {
		//TODO Fix Age.java
		for (String s : names) {
			String[] name = s.split(" ", 2);
			int n = new Random().nextInt(ageArray.length);
			Person p = new Person(name[0], name[1], createEmail(name[0]), createPhoneNumber(), ageArray[n]);
			DataAccess.allPersons.add(p);
		}	
	}

	/** Fill Business ArrayList.
	 * Static method that creates Business objects and adds them to the static arraylist in DataAccess.java.
	 */
	private static void createBusinesses() {
		//TODO Fix Business.java
		DataAccess.allBusinesses.add(new Business("Average Music Shop", 92.9, BusinessType.STORE));
		DataAccess.allBusinesses.add(new Business("Average Pharmacy", 139.4, BusinessType.STORE));
		DataAccess.allBusinesses.add(new Business("Average Office", 69.6, BusinessType.OFFICE));
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

	/** Exit Promt.
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
		String firstName = Interface.firstNameInput();
		String lastName = Interface.lastNameInput();
		String email = Interface.emailInput();
		long phoneNumber = Inteface.custPhoneNum();
		Age ageCategory = Interface.ageGroup();
		Person p = new Person(firstName, lastName, email, phoneNumber, ageCategory);
		DataAccess.allPersons.add(p);	
		
	}
	/**
	 * Create Business Object.
	 * Static method that creates a Business Object, using Interface.java to prompt
	 * the user to enter the required information.
	 */
	private static void createNewBusiness() {
		System.out.println("\nΌνομα επιχείρησης:");
		String businessName = input.nextLine();
		system.out.println();
		double floorArea = Interface.floorAreaInput();
		//TODO getBusinessType -> Interface.java

	}
	
	/** Create Record Object.
	 *
	 */
	private static void createNewRecord() {

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
                        if (answer != 1 && answer != 3)
                                System.out.println("Παρακαλώ, εισάγετε μια έγκυρη επιλογή...");
                } while (answer != 1 && answer != 3);
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

	public static void main(String[] args) {
		//Pregenerated stuff
		createPersons();
		createBusinesses();
		while (true) {
			menu();
			int answer;
			do {
				answer = input.nextInt();
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
