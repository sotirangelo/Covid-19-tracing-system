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
	private static int[] ages = {58,62,67,74,21,45,28,53,52,55,79,24,18,59,72,69,61,44,65,40,33,78,53,29,31,49,74,44,49,43,76,60,74,20,69,29,18,79,75,57};
	/** Email array */
	private static String[] emailTypes = {"@gmail.com", "@yahoo.com", "@yahoo.gr", "aueb.gr"} 


	private static String createEmail(String name) {
		int n = new Random().nextInt(names.length);
		String[] emailName = name.split(" ", 2);
		return 	(emailName[0].toLowerCase() + emailType[n]);
	}

	private static int createPhoneNumber(){
		
	}

	private static void createPersons() {
		
	}

	public static void main(String[] args) {

	}
}
