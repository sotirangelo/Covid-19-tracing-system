/*
 * Recordings
 *
 * Copyright (not) 2020 Javavirus
 */
 
import java.util.Date;

/**
 * Recordings class.
 * This class represents a recording, which is defined by its Check type (Check in/Check out),
 * its UserID,
 * its Date and Time,
 * and its Mask Type (for now).
 * 
 * @version 0.1 30 Nov 2020
 * @author Ioannis
 */

public class Recordings {
	private Check checkType;
	private String userID;
	private Date date; 
	private Mask maskType;
	
	public  Recordings(Check checkType, String userID, Date date, Mask maskType) {
		this.checkType = checkType;
		this.userID = userID;
		this.date = date;
		this.maskType = maskType;
		//System.out.println("Check Type,User ID,Date and Time,Mask Type"); (check how it works with JUnit)
		//System.out.println(checkType + "," + userID + "," + date + "," + maskType); 
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Mask getMaskType() {
		return maskType;
	}
	
	public void setMaskType(Mask maskType) {
		this.maskType = maskType;
	}
	
	public Check getCheckType() {
		return checkType;
	}
	
	public void setCheckType(Check checkType) {
		this.checkType = checkType;
	}
}
