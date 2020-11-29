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
 * @author
 * @author
 */
public class DataAccess {
	/** ArrayList of all Businesses */
	public static ArrayList<Business> allBusinesses = new ArrayList<>();
        public ArrayList<Person> covidlist = new ArrayList<>();	
	/** Search businesses visited by person.
	 * Static class that looks for the businesses that a person visited.
	 *
	 * @param user Person object
	 * @return ArrayList<Business> List with all businesses visited
	 */
	public static ArrayList<Business> searchBusiness (Person user) {
		ArrayList<Business> covidStores = new ArrayList<>();
		for (Business element : allBusinesses ) {
			for(Person e : element.customers) {
				if(e.contains(user)) {
					covidStores.add(element) ;
					break;
				}
		    }

	    }
		return covidStores;
	}
}

