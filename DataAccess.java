/*
 * DataAccess
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;

/**
 * Temporary substitute database interaction.
 * This class consists exclusively of static methods and fields.
 * Its purpose is to interact with databases (currently substituted by arraylists).
 *
 * @version 0.1 23 Nov 2020
 * @author Sotiris
 * @author Christos
 * @author Tasos
 * @author
 */
public class DataAccess {
	/** ArrayList of all Businesses */
	public static ArrayList<Business> allBusinesses = new ArrayList<>();
	/** ArrayList of all Persons */
	public static ArrayList<Person> allPersons = new ArrayList<> ();

	/** Search Person object
	 * Static method that looks for, and returns, a Person object.
	 *
	 * @param firstName String representing person's first name
	 * @param lastName String representing person's last name
	 * @return Person A Person type object
	 */
	public static Person searchPerson(String firstName, String lastName) throws NullPointerException {
		for (Person p : allPersons) {
			if (p.getFirstName == firstName && p.getLastName == lastName) {
				return p;
			}
			return null;
		}
	}

	/** Search businesses visited by person.
	 * Static method that looks for the businesses that a person visited.
	 *
	 * @param user Person object
	 * @return ArrayList<Business> List with all businesses visited
	 */
	public static ArrayList<Business> searchBusiness (Person userId) {
		ArrayList<Business> covidStores = new ArrayList<>(); 
		for (Business b : allBusinesses ) {
			for(Recording r : b.recordings) {
				if(Recording.userId==Person.userId) {
					covidStores.add(b) ;
					break;
				}
		    }

	    }
		return covidStores;
	}

	public static ArrayList<Arraylist<Record>> searchPossiblyInfected (String firstName, String lastName) {
		ArrayList<ArrayList<Record>> output = new ArrayList<ArrayList<Record>>();
		ArrayList<Business> infectedBusinesses = searchBusiness(firstName, lastName);
		for (Business b : infectedBusinesses ) {
			for (Record r1 : searchPerson(firstName, lastName, b)) {
				for (Record r2 : b.customers) {
					if (r2.getEntryDate() >= r1.getEntryDate() && r2.getEntryDate < r1.getExitDate || r2.getExitDate > r1.getEntryDate) {
						output.add(r2);
					}
				}
			}
		}
	}
}
	public static Person recordMatching(String UserID) throws NullPointerException {
		for (Person p : allPersons) {
			if (p.getUserID == UserID) {
				return p;
			}
			return null;
		}
	}
