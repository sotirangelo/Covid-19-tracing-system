/*
 * Business
 *
 * Copyright (not) 2020 Javavirus
 */

/**
 * Business class.
 * This class represents a business, which is defined by its businessID, email, name, total floor area,
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
	private String email;
	private String password;

	public Business(String businessID, String email, String password, String name, double space, BusinessType businessType) {
		this.businessID = businessID;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Business ID : " + businessID +
				", Business Name :" + name +
				", Email : " + email +
				", Floor Area : " + space +
				" m2, Business Type : " + businessType;
	}
}
