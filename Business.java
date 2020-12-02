/*
 * Business
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;

/**
 * Business class.
 * This class represents a business, which is defined by its total floor area,
 * and its type (for now). 
 *
 * @version 0.1 26 Nov 2020
 * @author Sotiris
 */
public class Business {
	private String businessID;
	private String name;
	private double space;
	private BusinessType businessType;
	public ArrayList<Record> records = new ArrayList<Record>();

	public Business(String businessID, String name, double space, BusinessType businessType) {
		this.businessID = businessID;
		this.name = name;
		this.space = space;
		this.businessType = businessType;
		
	}

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpace() {
		return space;
	}

	public void setSpace(double space) {
		this.space = space;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
}
