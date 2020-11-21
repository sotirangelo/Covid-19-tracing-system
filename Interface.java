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
	
	public static String firstName() {
		System.out.println("Δώστε το όνομα του πελάτη: ");
		String custfirst= SCNR.nextLine();
		return custfirst;
	}
	
	public static String lastName() {
		System.out.println("Δώστε το επίθετο του πελάτη: ");
		String custlast = SCNR.nextLine();
		return custlast;
	}
	
	public static String custEmail() {
		System.out.println("Δώστε το email του πελάτη: ");
		String emailadr = SCNR.nextLine();
		return emailadr;
	}
	
	public static String custPhoneNum() {
		System.out.println("Δώστε το τηλέφωνο του πελάτη (εξαιρουμένου του κωδικού χώρας: +..): ");
		String phonenum = SCNR.nextLine();
		if (phonenum.length() != 10) {
			System.out.println("Λανθασμένη εισαγωγή, παρακαλώ προσπαθήστε ξανά.")
		}else {
			break;
		}
			
	}
	
	public static Mask maskType() {
		System.out.println("Εισάγετε το είδος μάσκας του πελάτη: ");
		System.out.println("Αν δεν φοράει μάσκα, εισάγετε 0.");
		System.out.println("Αν φοράει μάσκα τύπου Fabric, εισάγετε 1.");
		System.out.println("Αν φοράει μάσκα τύπου Medical, εισάγετε 2.");
		System.out.println("Αν φοράει μάσκα τύπου Respirator, εισάγετε 3.");
		int custmask = SCNR.nextLine();
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

