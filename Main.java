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
	static Scanner sc = new Scanner(System.in);
	/** Name array */
	private static String[] names = {"Nishat Tyson","Warren Blankenship","Lennox Fraser","Sahra Stubbs","Robson Kidd","Sebastian Patton","Lilly-Mae Boyce","Luca Lin","Eva Firth","Liyah Dean","Qasim Mckinney","Jago Sexton","Marvin Ashley","Dan Snider","Dylan Witt","Walter Barr","Abiha Cline","Bruce Bloom","Kierran Marin","Leja Webb","Rufus Holt","Kelly Hale","Jac Pratt","Maia Hobbs","Tamera Novak","Christopher Duran","Timur Cunningham","Marguerite Lester","Norman Frost","Jesus Milner","Isobelle Farrell","Bessie Hammond","Emelie Mueller","Sania Mckay","Mahek Shelton","Codey Martins","Garin Mccarthy","Mustafa James","Bobby Richard","Colm Richardson"};
	/** Age array */
	private static int[] ageArray = {58,62,67,74,21,45,28,53,52,55,79,24,18,59,72,69,61,44,65,40,33,78,53,29,31,49,74,44,49,43,76,60,74,20,69,29,18,79,75,57};
	/** Email array */
	private static String[] emailTypes = {"@gmail.com", "@yahoo.com", "@yahoo.gr", "aueb.gr"} 


	private static String createEmail(String name) {
		int n = new Random().nextInt(names.length);
		String[] emailName = name.split(" ", 2);
		return 	(emailName[0].toLowerCase() + emailType[n]);
	}

	private static long createPhoneNumber(){
		return ((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
	}

	private static void createPersons() {
		//TODO Fix Age.java
		for (String s : names) {
			String[] name = s.split(" ", 2);
			int n = new Random().nextInt(ageArray.length);
			Person p = new Person(name[0], name[1], createEmail(name[0]), createPhoneNumber(), ageArray[n]);
			DataAccess.allPersons.add(p);
		}	
	}

	public static void createBusinesses() {
		//TODO Fix Business.java
		DataAccess.allBusinesses.add(new Business("Average Music Shop", 92.9, BusinessType.STORE));
		DataAccess.allBusinesses.add(new Business("Average Pharmacy", 139.4, BusinessType.STORE));
		DataAccess.allBusinesses.add(new Business("Average Office", 69.6, BusinessType.OFFICE));
	}

		public static void message() {
		System.out.println("\n~~~~~~~~~~ Μενού Επιλογών ~~~~~~~~~~\n");
		System.out.println("1. Προσθήκη\n");
		System.out.println("2. Προβολή\n");
		System.out.println("3. Έξοδος\n");
		System.out.print("Εισάγετε επιλογή [1-3] : ");
	}

	public static void main(String[] args) {
		//Pregenerated stuff
		createPersons();
		createBusinesses();


	}
}
