package test;
/*
 * Interface
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.Scanner;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.regex.Pattern;

import data.AER;
import data.Age;
import data.BusinessType;
import data.Exertion;
import data.Mask;

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
	
	/** Temporary field that formats String objects to Date "dd-MM-yyyy HH:mm:ss" format */
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/** 
	 * Business' total floor area input.
	 * Static method, that prompts the user to specify their business' total floor area in square metres.
	 *
	 * @return double Floor area in square meters.
	 */
	public static double heigthInput() {
		System.out.println("Πόσο είναι το ύψος του καταστήματός σας;");
		double h = 0;
		while (h <= 0) {
			if (SCNR.hasNextDouble())
				h = SCNR.nextDouble();
	        	else {
	        		System.out.println("Πρέπει να δώσετε το ύψος του καταστήματός σας. Προσπαθείστε ξανά.");
	            		SCNR.nextLine();
	            		continue;
	        	}
	  	}
	  	SCNR.nextLine();
	  	return h;
  	}
	public static double floorAreaInput() {
		System.out.println("Πόσα τετραγωνικά μέτρα είναι το κατάστημά σας;");
	  	String sqm = null;
	  	double m2 = 0;
	  	while (sqm == null) {
	  		sqm = SCNR.nextLine();
			try {
				m2 = Double.parseDouble(sqm);
				if (m2 <= 0) {
					System.out.println("Πρέπει να δώσετε τον αριθμό τετραγωνικών μέτρων του καταστήματός σας. Προσπαθείστε ξανά.");
					sqm = null;
				}
			} catch (Exception e) {
				System.out.println("Πρέπει να δώσετε τον αριθμό τετραγωνικών μέτρων του καταστήματός σας. Προσπαθείστε ξανά.");
				sqm = null;
			}
	  	}
		return m2;
  	}
  
	/**
	 * USER / BUSINESS ID input.
	 * Static method, that prompts the user to input the user or the business ID.
	 * 
	 * @param Int : if 0 then prints 'User'ID, else it prints 'Business'ID
	 * @return String USER / BUSINESS ID.
	 */
	public static String userIDInput(int i) {
		if (i == 0) {
			System.out.println("Δώστε το ID του πελάτη: ");
		} else {
			System.out.println("Δώστε το ID της επιχείρησης: ");
		}
		String custID = null;
		while (custID == null) {
			custID = SCNR.next();
			if (custID.length() == 8) {
				try {
					Integer.parseInt(custID);
				} catch (NumberFormatException e) {
					System.out.println("Το ID του πελάτη που εισήχθει ήταν λανθασμένο. Προσπαθήστε ξανά (πχ. 12345678) : ");
					custID = null;
				}
			} else {
				System.out.println("Το ID του πελάτη που εισήχθει ήταν λανθασμένο. Προσπαθήστε ξανά (πχ. 12345678) : ");
				custID = null;
			}
		}
		
		return custID;
	}
	
	
	
	/**
	 * Customer Password input.
	 * Static method, that prompts the user to input the customer's Pasword.
	 * 
	 * @return String Customer's Password.
	 */
	public static String createPassword() {
		System.out.print("Please enter a given  password : ");
		String passwordhere = SCNR.next();
	    System.out.print("Please re-enter the password to confirm : ");
	    String confirmhere = SCNR.next();

	    ArrayList<String> errorList = new ArrayList<String>();

	    while (!isValid(passwordhere, confirmhere, errorList)) {
	        System.out.println("The password entered here  is invalid");
	        for (String error : errorList) {
	            System.out.println(error);
	        }
	        System.out.print("Please enter a given  password : ");
	        passwordhere = SCNR.next();
	        System.out.print("Please re-enter the password to confirm : ");
	        confirmhere = SCNR.next();
	    }
	    System.out.println("your password is: " + passwordhere);
	    return passwordhere;
	}
	public static boolean isValid(String passwordhere, String confirmhere, ArrayList<String> errorList) {
	    Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
	    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
	    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
	    errorList.clear();
	    boolean flag=true;
	    if (!passwordhere.equals(confirmhere)) {
	        errorList.add("password and confirm password does not match");
	        flag=false;
	    }
	    if (passwordhere.length() < 8) {
	        errorList.add("Password lenght must have alleast 8 character !!");
	        flag=false;
	    }
	    if (!specailCharPatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have atleast one specail character !!");
	        flag=false;
	    }
	    if (!UpperCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have atleast one uppercase character !!");
	        flag=false;
	    }
	    if (!lowerCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have atleast one lowercase character !!");
	        flag=false;
	    }
	    if (!digitCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have atleast one digit character !!");
	        flag=false;
	    }
	    return flag;
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
		
		System.out.println("Δώστε το email: ");
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		String emailadr;
		Matcher mat;
		
		do {
			emailadr = SCNR.next();
			mat = pattern.matcher(emailadr);
	        if(mat.matches()){
	            System.out.println("Valid email address");
	        } else {
	        	System.out.println("Το e-mail που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: " + emailadr);
	        }
		} while(!mat.matches());
		
		SCNR.reset();
		return emailadr;
	}

	/**
         * Customer phone number input.
         * Static method, that prompts the user to input the customer's phone number.
         *
         * @return Long Customer's 10-Digit phone number.
         */
	public static long custPhoneNum() {
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
		SCNR.nextLine();
		return Long.parseLong(phonenum);		
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
		SCNR.nextLine();
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
	 * @return Date Current Date and Time.
	 */
	public static Date getDate() {
		Date dateNow = new Date();
		return dateNow;
	}
	
	/** Customer's exit date and time.
	 * Static method, representing the customer's date and time of exit.
	 *
	 * @return Date Entry Date and Time + Minutes in Store.
	 */
	public static Date getExitDate(Date entryDate, int minutesInStore) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entryDate);
		cal.add(Calendar.MINUTE, minutesInStore);
		Date newDate = cal.getTime();
		return newDate;
	}
	
	// Converts Date to String. Could be used to create a String type 'Date and Time' that can be sent to the database.
	public static String dateToString(Date date) {
		return dateFormat.format(date);
	}
	
	// Converts String to Date
	public static Date stringToDate(String strDate) {
		Date fdate = null;
		try {
			fdate = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fdate;
	}
	
	/** Customer's type of activity.
         * Static method, that prompts the user to input the customer's exertion level.
         *
         * @return Exertion Customer's exertion level.
         */
	public static Exertion exertionType() { //input of customer's exertion type//
		System.out.println("Εισάγετε το είδος δραστηριότητας πελατών στην επιχειρηση:\n"
				+ "Αν κάθονται και δεν μιλάνε δυνατά, εισάγετε 0.\n"
				+ "Αν κάθονται και μιλάνε δυνατά, εισάγετε 1.\n"
				+ "Αν είναι όρθιοι και δεν μιλάνε δυνατά, εισάγετε 2.\n"
				+ "Αν είναι όρθιοι και μιλάνε δυνατά, εισάγετε 3.\n"
				+ "Αν κινούνται ελαφρώς και δεν μιλάνε δυνατά εισάγετε 4.\n"
				+ "Αν κινούνται ελαφρώς και μιλάνε δυνατά εισάγετε 5.\n"
				+ "Αν κινούνται πολύ ή γυμνάζομται και δεν μιλάνε δυνατά εισάγετε 6.\n"
				+ "Αν κινούνται πολύ ή γυμνάζομται και μιλάνε δυνατά εισάγετε 7.\n\n");
		int custExertion = 0;
		boolean aa = false;
		while (aa == false) {
			try {
            	custExertion = SCNR.nextInt();
                if (custExertion >= 0 && custExertion <= 7) {
                    aa = true;	 
                } else {
                   	System.out.println("Το είδος δραστηριότητας που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
                   	SCNR.nextLine();
		        }
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το είδος δραστηριότητας που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
				SCNR.nextLine();
	            continue;
			}
		}
		SCNR.nextLine();
		switch (custExertion) {
			case 0:
				return Exertion.R_S;
			case 1:
				return Exertion.R_L_S;
			case 2:
				return Exertion.S_S;
			case 3:
				return Exertion.S_L_S;
			case 4:
				return Exertion.L_E_S;
			case 5:
				return Exertion.L_E_L_S;
			case 6:
				return Exertion.H_E_S;
			default:
				return Exertion.H_E_L_S;
		}	
	}
	
	public static AER ventilationType() { //input of business' ventilation type//
		System.out.println("Εισάγετε το είδος εξαερισμού του καταστήματος: ");
		System.out.println("Αν έχετε φυσικό εξαερισμό, εισάγετε 0.");
		System.out.println("Αν έχετε ανοιχτές πόρτες και παράθυρα, εισάγετε 1.");
		int busVentilation = 0;
		boolean aa = false;
		while (aa == false) {
			try {
            	busVentilation = SCNR.nextInt();
                if (busVentilation == 0 || busVentilation == 1) {
                    aa = true;	 
                } else {
                   	System.out.println("Το είδος εξαερισμού που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
		        }
			}catch (java.util.InputMismatchException e) {
				System.out.println("Το είδος εξαερισμού που εισήχθη ήταν λανθασμένο. Παρακαλώ προσπαθήστε ξανά: ");
				SCNR.nextLine();
	            continue;
			}
		}
		SCNR.nextLine();
		if (busVentilation == 0) {
            return AER.NATURAL;
		} else {
            return AER.OPEN;
		}
	}


	/**
         * Customer age group input.
         * Static method, that prompts the user to input the customer's age group.
         *
         * @return Customer's age group.
         */
	public static Age ageGroup() {
		System.out.println("Εισάγετε την ηλικιακή ομάδα στην οποία ανήκει ο πελάτης: ");
		System.out.println("Αν ανήκει στην ηλικιακή ομάδα 1-18, εισάγετε 0.");
		System.out.println("Αν ανήκει στην ηλικιακή ομάδα 19-30, εισάγετε 1.");
		System.out.println("Αν ανήκει στην ηλικιακή ομάδα 31-60, εισάγετε 2.");
		System.out.println("Αν ανήκει στην ηλικιακή ομάδα 61-100, εισάγετε 3.");
		int custage = 0;
		boolean sechecker = false;
		while (sechecker == false) {
			if (SCNR.hasNextInt()) { // checks if the given value is an integer (0-3)
				custage = SCNR.nextInt();
				if (custage >= 0 && custage <= 3) {
					sechecker = true;
				} else {
					System.out.println("Η ηλικιακή ομάδα που εισήχθη ήταν λανθασμένη . Προσπαθήστε ξανά : ");
					System.out.println("Αν ανήκει στην ηλικιακή ομάδα 1-18, εισάγετε 0.");
					System.out.println("Αν ανήκει στην ηλικιακή ομάδα 19-30, εισάγετε 1.");
					System.out.println("Αν ανήκει στην ηλικιακή ομάδα 31-60, εισάγετε 2.");
					System.out.println("Αν ανήκει στην ηλικιακή ομάδα 61-100, εισάγετε 3.");
				}
			}
	      }
		
		SCNR.nextLine();
		switch (custage) {
			case 0:
				return Age.UNDERAGE;
			case 1:
				return Age.EIGHTEEN;
			case 2:
				return Age.THIRTY;
			default:
				return Age.SIXTY;
		}
		
	}
	

	/**
	 * Business name input.
	 * Static method, that prompts the user to input the business name.
	 * 
	 * @return String Business name.
	 */
	public static String businessNameInput() {
		System.out.println("Δώστε το όνομα της επιχείρησης: ");
		String custfirst = SCNR.nextLine();
		return custfirst;
	}
	
	/**
     * Business type input.
     * Static method, that prompts the user to input the business' type.
     *
     * @return BusinessType Business' type.
     */
	public static BusinessType inputBusinessType() {
		System.out.println("Εισάγετε το είδος δραστηριότητας της επιχείρησης: ");
		System.out.println("Αν είναι εστιατόριο, εισάγετε 0.");
		System.out.println("Αν είναι bar, εισάγετε 1.");
		System.out.println("Αν είναι γραφείο, εισάγετε 2.");
		System.out.println("Αν είναι κατάστημα λιανικής, εισάγετε 3.");
		System.out.println("Αν είναι market, εισάγετε 4.");
		int custbusinesstype = 0;
		boolean thichecker = false;
		while (thichecker == false) {
			if (SCNR.hasNextInt()) { // checks if the given value is an integer (0-4)
				custbusinesstype = SCNR.nextInt();
				if (custbusinesstype >= 0 && custbusinesstype <= 4) {
					thichecker = true;
				} else {System.out.println("Εισάγετε το είδος δραστηριότητας της επιχείρησης: ");
					System.out.println("Αν είναι εστιατόριο, εισάγετε 0.");
					System.out.println("Αν είναι bar, εισάγετε 1.");
					System.out.println("Αν είναι γραφείο, εισάγετε 2.");
					System.out.println("Αν είναι κατάστημα λιανικής, εισάγετε 3.");
					System.out.println("Αν είναι market, εισάγετε 4.");
				}
			} else {
				System.out.println("Εισάγετε το είδος δραστηριότητας της επιχείρησης: ");
				System.out.println("Αν είναι εστιατόριο, εισάγετε 0.");
				System.out.println("Αν είναι bar, εισάγετε 1.");
				System.out.println("Αν είναι γραφείο, εισάγετε 2.");
				System.out.println("Αν είναι κατάστημα λιανικής, εισάγετε 3.");
				System.out.println("Αν είναι market, εισάγετε 4.");
	           	SCNR.nextLine();
	            continue;
	        }
		}
		switch (custbusinesstype) {
			case 0:
				return BusinessType.RESTAURANT;
			case 1:
				return BusinessType.BAR;
			case 2:
				return BusinessType.OFFICE;
			case 3: 
				return BusinessType.STORE;
			default:
				return BusinessType.MARKET;
		}
	}
}
