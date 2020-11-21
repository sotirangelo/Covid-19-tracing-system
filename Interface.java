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
static Scanner SCNR = new Scanner(System.in); 
  public static double setShopSqm() { //check the value for the square meters of the shop
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
  
}

