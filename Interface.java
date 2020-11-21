/*
 * Interface
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.Scanner;

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
	/** Scanner Field */
	static Scanner SCNR = new Scanner(System.in); 
  	
	/** 
	 * Business total floor area input.
	 * Static method, that prompts the user to specify their business' total floor area in square metres. Returns double value.
	 */
	public static double setShopSqm() {
		System.out.println("Πόσα τετραγωνικά μέτρα είναι το κατάστημά σας;");
	  	double sqm = 0;
	  	while (sqm <= 0) {
			if (SCNR.hasNextDouble())
				sqm = SCNR.nextDouble();
	        	else {
	        		System.out.println("Πρέπει να δώσετε τον αριθό τετραγωνικών μέτρων του καταστήματός σας. Προσπαθείστε ξανά.");
	            		SCNR.nextLine();
	            		continue;
	        	}
	  	}
	  	return sqm;
  	}
  
  	public static int checkIn() { //check the USER ID (11111111-99999999) - not working 100%
		System.out.println("Δώστε το USER ID του πελάτη (12345678): ");
	  	String id = SCNR.next();
	  	int intID =-1;
	  	while (intID < 11111111 || intID > 99999999) {
			try {
				intID = Integer.parseInt(id);
		  	}
		  	catch (Exception y) {
				System.out.println("Το USER ID του πελάτη που δώσατε ήταν λάθος. Πρέπει να έχει μορφή 12345678. Προσπαθήστε ξανά: ");
		  	}
		  	if (intID < 11111111 || intID >99999999) {
			  	id = SCNR.nextLine();
		  	}
	  	}
	  	return intID;
  	}
	
	public static String firstName() { //input of customer's first name//
		System.out.println("Δώστε το όνομα του πελάτη: ");
		String custfirst= SCNR.nextLine();
		while (true) {
			try {
            			return SCNR.nextString();
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το όνομα πελάτη που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
            			SCNR.nextLine();
			}
		}
		return custfirst;
	}
	
	public static String lastName() { //input of customer's last name//
		System.out.println("Δώστε το επίθετο του πελάτη: ");
		String custlast = SCNR.nextLine();
		while (true) {
			try {
            			return SCNR.nextString();
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το επίθετο πελάτη που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
            			SCNR.nextLine();
			}
		}
		
		return custlast;
	}
	
	public static String custEmail() { //input of customer's e-mail//
		System.out.println("Δώστε το email του πελάτη: ");
		String emailadr = SCNR.nextLine();
		while (true) {
			try {
            			return SCNR.nextString();
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το e-mail πελάτη που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
            			SCNR.nextLine();
			}
		}
		return emailadr;
	}
	
	public static String custPhoneNum() { //input of customer's phone number, return type may need changes//
		System.out.println("Δώστε το τηλέφωνο του πελάτη (εξαιρουμένου του κωδικού χώρας: +..): ");
		String phonenum = SCNR.nextLine();
		while (true) {
			try {
            			return SCNR.nextString();
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το τηλέφωνο πελάτη που εισήχθη ήταν λανθασμένο.Πρέπει να είναι ένας 10ψήφιος αριθμός.Παρακαλώ προσπαθήστε ξανά: ");
            			SCNR.nextLine();
			}
		}		
		if (phonenum.length() != 10) {
			System.out.println("Λανθασμένη εισαγωγή, παρακαλώ προσπαθήστε ξανά.")
		}else {
			break;
		}
			
	}
	
	public static Mask maskType() { //input of customer's mask type//
		System.out.println("Εισάγετε το είδος μάσκας του πελάτη: ");
		System.out.println("Αν δεν φοράει μάσκα, εισάγετε 0.");
		System.out.println("Αν φοράει μάσκα τύπου Fabric, εισάγετε 1.");
		System.out.println("Αν φοράει μάσκα τύπου Medical, εισάγετε 2.");
		System.out.println("Αν φοράει μάσκα τύπου Respirator, εισάγετε 3.");
		int custmask = SCNR.nextLine();
		while (true) {
			try {
            			return SCNR.nextMask();
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το είδος μάσκας που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
            			SCNR.nextLine();
			}
		}
		switch (custmask) {
			case 0:
				return NONE;
				break;
			case 1:
				return FABRIC;
				break;
			case 2:
				return MEDICAL;
				break;
			case 3:
				return RESPIRATOR;
				break;
		}
	}
	
	
		
	
	
	
	
}

