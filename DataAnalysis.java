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
		ArrayList<Business> covidstores = removeDuplicateBusinesses(DB_Access.businessesVisited(infected.getUserID()));
		for (Business b : covidstores) {
			System.out.println(infected.getFirstName() + " has been to: " + b.getName());
			ArrayList<InfectedPerson> temp = calculatePI(infected, b);
			allInfected.addAll(temp);
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
	public static ArrayList<InfectedPerson> calculatePI(InfectedPerson infected, Business business) {
		ArrayList<InfectedPerson> infectedPeople = new ArrayList<InfectedPerson>();
		Record covidrecord = DB_Access.getPersonsRecord(infected, business); //ONLY NEED FIRST RECORD (IN CASE OF MULTIPLE)
		var records = DB_Access.getBusinessDayRecords((Timestamp) covidrecord.getEntryDate());
		double[] erq = calculateTotalErq(covidrecord);
		System.out.println("Where he came into contact with: ");
		for (Record r : records) { // Iterate day's records...
			if (DB_Access.isUserIDInfected(r.getUserID()) == false) { //...ignoring already infected
				double p = 0;
				for (int i = getEntryMinute(r) ; i < erq.length ; i++) {
					if (i <= getExitMinute(r)) {
						p += erq[i] * ((i / 60) - ((i - 1) / 60));		
					}
				}
				Person temp = DB_Access.findUser(r.getUserID());//TODO: Fix Exception Handling	
				p *= getActivity(temp, business);
				System.out.println(temp.getFirstName() + "\n");
				if (p > 0) {
					
					infectedPeople.add(new InfectedPerson(temp, p));
				}		
			}
		}
		return infectedPeople;
	}
	
	public static double[] calculateTotalErq(Record covidrecord) {
		double[] totalerq = new double[(int) getBusinessDayDuration(covidrecord).toMinutes()];
		System.out.println("totalerq.length: " + totalerq.length);
		for (int i = 0; i < totalerq.length; i++) { //XXX: Fill totalerq with zeros
			totalerq[i] = 0;
		}
		ArrayList<Record> dayrecords = DB_Access.getBusinessDayRecords((Timestamp) covidrecord.getEntryDate());
		for (Record r : dayrecords) {
				if (DB_Access.isUserIDInfected(r.getUserID())) {
					double[] erq = calculateErq(r);
					for (int i = 0; i < erq.length; i++) {
						totalerq[i] += erq[i];
					}
				}
		}
		return totalerq;
		
	}
	
	private static double[] calculateErq (Record record) {
		Business business = DB_Access.findBusiness(record.getBusinessID());// TODO FIX EXCEPTION HANDLING
		double activity = getActivity(DB_Access.findUser(record.getUserID()), business);
		Duration businessDayDuration = getBusinessDayDuration(record);
		double[] erq = new double[(int) businessDayDuration.toMinutes()];	
		int entry = getEntryMinute(record);
		int exit = getExitMinute(record);
		System.out.println("EXIT: " + exit + " DATE: " + record.getExitDate());
		for (int counter = 0; counter <= (int) businessDayDuration.toMinutes(); counter++) {
			if (counter < entry) {
				erq[counter] = 0;
			} else if (counter < exit) {
				//System.out.println(activity + "/" + business.getIVRR() +"*"+ business.getV()+")"+ "*"+ "1"+ "- Math.exp((-business.getIVRR())" +"*" +"(counter -" + " entry)))");
				erq[counter] = (activity / (business.getIVRR() * business.getV())) * (1 - Math.exp((-business.getIVRR()) * (counter - entry)));
			} else {
				erq[counter] = erq[counter - 1] * Math.exp((-business.getIVRR()) * (counter - exit));
			}
			//System.out.println("ERQ: " + erq[counter]);
		}
		return erq;
			
	}
	
	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private static ArrayList<Business> removeDuplicateBusinesses(ArrayList<Business> list) {
		ArrayList<Business> newList = new ArrayList<Business>();
		for (Business b : list) {
			if (newList.contains(b) == false) {
				newList.add(b);
			}
		}
		return newList;
	}
	
	private static int getEntryMinute(Record record) {
		LocalDateTime startTime = convertToLocalDateTime(getStartRecord(record.getEntryDate()).getEntryDate());
		LocalDateTime recordEntryTime = convertToLocalDateTime(record.getEntryDate());
		int entry = (int) Duration.between(startTime, recordEntryTime).toMinutes();
		return entry;
	}
	
	private static int getExitMinute(Record record) {
		LocalDateTime startTime = convertToLocalDateTime(getStartRecord(record.getEntryDate()).getEntryDate());
		LocalDateTime recordExitTime = convertToLocalDateTime(record.getExitDate());
		int exit = (int) Duration.between(startTime, recordExitTime).toMinutes();
		return exit;
	}
	
	private static Duration getBusinessDayDuration(Record record) {
		Record start = getStartRecord(record.getEntryDate()); //start of business hours
		Record end = getLastRecord(record.getEntryDate()); //end of business hours		
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
}
