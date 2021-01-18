/*
 * DataAnalysis
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;

/**
 * Analysis of data (temporarily from arraylists).
 * This class consists exclusively of static methods and fields.
 * Its purpose is to analyze data taken from the embedded database (currently substituted by arraylists).
 *
 * @version 0.1 23 Nov 2020
 * @author Sotiris
 * @author Nefeli
 * @author
 * @author
 */
public class DataAnalysis {
	
	public static ArrayList<InfectedPerson> contactTracing(InfectedPerson infected) {
		ArrayList<InfectedPerson> allInfected = new ArrayList<InfectedPerson>();
		ArrayList<Business> covidstores = DB_Access.businessesVisited(infected.getUserID());
		for (Business b : covidstores) {
			allInfected.addAll(calculatePI(infected, b));
		}
		return allInfected;
	}
	
	public static double getActivity(Person p, Business b) {
		if (p instanceof InfectedPerson) {
			if (p.isEmployee()) {
				return b.getInfectedEmpExertion();	
			} else {
				return b.getInfectedCustExertion();
			}
		} else {
			if (p.isEmployee()) {
				return b.getHealthyEmpExertion();
			} else {
				return b.getHealthyCustExertion();
			}
		}
	}

	public static double[] calculateErq(InfectedPerson infected, Business store) { //TODO: Add necessary methods/fields
		var erq = new double[];
        	var activity = getActivity(infected, store);
		Record r = getPersonsRecord(infected, store);
		int operationMinutes = getOperationMinutes(); //TODO: For each business (start record & end record)
		int infectedEntry; //TODO
		int infectedExit; //TODO
		double IVRR;
		double V;
		for (i = 0; i < operationMinutes ; i++) {
			if (i < infectedEntry) {
				erq[i] = 0;
			} else if (i <= infectedExit) {
				erq[i] = (activity.getErq() / (IVRR * V)) * (1 - Math.exp((IVRR * -1) * (i - infectedEntry)));
			} else {
				erq[i] = erq[i - 1] * Math.exp((IVRR * -1) * (i - infectedExit));
			}
		}
	}
	
	public static ArrayList<InfectedPerson> calculatePI(InfectedPerson infected, Business business) { //TODO: Add necessary methods/fields
		var records = DB_Access.getRecords(business.getBusinessID());
		ArrayList<InfectedPerson> output = new ArrayList<InfectedPerson>();
		double[] erq = calculateErq(infected, business);
		for (Record r : records) { //TODO: Get only useful records
			double p = 0;
			for (int i = r.getEntry() ; i < erq.length ; i++) {
				if (i <= r.getExit()) {
					p += erq[i] * ((i / 60) - ((i - 1) / 60));		
				}
			}
			p *= business.getIR();
			if (p > 0) {
				output.add((InfectedPerson) r.getPerson()); //TODO: Fix Infected Person casing
			}
		}
	}
	
/*
	public static ArrayList<InfectedPerson> infectionScores(String userID) {
		ArrayList<InfectedPerson> ip = new ArrayList<InfectedPerson>();
		ArrayList<ArrayList<Record>> tasosList = DataAccess.searchPossiblyInfected(userID);
		for (ArrayList<Record> a_r : tasosList) {
			for (Record r : a_r) {
				double score = 0;
				score += r.getMaskType().getEfficiency();
				Person p = DataAccess.recordMatching(r.getUserID());
				score += p.getAgeCategory().getVulnerability();
				InfectedPerson temp = new InfectedPerson(p.getUserID(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhoneNumber(), p.getAgeCategory()); 
				temp.setScore(score);
				ip.add(temp);

			}

		}
		//Collections.sort(ip);
		return ip;
	}
*/

}
