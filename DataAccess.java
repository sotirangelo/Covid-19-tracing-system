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
public static ArrayList<Business> allBusinesses = new ArrayList<>();
	public ArrayList<String> covidlist = new ArrayList<String>();

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

