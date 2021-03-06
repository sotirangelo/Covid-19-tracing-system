package data;
/*
 * Record
 *
 * Copyright (not) 2020 Javavirus
 */
 
import java.util.Date;

/**
 * Record class.
 * This class represents a recording, which is defined by its Check type (Check in/Check out),
 * its UserID,
 * its Date and Time,
 * and its Mask Type (for now).
 * 
 * @version 0.1 30 Nov 2020
 * @author Ioannis
 */

public class Record {

	private String userID;
	private Date entryDate;
	private Date exitDate;
	private Mask maskType;
	private String businessID;

	public Record (String userID, Mask maskType, Date entryDate, Date exitDate, String businessID) {
		this.userID = userID;
		this.maskType = maskType;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.businessID = businessID;
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate) {
    	this.entryDate = entryDate;
	}
	
	public Date getExitDate() {
		return exitDate;
	}
	
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	
	public Mask getMaskType() {
		return maskType;
	}
	
	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}
	
	public String getBusinessID() {
		return businessID;
	}
	
	public void setMaskType(Mask maskType) {
		this.maskType = maskType;
	}
	
	@Override
	public String toString() {
		return "User ID : " + userID +
				", Mask Type : " + maskType.name() +
				", EntryDate : " + entryDate +
				", Exit Date : " + exitDate +
				", Business ID : " + businessID;
	}
	
}
