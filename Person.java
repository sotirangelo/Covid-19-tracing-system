/*
 * Person
 *
 * Copyright (not) 2020 Javavirus
 */

/**
 * This class consists exclusively of static methods and fields.
 * Its purpose is to get or set values for a "person" object.
 *
 * @version 0.1 26 Nov 2020
 * @author Sotiris
 * @author Ioannis
 * 
 */

public class Person {
	
	private String UserID;
	private String firstName;
	private String lastName;
	private String email;
	private int phoneNumber;
	private Age ageCategory;
	
	public Person(String UserID, String firstName, String lastName, String email, int phoneNumber, Age ageCategory) {
		this.UserID = UserID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.ageCategory = ageCategory;
	}
	
	public String getUserID() {
		return firstName;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Age getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCAtegory(Age ageCategory) {
		this.ageCategory = ageCategory;
	}
	@Override
	public String toString() {
		return "User ID : " + UserID +
				", First & Last Name :" + firstName + "" + lastName +
				", Email : " + email +
				", Phone number : " + phoneNumber +
				", Age Category : " + ageCategory;
	}
}
