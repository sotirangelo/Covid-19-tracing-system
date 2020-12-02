/*
 * Output
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;
/**
 * Output class
 * This class consists exclusively of static methods and fields.
 * Its purpose is to present DataAccess.java and DataAnalysis.java ArrayLists.
 *
 * @version 0.1 01 Dec 2020
 * @author Ioannis
 * @author
 * @author
 * 
 */

public class Output {
	
	/**
	 * DataAccess/DataAnalyisis output.
	 * Static method, that can be used to present the objects of an ArrayList<ArrayList<Person>>.
	 * 
	 * Does not return a value - prints Person objects of the ArrayList.
	 */
	public static void getOutput(ArrayList<InfectedPerson> infected) {
			for (InfectedPerson ip : infected) {
				System.out.println(ip.toString());
			}
	}
}
