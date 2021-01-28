package data;
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
	private String password;
	
	/**
	 * Person Constructor, create a Person Object
	 * 
	 * @param userID, String
	 * @param firstName, String
	 * @param lastName, String
	 * @param email, String
	 * @param phoneNumber, long
	 * @param ageCategory, Age (Enumeration)
	 * @param password, String
	 * 
	 */
	public Person(String userID, String firstName, String lastName, String email, long phoneNumber, String password) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public String getUserID() {
		return userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "User ID : " + userID +
				", First & Last Name :" + firstName + " " + lastName +
				", Email : " + email +
				", Phone number : " + phoneNumber;
	}
}
