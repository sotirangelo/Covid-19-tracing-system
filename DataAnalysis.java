/*
 * DataAnalysis
 *
 * Copyright (not) 2020 Javavirus
 */

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
		for (Business b : covidstores) { //covidstores might include same store multiple times
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
/*
	public static double[] calculateErq(InfectedPerson infected, Business store) { //TODO: Add necessary methods/fields
		var erq = new double[];
        	var activity = getActivity(infected, store);
		Record r = DB_Access.getPersonsRecord(infected, store);
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
*/
	public static ArrayList<InfectedPerson> calculatePI(InfectedPerson infected, Business business) { //TODO: Fix necessary stuff
		var records = DB_Access.getRecords(business.getBusinessID());
		ArrayList<InfectedPerson> output = new ArrayList<InfectedPerson>();
		double[] erq = calculateTotalErq(infected, business);
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
	
	public static double[] calculateTotalErq(InfectedPerson infected, Business business) {
		Record covidrecord = DB_Access.getPersonsRecord(infected, business); //ONLY NEED FIRST RECORD (IN CASE OF MULTIPLE)
		double[] totalerq = new double[(int) getBusinessDayDuration(covidrecord).toMinutes()];
		for (int i = 0; i < 0; i++) { //XXX: Fill totalerq with zeros
			totalerq[i] = 0;
		}
		ArrayList<Record> dayrecords = DB_Access.getBusinessDayRecords((Timestamp) covidrecord.getEntryDate());
		for (Record r : dayrecords) {
			try {
				if (DB_Access.isUserIDInfected(r.getUserID())) {
					double[] erq = calculateErq(r);
					for (int i = 0; i < erq.length; i++) {
						totalerq[i] += erq[i];
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalerq;
		
	}
	
	private static double[] calculateErq (Record record) {
		LocalDateTime startTime = convertToLocalDateTime(getStartRecord(record.getEntryDate()).getEntryDate());
		Business business = DB_Access.findBusiness(record.getBusinessID());
		double activity = getActivity(DB_Access.findUser(record.getUserID()), business);
		LocalDateTime recordEntryTime = convertToLocalDateTime(record.getEntryDate());
		LocalDateTime recordExitTime = convertToLocalDateTime(record.getExitDate());
		Duration businessDayDuration = getBusinessDayDuration(record);
		double[] erq = new double[(int) businessDayDuration.toMinutes()];	
		int entry = (int) Duration.between(startTime, recordEntryTime).toMinutes();
		int exit = (int) Duration.between(startTime, recordExitTime).toMinutes();
		for (int counter = 0; counter <= (int) businessDayDuration.toMinutes(); counter++) {
			if (counter < entry) {
				erq[counter] = 0;
			} else if (counter < exit) {
				erq[counter] = (activity / (business.getIVRR() * business.getV())) * (1 - Math.exp((business.getIVRR() * -1) * (counter - entry)));
			} else {
				erq[counter] = erq[counter - 1] * Math.exp((business.getIVRR() * -1) * (counter - exit));
			}
		}
		return erq;
			
	}
	
	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private static Duration getBusinessDayDuration(Record record) {
		Record start = getStartRecord(record.getEntryDate()); //start of business hours
		Record end = getLastRecord(record.getEntryDate()); //end of business hours		
		Business business = DB_Access.findBusiness(start.getBusinessID());
		LocalDateTime startTime= convertToLocalDateTime(start.getEntryDate());//TODO: convertToLocalDateTime(Date dateToConvert)
		LocalDateTime endTime = convertToLocalDateTime(end.getExitDate());
		Duration businessDayDuration = Duration.between(startTime, endTime);
		return businessDayDuration;
	}
	
	private static Record getStartRecord(Date date) {
		ArrayList<Record> dayrecords = DB_Access.getBusinessDayRecords((Timestamp) date);//record1.getEntryDate
		Record start = null;
		for (int i = 0; i < dayrecords.size(); i++) {
			if (i == 0) {
				start = dayrecords.get(i);
			} else {
				if (start.getEntryDate().after(dayrecords.get(i).getEntryDate())) {
					start = dayrecords.get(i);
				}
			}
		}
		return start;
	}
	
	private static Record getLastRecord(Date date) {
		ArrayList<Record> dayrecords = DB_Access.getBusinessDayRecords((Timestamp) date);//record1.getEntryDate
		Record end = null;
		for (int i = 0; i < dayrecords.size(); i++) {
			if (i == 0) {
				end = dayrecords.get(i);
			} else {
				if (end.getExitDate().before(dayrecords.get(i).getExitDate())) {
					end = dayrecords.get(i);
				}
			}
		}
		return end;
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
