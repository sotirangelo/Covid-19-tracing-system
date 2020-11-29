/*
 * DataAnalysis
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

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

}