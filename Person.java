/*
 * Person
 *
 * Copyright (not) 2020 Javavirus
 */
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

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
	/** Temporary field that formats Date objects in String "dd-MM-yyyy HH:mm:ss" format . Has not been used yet*/
	static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private int phoneNumber;
	private Mask maskType;
	private Exertion exertionType;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;

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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public Mask getMask() {
        return maskType;
	}
	public void setMask(Mask mask) {
        this.maskType = mask;
	}
	public Exertion getExertion() {
		return exertionType;
	}
	public void setExertion(Exertion exertion) {
		this.exertionType = exertion;
	}
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalDateTime time) {
		this.entryTime = time;
	}
	public LocalDateTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalDateTime time) {
		this.exitTime = time;
	}


}

