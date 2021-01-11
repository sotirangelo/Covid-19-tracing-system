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
/*	
	private Person p = new Person();
	private double maskScore = scoreFromMask(p); 
	private double exertionScore = scoreFromExertion(p);
	private double ageScore = scoreDependingOnAge(p);
	private double generalScore = maskScore + exertionScore + ageScore;
	
	public double scoreFromMask(Person p) {
		double score =0;
		if (p.getMask() == Mask.NONE) {
			score +=0;
		} else if(p.getMask() == Mask.FABRIC) {
			score +=1;
		}else if(p.getMask() == Mask.MEDICAL) {
			score +=2;
		}else if (p.getMask() == Mask.RESPIRATOR) {
			score +=3;
		}
		return score;
	}
	public double scoreFromExertion(Person p) {
		double score = 0;
		if (p.getExertion() == Exertion.RESTING) {
			score +=0.5;
		} else if (p.getExertion() == Exertion.STANDING) {
			score +=0;
		}
		return score;
	}
	public double scoreDependingOnAge(Person p) {
		double score = 0;
		if (p.getAgeCategory() == Age.UNDERAGE) {
			score += 0;
		}else if (p.getAgeCategory() == Age.EIGHTEEN) {
			score += 1;
		}else if (p.getAgeCategory() == Age.THIRTY) {
			score += 2;
		}else if (p.getAgeCategory() == Age.SIXTY) {
			score +=3;
		}
		return score;
	}
*/

	public double[] calculateErq(Business business) {
		var erq = new double[];
		var activity = getInfectedActivity(); //TODO
		Record r = getRecordOfInfected(); //TODO
		int operationMinutes = getOperationMinutes(); //TODO: For each business (start record & end record)
		int infectedEntry; //TODO
		int infectedExit; //TODO
		double IVRR; //TODO
		double V; //TODO
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

}
