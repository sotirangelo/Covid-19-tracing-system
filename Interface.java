/*
 * Interface
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.Scanner;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Temporary command-line Input and Output.
 * This class consists exclusively of static methods that handle user-interface.
 *
 * @version 0.1 20 Nov 2020
 * @author Sotiris
 * @author Ioannis
 * @author Serafeim
 */
public class Interface {

	/** Temporary scanner Field */
	static Scanner SCNR = new Scanner(System.in); 

	/** Temporary field that formats Date objects in String "dd-MM-yyyy HH:mm:ss" format */
	static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	/** 
	 * Business' total floor area input.
	 * Static method, that prompts the user to specify their business' total floor area in square metres.
	 *
	 * @return double Floor area in square meters.
	 */
	public static double floorAreaInput() {
		System.out.println("Πόσα τετραγωνικά μέτρα είναι το κατάστημά σας;");
	  	double sqm = 0;
	  	while (sqm <= 0) {
			if (SCNR.hasNextDouble())
				sqm = SCNR.nextDouble();
	        	else {
	        		System.out.println("Πρέπει να δώσετε τον αριθμό τετραγωνικών μέτρων του καταστήματός σας. Προσπαθείστε ξανά.");
	            		SCNR.nextLine();
	            		continue;
	        	}
	  	}
	  	return sqm;
  	}
  
	/**
	 * Customer USER ID input.
	 * Static method, that prompts the user to input the customer's USER ID.
	 * 
	 * @return Integer Customer's USER ID.
	 */
  	public static int checkIn() {
		System.out.println("Δώστε το USER ID του πελάτη (πχ. 12345678): ");
	  	int intID =0;
		while (intID < 11111111 || intID > 99999999) { // checks if the given value is an Integer and a valid USER ID (11111111-99999999)
			while (!SCNR.hasNextInt()) {
		        System.out.println("Το USER ID του πελάτη που εισήχθει ήταν λανθασμένο. Προσπαθήστε ξανά (πχ. 12345678) : ");
		        SCNR.next(); 
		    }
		    intID = SCNR.nextInt();
		    if (intID < 11111111 || intID > 99999999) {
		    	System.out.println("Το USER ID του πελάτη που εισήχθει ήταν λανθασμένο. Προσπαθήστε ξανά (πχ. 12345678) : ");
		    }
		}
	  	return intID;
  	}
	
	
	/**
	 * Customer first name input.
	 * Static method, that prompts the user to input the customer's first name.
	 * 
	 * @return String Customer's first name.
	 */
	public static String firstNameInput() {
		System.out.println("Δώστε το όνομα του πελάτη: ");
		String custfirst = SCNR.next();
		return custfirst;
	}
	
	/**
         * Customer last name input.
         * Static method, that prompts the user to input the customer's last name.
         *
         * @return String Customer's last name.
         */
	public static String lastNameInput() {
		System.out.println("Δώστε το επίθετο του πελάτη: ");
		String custlast = SCNR.next();
		return custlast;
	}

