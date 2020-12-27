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
	
	private String userID;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNumber;
	private Age ageCategory;
	private String password;
	
	public Person(String userID, String firstName, String lastName, String email, long phoneNumber, Age ageCategory, String password) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.ageCategory = ageCategory;
		this.password = password;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Age getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCAtegory(Age ageCategory) {
		this.ageCategory = ageCategory;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User ID : " + userID +
				", First & Last Name :" + firstName + " " + lastName +
				", Email : " + email +
				", Phone number : " + phoneNumber +
				", Age Category : " + ageCategory;
	}
}
