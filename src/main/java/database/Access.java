package database;

import data.*;
import data.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.sql.Date;

/**
 * This class consists exclusively of static methods and fields.
 * Its purpose is to Register, Fetch, Authenticate and Find Users of the Database.
 * 
 * @version 0.1 27 Dec 2020
 * @author Ioannis
 * 
 */

public class Access {
	
	/**
	 * Returns a random UserID that doesn't exist in the Database
	 * 
	 * @return userID, String (8-digit UserID)
	 * @throws Exception, if encounter any error.
	 */
	public static String findNewUserID() {
		String userID;
		int numbers;
		ArrayList<String> userIDs = new ArrayList<String>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT UserID FROM isandalis_database_dmst.Person;";
		try {
            con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			while(rs.next()) {
				userIDs.add(rs.getString("Person.UserID"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING USER-IDs FROM DATABASE");
            e.printStackTrace();
		}
		Random r = new Random();
		boolean flag = true;
		do {
			flag = false;
			userID = "";
			numbers = 10000000 + (int)(r.nextFloat() * 89990000);
			userID += String.valueOf(numbers);
			for (String x: userIDs) {
				if (x.equals(userID)) {
					flag = true;
					break;
				}
			}
		} while(flag);
		return userID;
	} //End of findNewUserID
	
	/**
	 * Register/create new User.
	 *
	 * @param user, Person
	 * @throws Exception, if encounter any error.
	 */
	public static void register(Person person) {
        Connection con = null;
        PreparedStatement stmt = null;
        String checkSql = "SELECT * FROM Person WHERE PhoneNumber = ?";
        String sql = "INSERT INTO isandalis_database_dmst.Person (UserID, firstName, lastName, email, PhoneNumber, Password) VALUES (?, ?, ?, ?, ?, md5(?));";
        try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(checkSql);
            stmt.setLong(1, person.getPhoneNumber());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.close();
                stmt.close();
                System.out.println("Phone already registered");
                return;
            }
            rs.close();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, person.getUserID());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.setString(4, person.getEmail());
            stmt.setLong(5, person.getPhoneNumber());
            stmt.setString(6,  person.getPassword());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            System.out.println("REGISTRATION SUCCESSFULL");
        } catch (Exception e) {
        	System.out.println("ERROR WHILE REGISTERING USER");
            e.printStackTrace();
        }
	}//end of register
	
