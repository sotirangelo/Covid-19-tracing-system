package data;
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
		ArrayList<InfectedPerson> finalInfected = new ArrayList<InfectedPerson>();
		ArrayList<InfectedPerson> allInfected = new ArrayList<InfectedPerson>();
		ArrayList<Business> covidstores = removeDuplicateBusinesses(database.Access.businessesVisited(infected.getUserID()));
		for (Business b : covidstores) {
			ArrayList<InfectedPerson> temp = calculatePI(infected, b);
			allInfected.addAll(temp);
		}
		for (int i = 0; i < allInfected.size(); i++) {
			InfectedPerson x = allInfected.get(i);
			boolean exists = false;
			for (InfectedPerson p : finalInfected) {
				if (p.getUserID().equals(x.getUserID())) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				for (int j = 0; j < allInfected.size(); j++) {
					if (i != j) {
						InfectedPerson y = allInfected.get(j);
						if (x.getUserID().equals(y.getUserID())) {
							x.increaseProbability(y.getPropability());
						}						
					}
				}
				finalInfected.add(x);
			}
		}
		return finalInfected;
	}
	
	
	public static double getActivity(Person p, Business b) {
		if (p instanceof InfectedPerson) {
			return b.getInfectedExertion();
		} else {
			return b.getHealthyExertion();
		}
	}
	
	public static ArrayList<InfectedPerson> calculatePI(InfectedPerson infected, Business business) { //TODO CHANGE TO BIGDECIMAL INSTEAD OF DOUBLE
		ArrayList<InfectedPerson> infectedPeople = new ArrayList<InfectedPerson>();
		Record covidrecord = database.Access.getPersonsRecord(infected, business); //ONLY NEED FIRST RECORD (IN CASE OF MULTIPLE)
		ArrayList<Record> records = database.Access.getBusinessDayRecords((Timestamp) covidrecord.getEntryDate());
		double[] erq = calculateTotalErq(covidrecord);
		for (Record r : records) { // Iterate day's records...
			if (database.Access.isUserIDInfected(r.getUserID()) == false) { //...ignoring already infected
				double[] p = new double[getExitMinute(r)]; //erq.length
				for (int i = 0; i < p.length; i++) { //XXX: Fill p with zeros
					p[i] = 0;
				}
				for (int i = getEntryMinute(r) ; i < p.length ; i++) {
					if (i == 0) {//in order to use i - 1
						continue;
					}
					
					p[i] = erq[i] * ((i / 60.0) - ((i - 1) / 60.0)); // include sum of previous p's 
				}
				Person temp = database.Access.findUser(r.getUserID());
				double sum = 0;
				for (int j = getEntryMinute(r); j < p.length; j++) {//XXX: j = getEntryMinute() instead of 0
					sum += p[j];
				}
				p[p.length - 1] = sum * getActivity(temp, business);
				p[p.length - 1] = 1 - Math.exp(-p[p.length - 1]);
				double percentage = p[p.length - 1] * 100;
				System.out.println(temp.getFirstName() + ": " + String.format("%.2f", percentage) + "%\n");
				if (p[p.length - 1] * 100 > 0) {
					
					infectedPeople.add(new InfectedPerson(temp, p[p.length - 1] * 100));//XXX: ADDING INFECTED ONE BY ONE IS SLOW
				}		
			}
		}
		return infectedPeople;
	}

	public static double[] calculateTotalErq(Record covidrecord) {
		double[] totalerq = new double[(int) getBusinessDayDuration(covidrecord).toMinutes()];
		for (int i = 0; i < totalerq.length; i++) { //XXX: Fill totalerq with zeros
			totalerq[i] = 0;
		}
		ArrayList<Record> dayrecords = database.Access.getBusinessDayRecords((Timestamp) covidrecord.getEntryDate());
		for (Record r : dayrecords) {
				if (database.Access.isUserIDInfected(r.getUserID())) {
					double[] erq = calculateErq(r);
					for (int i = 0; i < erq.length; i++) {
						totalerq[i] += erq[i];
					}
				}
		}
		return totalerq;
		
	}	

	private static double[] calculateErq (Record record) {
		Business business = database.Access.findBusiness(record.getBusinessID());
		double activity = getActivity(database.Access.findUser(record.getUserID()), business);
		Duration businessDayDuration = getBusinessDayDuration(record);
		double[] erq = new double[(int) businessDayDuration.toMinutes()];	
		int entry = getEntryMinute(record);
		int exit = getExitMinute(record);
		try {
			for (int counter = 0; counter < (int) businessDayDuration.toMinutes(); counter++) {	
				if (counter < entry) {
					erq[counter] = 0;
				} else if (counter <= exit) {
					erq[counter] = activity;
					erq[counter] = erq[counter] / (business.getIVRR() * business.getVolume());
					erq[counter] = erq[counter] * (1 - Math.exp(-business.getIVRR() * ((counter / 60.0) - (entry / 60.0))));
				} else {
					erq[counter] = erq[counter - 1];
				}
			}
			for (int counter = exit + 1; counter < (int) businessDayDuration.toMinutes(); counter++) {
				erq[counter] = erq[counter] * Math.exp(-business.getIVRR() * ((counter / 60.0) - (exit / 60.0)));
			}
		} catch (ArithmeticException e) {
			System.out.println("calculateErq numbers problem");
			e.printStackTrace();
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
		LocalDateTime startTime= convertToLocalDateTime(start.getEntryDate());
		LocalDateTime endTime = convertToLocalDateTime(end.getExitDate());
		Duration businessDayDuration = Duration.between(startTime, endTime);
		return businessDayDuration;
	}
	
	private static Record getStartRecord(Date date) {
		ArrayList<Record> dayrecords = database.Access.getBusinessDayRecords((Timestamp) date);//record1.getEntryDate
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
		ArrayList<Record> dayrecords = database.Access.getBusinessDayRecords((Timestamp) date);//record1.getEntryDate
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
