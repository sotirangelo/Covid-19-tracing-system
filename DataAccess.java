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
	public static ArrayList<Person> allPersons = new ArrayList<>();
	/** ArrayList of all Infected */
	public static ArrayList<InfectedPerson> allInfected = new ArrayList<>();

	/** Search Person by Name.
	 * Static method that looks for, and returns, a Person object by using a name.
	 *
	 * @param firstName String representing person's first name
	 * @param lastName String representing person's last name
	 * @return Person A Person type object
	 */
	public static Person searchPersonByName(String firstName, String lastName) throws NullPointerException {
		for (Person p : allPersons) {
			if (p.getFirstName() == firstName && p.getFirstName() == lastName) {
				return p;
			}
			
		}
		return null;
	}
	
	/** Search Person by ID.
	 * Static method that looks for, and returns, a Person object by using a userID String.
	 * 
	 * @param userID String representing userID
	 * @return Person Person type object
	 */
	public static Person searchPersonByID(String userID) throws NullPointerException {
		for (Person p : allPersons) {
			if (p.getUserID().equals(userID)) {
				return p;
			}
			
		}
		return null;
	}
	
	/**
	 * Search Business by ID.
	 * Static method that looks for, and returns, a Business object by using a businessID String.
	 *
	 * @param businessID String representing businessID
	 * @return Business Business type object
	 */
	public static Business searchBusinessByID(String businessID) throws NullPointerException {
		for (Business b : allBusinesses) {
			if (b.getBusinessID().equals(businessID)) {
				return b;
			}
		}
		return null;
	}
	
	/**
	 * Validate UserID Input.
	 * Static method that looks for a specific userID in the static allPersons list.
	 * 
	 * @param userID String representing user ID
	 * @return boolean
	 */
	public static boolean validatePersonID(String userID) {
		for (Person p : allPersons) {
			if (p.getUserID().equals(userID)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validate BusinesID Input.
	 * Static method that looks for a specific BusinessID in the static allBusinesses list.
	 * @param businessID String representing business ID
	 * @return boolean
	 */
	public static boolean validateBusinessID(String businessID) {
		for (Business b : allBusinesses) {
			if (b.getBusinessID().equals(businessID)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Record> searchRecord(Person p, Business b) {
		ArrayList<Record> outputOfSameRecords = new ArrayList<Record>() ;
		for (Record r : b.records) {
			if (r.getUserID().equals(p.getUserID())) {
				outputOfSameRecords.add(r);
			}
		}
		return outputOfSameRecords;
	}

	/** Search businesses visited by person.
	 * Static method that looks for the businesses that a person visited.
	 *
	 * @param user Person object
	 * @return ArrayList<Business> List with all businesses visited
	 */
	public static ArrayList<Business> searchBusiness (String userId) {
		ArrayList<Business> covidStores = new ArrayList<>(); 
		for (Business b : allBusinesses ) {
			for(Record r : b.records) {
				if(r.getUserID().equals(userId)) {
					covidStores.add(b) ;
					break;
				}
		    }

	    }
		return covidStores;
	}

	public static ArrayList<ArrayList<Record>> searchPossiblyInfected (String userID) {
		ArrayList<ArrayList<Record>> output = new ArrayList<ArrayList<Record>>();
		ArrayList<Business> infectedBusinesses = searchBusiness(userID);
		for (Business b : infectedBusinesses ) {
			ArrayList<Record> temp = new ArrayList<Record>();
			for (Record r1 : searchRecord(searchPersonByID(userID), b)) {
				for (Record r2 : b.records) {
					if (r2.getEntryDate().after(r1.getEntryDate()) && r2.getEntryDate().before(r1.getExitDate()) || r2.getExitDate().after(r1.getEntryDate())) {
						temp.add(r2);
					}
				}
			}
			output.add(temp);
		}
		return output;
	}
		public static Person recordMatching(String UserID) throws NullPointerException {
		for (Person p : allPersons) {
			if (p.getUserID() == UserID) {
				return p;
			}
			
		}
		return null;
	}
	
	public static ArrayList<Arraylist<Person>> searchPossiblyInfected (String UserID) {
		ArrayList<ArrayList<Person>> output = new ArrayList<ArrayList<Person>>();
		ArrayList<Business> infectedBusinesses = searchBusiness(UserID);
		for (Business b : infectedBusinesses ) {
			for (Person p1 : searchPerson(UserID, b)) {
				for (Person p2 : b.customers) {
					if (p2.getEntryTime() >= p1.getEntryTime() && p2.getEntryTime < p1.getExitTime || p2.getExitTime > p1.getEntryTime) {
						output.add(p2);
					}
				}
			}
		}
	}
}