	/**
	 * Add infected person in db.
	 * 
	 * @param person InfectedPerson
	 */
	public static void addInfected(InfectedPerson person) {
        Connection con = null;
        PreparedStatement stmt = null;
        String checkSql = "SELECT * FROM InfectedPerson WHERE UserID = ?";
        String sql = "INSERT INTO InfectedPerson (UserID, Propability) VALUES (?, ?);";
        try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(checkSql);
            stmt.setString(1, person.getUserID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.close();
                stmt.close();
                System.out.println("UserID already registered as infected.");
                return; //TODO: Remove
            }
            rs.close();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, person.getUserID());
            stmt.setDouble(2, person.getPropability());
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (Exception e) {
        	System.out.println("ERROR WHILE REGISTERING INFECTED USER");
            e.printStackTrace();
        }
	}//end of infected register
	
	/**
	 * Fetch Users from Database.
	 *
	 * @throws Exception, if encounter any error.
	 * @return ArrayList<Person>, which contains all the Person objects on the Database.
	 */
	public static ArrayList<Person> getUsers() {
		ArrayList<Person> users = new ArrayList<Person>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Person;";
		try {
            con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			while(rs.next()) {		
				Person user = new Person(rs.getString("Person.UserID"),
					rs.getString("Person.firstName"),
					rs.getString("Person.lastName"),
					rs.getString("Person.Email"),
					rs.getLong("Person.PhoneNumber"),
					rs.getString("Person.Password"));
				users.add(user);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING USERS FROM DATABASE");
            e.printStackTrace();
		}
		return users;
	} //End of getUsers
	
	/**
	 * This method is used to authenticate a user.
	 *
	 * @param UserID, String
	 * @param Password, String
	 * @return User, the Person object
	 * @throws Exception, if the credentials are not valid
	 */
	public static Person authenticateUser(String UserID, String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Person user = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Person WHERE UserID=? AND Password=md5(?);";
        try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, UserID);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                rs.close();
                stmt.close();
                System.out.println("Wrong UserID or password");
                return null;
            }
            user = new Person(rs.getString("Person.UserID"),
				rs.getString("Person.firstName"),
				rs.getString("Person.lastName"),
				rs.getString("Person.Email"),
				rs.getLong("Person.PhoneNumber"),
				rs.getString("Person.Password"));
            rs.close();
            stmt.close();
            con.close();
            System.out.println("LOG IN SUCCESSFULL");
        } catch (Exception e) {
        	System.out.println("ERROR WHILE AUTHENTICATING USER");
            e.printStackTrace();
        }
        return user;
	} //End of authenticateUser
	
	/**
	 * Search user by UserID
	 *
	 * @param UserID, String
	 * @return User, the Person object
	 * @throws Exception, if user not found
	 */
	public static Person findUser(String UserID) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Person user = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Person WHERE UserID=?;";
		try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, UserID);
            rs = stmt.executeQuery();
            if (!rs.next()) {
            	rs.close();
                stmt.close();
                return user;
            }
            Person testUser = new Person(rs.getString("Person.UserID"),
					rs.getString("Person.firstName"),
					rs.getString("Person.lastName"),
					rs.getString("Person.Email"),
					rs.getLong("Person.PhoneNumber"),
					rs.getString("Person.Password"));
            rs.close();
            stmt.close();
            con.close();
            user = testUser;
        } catch (Exception e) {
        	System.out.println("ERROR WHILE SEARCHING FOR SPECIFIC USER");
            e.printStackTrace();
        }
		return user;
	} //End of findUser
	
	/**
	 * Updates existing User's Email with the given one
	 * 
	 * @param userID, String
	 * @param email, String
	 * @throws Exception
	 */
	public static void editUserEmail(String userID, String email) {
		Connection con = null;
		PreparedStatement stmt = null;		
		String updateSql = "UPDATE isandalis_database_dmst.Person SET Email = ? WHERE UserID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setString(1, email);
		    stmt.setString(2,userID);
		    stmt.executeUpdate();
		    System.out.println("EMAIL UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING USER EMAIL");
            e.printStackTrace();
		}
	} //End of editUserEmail
	
	/**
	 * Updates existing User's Phone Number with the given one
	 * 
	 * @param userID, String
	 * @param phoneNumber, long
	 * @throws Exception
	 */
	public static void editUserPhoneNumber(String userID, long phoneNumber) {
		Connection con = null;
		PreparedStatement stmt = null;		
		String updateSql = "UPDATE isandalis_database_dmst.Person SET PhoneNumber = ? WHERE UserID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setLong(1, phoneNumber);
		    stmt.setString(2,userID);
		    stmt.executeUpdate();
		    System.out.println("PHONE NUMBER UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING USER PHONE NUMBER");
            e.printStackTrace();
		}
	} //End of editUserPhoneNumber
	
	/**
	 * Updates existing User's Password with the given one
	 * 
	 * @param userID, String
	 * @param password, String
	 * @throws Exception
	 */
	public static void editUserPassword(String userID, String password) {
		Connection con = null;
		PreparedStatement stmt = null;		
		String updateSql = "UPDATE isandalis_database_dmst.Person SET Password = md5(?) WHERE UserID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setString(1, password);
		    stmt.setString(2,userID);
		    stmt.executeUpdate();
		    System.out.println("PASSWORD UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING USER PASSWORD");
            e.printStackTrace();
		}
	} //End of editUserAgeCategory
	
	/**
	 * Returns a random BusinessID that doesn't exist in the Database
	 * 
	 * @return businessID, String (8-digit BusinessID)
	 * @throws Exception, if encounter any error.
	 */
	public static String findNewBusinessID() {
		String businessID;
		int numbers;
		ArrayList<String> businessIDs = new ArrayList<String>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT BusinessID FROM isandalis_database_dmst.Business;";
		try {
            con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			while(rs.next()) {
				businessIDs.add(rs.getString("Business.BusinessID"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING BUSINESS-IDs FROM DATABASE");
            e.printStackTrace();
		}
		Random r = new Random();
		boolean flag = true;
		do {
			flag = false;
			businessID = "";
			numbers = 10000000 + (int)(r.nextFloat() * 89990000);
			businessID += String.valueOf(numbers);
			for (String x: businessIDs) {
				if (x.equals(businessID)) {
					flag = true;
					break;
				}
			}
		} while(flag);
		return businessID;
	} //End of findNewBusinessID
	
	/**
	 * Register/create new Business.
	 *
	 * @param business, Business
	 * @throws Exception, if encounter any error.
	 */
	public static void register(Business business) {
        Connection con = null;
        PreparedStatement stmt = null;
        String checkSql = "SELECT * FROM isandalis_database_dmst.Business WHERE Email = ?";
        String sql = "INSERT INTO Business (BusinessID, Email, Password, Name, Space, height, BusinessType, ventilation) VALUES (?, ?, md5(?), ?, ?, ?, ?, ?);";   
        try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(checkSql);
            stmt.setString(1, business.getEmail());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.close();
                stmt.close();
                System.out.println("Email already registered");
                return;
            }
            rs.close();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, business.getBusinessID());
            stmt.setString(2, business.getEmail());
            stmt.setString(3,  business.getPassword());
            stmt.setString(4, business.getName());
            stmt.setDouble(5, business.getSpace());
            stmt.setDouble(6, business.getHeight());
            stmt.setString(7, business.getBusinessType().name());
            stmt.setString(8,  business.getVentilation().name());
            stmt.executeUpdate();
            stmt.close();
            con.close();    
            System.out.println("REGISTRATION SUCCESSFULL");
        } catch (Exception e) {
        	System.out.println("ERROR WHILE REGISTERING BUSINESS");
            e.printStackTrace();
        }
	}//end of register
	
	/**
	 * Fetch Businesses from Database.
	 *
	 * @return ArrayList<Business>, which contains all the Business objects on the Database.
	 * @throws Exception, if encounter any error.
	 */
	public static ArrayList<Business> getBusinesses() {
		ArrayList<Business> businesses =  new ArrayList<Business>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Business;";
		try {
            con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			while(rs.next()) {			
				Business business = new Business(rs.getString("Business.BusinessID"),
					rs.getString("Business.Email"),
					rs.getString("Business.Password"),
					rs.getString("Business.Name"),
					rs.getDouble("Business.Space"),
					rs.getDouble("Business.height"),
					BusinessType.valueOf(rs.getString("Business.BusinessType")),
					AER.valueOf(rs.getString("Business.ventilation")));
				businesses.add(business);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING BUSINESSES");
            e.printStackTrace();
		}
		return businesses;
	} //End of getBusinesses
	
	/**
	 * This method is used to authenticate a Business.
	 *
	 * @param BusinessID, String
	 * @param password, String
	 * @return business, the Business object
	 * @throws Exception, if the credentials are not valid
	 */
	public static Business authenticateBusiness(String BusinessID, String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Business business = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Business WHERE BusinessID=? AND Password=md5(?);";
        try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, BusinessID);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                rs.close();
                stmt.close();
                System.out.println("Wrong BusinessID or password");
            }
            business = new Business(rs.getString("Business.BusinessID"),
					rs.getString("Business.Email"),
					rs.getString("Business.Password"),
					rs.getString("Business.Name"),
					rs.getDouble("Business.Space"),
					rs.getDouble("Business.height"),
					BusinessType.valueOf(rs.getString("Business.BusinessType")),
					AER.valueOf(rs.getString("Business.ventilation")));
            rs.close();
            stmt.close();
            con.close();
            System.out.println("LOG IN SUCCESSFULL");
        } catch (Exception e) {
        	System.out.println("ERROR WHILE AUTHENTICATING BUSINESS");
        }
        return business;
	} //End of authenticateBusiness
	
	/**
	 * Search Business by BusinessID.
	 *
	 * @param businessID, String
	 * @return business, the Business object
	 * @throws Exception, if user not found
	 */
	public static Business findBusiness(String businessID) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Business business = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Business WHERE BusinessID = ?;";
		try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, businessID);
            rs = stmt.executeQuery();
            if (!rs.next()) {
            	rs.close();
                stmt.close();
            }
            Business testBusiness = new Business(rs.getString("Business.BusinessID"),
					rs.getString("Business.Email"),
					rs.getString("Business.Password"),
					rs.getString("Business.Name"),
					rs.getDouble("Business.Space"),
					rs.getDouble("Business.height"),
					BusinessType.valueOf(rs.getString("Business.BusinessType")),
					AER.valueOf(rs.getString("Business.ventilation")));
            rs.close();
            stmt.close();
            con.close();
            business = testBusiness;
        } catch (Exception e) {
        	System.out.println("ERROR WHILE SEARCHING FOR A SPECIFIC BUSINESS");
            e.printStackTrace();
        }
		return business;
	} //End of findBusiness
	
	/**
	 * Updates existing Business Email with the given one
	 * 
	 * @param businessID, String
	 * @param email, String
	 * @throws Exception
	 */
	public static void editBusinessEmail(String businessID, String email) {
		Connection con = null;
		PreparedStatement stmt = null;
		String updateSql = "UPDATE isandalis_database_dmst.Business SET Email = ? WHERE BusinessID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setString(1, email);
		    stmt.setString(2,businessID);
		    stmt.executeUpdate();
		    System.out.println("EMAIL UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING BUSINESS EMAIL");
            e.printStackTrace();
		}
	} //End of editBusinessEmail
	
	/**
	 * Updates existing Business Password with the given one
	 * 
	 * @param businesID, String
	 * @param password, String
	 * @throws Exception
	 */
	public static void editBusinessPassword(String businessID, String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		String updateSql = "UPDATE isandalis_database_dmst.Business SET Password = md5(?) WHERE BusinessID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setString(1, password);
		    stmt.setString(2,businessID);
		    stmt.executeUpdate();
		    System.out.println("PASSWORD UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING BUSINESS PASSWORD");
            e.printStackTrace();
		}
	} //End of editBusinessPassword
	
	/**
	 * Updates existing Business Floor Area with the given one
	 * 
	 * @param businesID, String
	 * @param space, double
	 * @throws Exception
	 */
	public static void editBusinessSpace(String businessID, double space) {
		Connection con = null;
		PreparedStatement stmt = null;
		String updateSql = "UPDATE isandalis_database_dmst.Business SET Space = ? WHERE BusinessID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setDouble(1, space);
		    stmt.setString(2, businessID);
		    stmt.executeUpdate();
		    System.out.println("FLOOR AREA UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING BUSINESS FLOOR AREA");
            e.printStackTrace();
		}
	} //End of editBusinesssSpace
	
	/**
	 * Updates existing Business Type with the given one
	 * 
	 * @param businesID, String
	 * @param businessType, BusinessType (Enumeration)
	 * @throws Exception
	 */
	public static void editBusinessType(String businessID, BusinessType businessType) {
		Connection con = null;
		PreparedStatement stmt = null;
		String updateSql = "UPDATE isandalis_database_dmst.Business SET BusinessType = ? WHERE BusinessID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setString(1, businessType.name());
		    stmt.setString(2, businessID);
		    stmt.executeUpdate();
		    System.out.println("BUSINESS TYPE UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING BUSINESS TYPE");
            e.printStackTrace();
		}
	} //End of editBusinesssType
	
	/**
	 * Updates existing Business Type with the given one
	 * 
	 * @param businesID, String
	 * @param ventilationType, AER (Enumeration)
	 * @throws Exception
	 */
	public static void editBusinessVentilationType(String businessID, AER ventilationType) {
		Connection con = null;
		PreparedStatement stmt = null;
		String updateSql = "UPDATE isandalis_database_dmst.Business SET ventilation = ? WHERE BusinessID = ?;";
		try {
			con = Connect.getConnection();
		    stmt = con.prepareStatement(updateSql);
		    stmt.setString(1, ventilationType.name());
		    stmt.setString(2, businessID);
		    stmt.executeUpdate();
		    System.out.println("VENTILATION TYPE UPDATED SUCCESSFULLY");
		    stmt.close();
		    con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING BUSINESS VENTILATION TYPE");
            e.printStackTrace();
		}
	} //End of editBusinesssVentilationType
	
	/**
	 * Stores userID, maskType and date (EntryDate) on the record table (isandalis_database_dmst.Record)
	 * 
	 * @param businessID, String
	 * @param userID, String
	 * @param date, java.util.Date
	 * @param maskType, Mask (enum)
	 * @throws Exception
	 */
	public static boolean checkIn (String businessID, String userID, java.util.Date date, Mask maskType) {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean success = false;
		String sql = "INSERT INTO isandalis_database_dmst.Record (UserID, MaskType,"
				+ " EntryDate, ExitDate, BusinessID) VALUES (?, ?, ?, ?, ?);";
		try {
			con = Connect.getConnection();
			stmt = con.prepareStatement(sql);
            stmt.setString(1, userID);
            stmt.setString(2, maskType.name());
            stmt.setTimestamp(3, new Timestamp(date.getTime()));
            stmt.setDate(4, null);
            stmt.setString(5, businessID);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            success = true;
            System.out.println("CHECK IN SUCCESSFULL\n");
		} catch (Exception e) {
			System.out.println("ERROR WHILE CHECKING IN");
            e.printStackTrace();
		}
		return success;
	} //End of checkIn
	
	/**
	 * Stores date (ExitDate) on record table (isandalis_database_dmst.Record) where ExitDate of given UserID is null
	 * 
	 * @param businessID, String
	 * @param userID, String
	 * @param date, java.util.Date
	 * @throws Exception
	 */
	public static boolean checkOut(String businessID, String userID, Date date) {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean success = false;
		String updateSql = "UPDATE isandalis_database_dmst.Record SET ExitDate = ? WHERE BusinessID = ? AND UserID = ? AND ExitDate IS NULL;";
		try {
			con = Connect.getConnection();
            		stmt = con.prepareStatement(updateSql);
            		stmt.setTimestamp(1, new Timestamp(date.getTime()));
            		stmt.setString(2, businessID);
            		stmt.setString(3, userID);
            		stmt.executeUpdate();
            		System.out.println("CHECK OUT SUCCESSFULL\n");
            		stmt.close();
            		con.close();
            		success = true;
		} catch (Exception e) {
			System.out.println("ERROR WHILE CHECKING OUT");
            e.printStackTrace();
		}
		return success;
	} //End of checkOut

	/**
	 * Fetch records of the given business' records (isandalis_database_dmst.Record)
	 * 
	 * @param businessID, String
	 * @return list, ArrayList<Record>
	 * @throws Exception
	 */	
	public static ArrayList<Record> getRecords(String businessID) {
		ArrayList<Record> list = new ArrayList<Record>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.Record WHERE Record.BusinessID = " + businessID + ";";
		try {
            		con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			while(rs.next()) { 
				Record record = new Record(rs.getString("Record.UserID"),
				Mask.valueOf(rs.getString("Record.MaskType")),
				rs.getTimestamp("Record.EntryDate"),
				rs.getTimestamp("Record.ExitDate"),
				rs.getString("Record.BusinessID"));
				list.add(record);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING RECORDS");
            e.printStackTrace();
		}
		return list;
	} //End of getRecords
	
	/**
	 * Last record of given businessID can be accessed so that UserID can be changed (to the given userID) in case of a typo
	 * 
	 * @param businessID, String
	 * @param userID, String
	 * @throws Exception 
	 */
	public static void editLastRecordsUserID(String businessID, String userID) {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM isandalis_database_dmst.Record WHERE BusinessID = ?"
				+ " AND NUM = (SELECT MAX(NUM) FROM isandalis_database_dmst.Record WHERE BusinessID = ?);";
		String updateSql = "UPDATE isandalis_database_dmst.Record SET UserID = ? WHERE NUM = ?;"; 
		try {
			con = Connect.getConnection();
            		stmt = con.prepareStatement(sql);
            		stmt.setString(1, businessID);
            		stmt.setString(2, businessID);            
            		ResultSet rs = stmt.executeQuery();
            		if (rs.next()) {
            			int num = rs.getInt("Record.NUM");
                		rs.close();
                		stmt.close();
                		stmt = con.prepareStatement(updateSql);
                		stmt.setString(1, userID);
                		stmt.setInt(2, num);
                		stmt.executeUpdate();
               			System.out.println("UPDATE SUCCESSFULL\n");
               			stmt.close();
            		} else {
            			System.out.println("AN ERROR HAS OCCURED");
            		}
            		rs.close();
            		con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE UPDATING USER ID ON LAST BUSINESS RECORD");
            		e.printStackTrace();
		}
	}// End of editLastRecordsUserID
	
	/**
	 * Register/create new Tracing User. It will need to be manually verified, because this account will have access to the personal information of all Users and Businesses
	 *
	 * @param password, String
	 * @param firstName, String
	 * @param lastName, String
	 * @param email, String
	 * @param phoneNum, long
	 * @throws Exception, if encounter any error.
	 */
	public static void registerTracingUser(String password, String firstName, String lastName, String email, long phoneNum) {
		Connection con = null;
        PreparedStatement stmt = null;
        String checkSql = "SELECT * FROM isandalis_database_dmst.TracingUser WHERE PhoneNumber = ? OR Email = ?";
        String sql = "INSERT INTO isandalis_database_dmst.TracingUser (ID, Password, firstName, lastName, email, PhoneNumber, Verified) VALUES (?, md5(?), ?, ?, ?, ?, ?);";
        try {
            con = Connect.getConnection();
            stmt = con.prepareStatement(checkSql);
            stmt.setLong(1, phoneNum);
            stmt.setString(2,  email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.close();
                stmt.close();
                System.out.println("Phone or Email already registered");
                return;
            }
            rs.close();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, findNewTracingID());
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, email);
            stmt.setLong(6, phoneNum);
            stmt.setBoolean(7,  false);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            System.out.println("REGISTRATION SUCCESSFULL");
        } catch (Exception e) {
        	System.out.println("ERROR WHILE REGISTERING TRACING USER");
            e.printStackTrace();
        }
	} //End of registerTracingUser
	
	/**
	 * This method is used to authenticate if a Tracing User exists and is verified.
	 *
	 * @param email, String
	 * @param password, String
	 * @return ver, boolean
	 * @throws Exception, if encounter any error
	 */
	public static boolean authenticateTracingUser(String email, String password) {
		boolean ver = false;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.TracingUser WHERE Email=? AND Password=md5(?);";
        try {
        	boolean isVerified = false;
            con = Connect.getConnection();
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                rs.close();
                stmt.close();
                con.close();
                System.out.println("Wrong UserID or password");
                return false;
            } else {
            	isVerified = rs.getBoolean("TracingUser.Verified");
            }
            if (!isVerified) {
            	rs.close();
            	stmt.close();
            	con.close();
            	System.out.println("USER NOT VERIFIED YET");
            	return false;
            }
            System.out.println("USER IS VERIFIED");
            rs.close();
            stmt.close();
            con.close();
            ver = true;
        } catch (Exception e) {
        	System.out.println("ERROR WHILE AUTHENTICATING USER");
            e.printStackTrace();
        }
        return ver;
	} //End of authenticateTracingUser
	
	/**
	 * Returns a random TracingUserID that doesn't exist in the Database
	 * 
	 * @return tracingID, String (8-digit TracingUserID)
	 * @throws Exception, if encounter any error.
	 */
	public static String findNewTracingID() {
		String tracingID;
		int numbers;
		ArrayList<String> tracingIDs = new ArrayList<String>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT ID FROM isandalis_database_dmst.TracingUser;";
		try {
            con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tracingIDs.add(rs.getString("TracingUser.ID"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING TRACING-IDs FROM DATABASE");
            e.printStackTrace();
		}
		Random r = new Random();
		boolean flag = true;
		do {
			flag = false;
			tracingID = "";
			numbers = 10000000 + (int)(r.nextFloat() * 89990000);
			tracingID += String.valueOf(numbers);
			for (String x: tracingIDs) {
				if (x.equals(tracingID)) {
					flag = true;
					break;
				}
			}
		} while(flag);
		return tracingID;
	} //End of findNewTracingID
	
    /**
     * Get list of Businesses visited by User.
     *
     * @param userId, String
     * @return stores, ArrayList<Business>
     */
    public static ArrayList<Business> businessesVisited(String userId) {
        ArrayList<Business> stores = new ArrayList<Business>();
        try {
		for (Business b : getBusinesses()) {
		    for (Record r : getRecords(b.getBusinessID())) {
		        if (r.getUserID().equals(userId)) {
		            stores.add(b);
		            break;
		        }
		    }
		}
	} catch (Exception e) {
		System.out.println("ERROR WHILE FETCHING BUSINESSES VISITED BY SPECIFIC USER");
		e.printStackTrace();
	}
        return stores;
    } //End of businessVisited
    
    /**
     * Get Person's Record in specific business
     *
     * @param person, Person
     * @param business, Business
     * @return record, Record
     */
    public static Record getPersonsRecord(Person person, Business business) {
        ArrayList<Record> records ;
		try {
			records = getRecords(business.getBusinessID());
			for (Record r : records) {
            if (r.getUserID().equals(person.getUserID())) {
                return r;
            }
        }
		} catch (Exception e) {
			System.out.println("ERROR WHILE FETCHING RECORDS OF A SPECIFIC USER");
			e.printStackTrace();
		}
        return null;
    } //End of getPersonsRecord
    
    public static ArrayList<Record> getBusinessDayRecords(Timestamp recordtime) {
    	ArrayList<Record> records = new ArrayList<Record>();
    	String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(recordtime);
    	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String sqlQuery = "SELECT * FROM isandalis_database_dmst.Record WHERE CAST(EntryDate AS Date)=?;";
	try {	
            con = Connect.getConnection();
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, formattedDate);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	Record record = new Record(rs.getString("Record.UserID"),
            			Mask.valueOf(rs.getString("Record.MaskType")),
						rs.getTimestamp("Record.EntryDate"),
						rs.getTimestamp("Record.ExitDate"),
            			rs.getString("Record.BusinessID"));
            	records.add(record);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return records;
    }
    
    public static boolean isUserIDInfected(String UserID) {
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.InfectedPerson WHERE UserID=" + UserID + ";";

		try {

            con = Connect.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();
			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
    }

}