	 /**
         * Customer email input.
         * Static method, that prompts the user to input the customer's email address.
         *
         * @return String Customer's email address.
         */
	public static String emailInput() {
		System.out.println("Δώστε το email του πελάτη: ");
		String emailadr = null; 
		while (emailadr == null) { //checks if the given String is an email
			emailadr = SCNR.nextLine();
			Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
	        Matcher mat = pattern.matcher(emailadr);
	        if(mat.matches()){
	            System.out.println("Valid email address");
	        }else{
	        	System.out.println("Το e-mail πελάτη που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
	        	emailadr = null;
	        }
		}
		return emailadr;
	}

	/**
         * Customer phone number input.
         * Static method, that prompts the user to input the customer's phone number.
         *
         * @return Int Customer's 10-Digit phone number.
         */
	public static int custPhoneNum() {
		System.out.println("Δώστε το τηλέφωνο του πελάτη (εξαιρουμένου του κωδικού χώρας: +..): ");
		String phonenum = null;
		while (phonenum == null) {
			phonenum = SCNR.next();
			String regexStr = "^[0-9]{10}$"; // 10 digit number
			if (phonenum.matches(regexStr)) { // checks if the given string is a 10 digit number
				System.out.println("Valid phone number");
			} else {
				System.out.println("Το τηλέφωνο πελάτη που εισήχθη ήταν λανθασμένο.Πρέπει να είναι ένας 10ψήφιος αριθμός.Παρακαλώ προσπαθήστε ξανά: ");
				phonenum = null;
			}
		}		
		return Integer.parseInt(phonenum);		
	}

	/**
         * Customer mask type input.
         * Static method, that prompts the user to input the customer's mask type.
         *
         * @return Mask Customer's mask type.
         */
	public static Mask maskType() {
		System.out.println("Εισάγετε το είδος μάσκας του πελάτη: ");
		System.out.println("Αν δεν φοράει μάσκα, εισάγετε 0.");
		System.out.println("Αν φοράει μάσκα τύπου Fabric, εισάγετε 1.");
		System.out.println("Αν φοράει μάσκα τύπου Medical, εισάγετε 2.");
		System.out.println("Αν φοράει μάσκα τύπου Respirator, εισάγετε 3.");
		int custmask = 0;
		boolean checker = false;
		while (checker == false) {
			if (SCNR.hasNextInt()) { // checks if the given value is an integer (0-3)
				custmask = SCNR.nextInt();
				if (custmask >= 0 && custmask <= 3) {
					checker = true;
				} else {
					System.out.println("Το είδος μάσκας του πελάτη που εισήχθει ήταν λανθασμένο. Προσπαθήστε ξανά : ");
					System.out.println("Αν δεν φοράει μάσκα, εισάγετε 0.");
					System.out.println("Αν φοράει μάσκα τύπου Fabric, εισάγετε 1.");
					System.out.println("Αν φοράει μάσκα τύπου Medical, εισάγετε 2.");
					System.out.println("Αν φοράει μάσκα τύπου Respirator, εισάγετε 3.");
				}
			} else {
				System.out.println("Το είδος μάσκας του πελάτη που εισήχθει ήταν λανθασμένο. Προσπαθήστε ξανά : ");
				System.out.println("Αν δεν φοράει μάσκα, εισάγετε 0.");
				System.out.println("Αν φοράει μάσκα τύπου Fabric, εισάγετε 1.");
				System.out.println("Αν φοράει μάσκα τύπου Medical, εισάγετε 2.");
				System.out.println("Αν φοράει μάσκα τύπου Respirator, εισάγετε 3.");
	           	SCNR.nextLine();
	            continue;
	        }
		}
		switch (custmask) {
			case 0:
				return Mask.NONE;
			case 1:
				return Mask.FABRIC;
			case 2:
				return Mask.MEDICAL;
			default:
				return Mask.RESPIRATOR;
		}
	}

	/** Customer's entry date and time.
	 * Static method, representing the customer's date and time of entry.
	 *
	 * @return LocalDateTime Current Date and Time.
	 */
	public static LocalDateTime getEntryDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
		return myDateObj;
	}
	/** Customer's exit date and time.
	 * Static method, representing the customer's date and time of exit.
	 *
	 * @return LocalDateTime Entry Date and Time + Minutes in Store.
	 */
	public static LocalDateTime getExitDate(LocalDateTime entryDate, int minutesInStore) {
		LocalDateTime exitDate = entryDate.plusMinutes(minutesInStore); 
		return exitDate;
	}
	
	/** Customer's type of activity.
         * Static method, that prompts the user to input the customer's exertion level.
         *
         * @return Exertion Customer's exertion level.
         */
	public static Exertion exertionType() { //input of customer's exertion type//
		System.out.println("Εισάγετε το είδος δραστηριότητας του πελάτη: ");
		System.out.println("Αν κάθεται σε τραπέζι ή σε πάγκο, εισάγετε 0.");
		System.out.println("Αν είναι όρθιος ή περπατάει, εισάγετε 1.");
		int custExertion = 0;
		boolean aa = false;
		while (aa == false) {
			try {
            	            custExertion = SCNR.nextInt();
            	            if (custExertion == 0 || custExertion == 1) {
            		        aa = true;	 
            	            } else {
			        System.out.println("Το είδος δραστηριότητας που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
		            }
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το είδος δραστηριότητας που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
			}
		}
		if (custExertion == 0) {
            return Exertion.RESTING;
		} else {
            return Exertion.STANDING;
		}
	}
}
