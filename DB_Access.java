/*
 * DB_Access
 *
 * Copyright (not) 2020 Javavirus
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;

/**
 * This class consists exclusively of static methods and fields.
 * Its purpose is to Register, Fetch, Authenticate and Find Users of the Database.
 * 
 * @version 0.1 27 Dec 2020
 * @author Ioannis
 * 
 */

public class DB_Access {
	
	/**
	 * Register/create new User.
	 *
	 * @param user, Person
	 * @throws Exception, if encounter any error.
	 */
	public static void register(Person person) throws Exception {

        Connection con = null;
        PreparedStatement stmt = null;
        String checkSql = "SELECT * FROM Person WHERE UserID = ? OR Email = ?";
        String sql = "INSERT INTO Person (UserID, firstName, lastName, email, PhoneNumber, AgeCategory, Password) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {

            con = DB_Connection.getConnection();

            stmt = con.prepareStatement(checkSql);
            stmt.setString(1, person.getUserID());
            stmt.setString(2, person.getEmail());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rs.close();
                stmt.close();
                throw new Exception("UserID or Email already registered");
            }

            rs.close();

            stmt = con.prepareStatement(sql);

            stmt.setString(1, person.getUserID());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.setString(4, person.getEmail());
            stmt.setLong(5, person.getPhoneNumber());
            stmt.setString(6, person.getAgeCategory().name());
            stmt.setString(7,  person.getPassword());

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

	}//end of register
	
	/**
	 * Fetch Users from Database.
	 *
	 * @throws Exception, if encounter any error.
	 * @return ArrayList<Person>, which contains all the Person objects on the Database.
	 */
	public static ArrayList<Person> getUsers() throws Exception {

		ArrayList<Person> users =  new ArrayList<Person>();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM Person;";

		try {

            con = DB_Connection.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();

			while(rs.next()) {
							
							Person user = new Person(rs.getString("Person.UserID"),
												rs.getString("Person.firstName"),
												rs.getString("Person.lastName"),
												rs.getString("Person.Email"),
												rs.getLong("Person.PhoneNumber"),
												Age.valueOf(rs.getString("Person.AgeCategory")),
												rs.getString("Person.Password"));

							users.add(user);

			}

			rs.close();
			stmt.close();
			con.close();
			return users;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	} //End of getUsers
	
	/**
	 * This method is used to authenticate a user.
	 *
	 * @param UserID, String
	 * @param Password, String
	 * @return User, the Person object
	 * @throws Exception, if the credentials are not valid
	 */
	public static Person authenticateUser(String UserID, String password) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM Person WHERE UserID=? AND Password=?;";

        try {

            con = DB_Connection.getConnection();
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, UserID);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();
                throw new Exception("Wrong UserID or password");
            }

            Person user = new Person(rs.getString("Person.UserID"),
					rs.getString("Person.firstName"),
					rs.getString("Person.lastName"),
					rs.getString("Person.Email"),
					rs.getLong("Person.PhoneNumber"),
					Age.valueOf(rs.getString("Person.AgeCategory")),
					rs.getString("Person.Password"));

            rs.close();
            stmt.close();
            con.close();
            
            return user;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
	} //End of authenticateUser
	
	/**
	 * Search user by UserID
	 *
	 * @param UserID, String
	 * @return User, the Person object
	 * @throws Exception, if user not found
	 */
	public static Person findUser(String UserID) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM Person WHERE UserID=?;";

		try {

            con = DB_Connection.getConnection();
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, UserID);

            rs = stmt.executeQuery();

            Person user = null;

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
					Age.valueOf(rs.getString("Person.AgeCategory")),
					rs.getString("Person.Password"));

            rs.close();
            stmt.close();
            con.close();
            
            user = testUser;
            return user;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

	} //End of findUser
	
	/**
	 * Register/create new Business.
	 *
	 * @param business, Business
	 * @throws Exception, if encounter any error.
	 */
	public static void register(Business business) throws Exception {
		
        Connection con = null;
        PreparedStatement stmt = null;
        String checkSql = "SELECT * FROM Business WHERE BusinessID = ? OR Email = ?";
        String sql = "INSERT INTO Business (BusinessID, Email, Password, Name, Space, BusinessType, AER) VALUES (?, ?, ?, ?, ?, ?, ?);";
                
        try {

            con = DB_Connection.getConnection();

            stmt = con.prepareStatement(checkSql);
            stmt.setString(1, business.getBusinessID());
            stmt.setString(2, business.getEmail());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rs.close();
                stmt.close();
                throw new Exception("BusinessID or Email already registered");
            }

            rs.close();

            stmt = con.prepareStatement(sql);

            stmt.setString(1, business.getBusinessID());
            stmt.setString(2, business.getEmail());
            stmt.setString(3,  business.getPassword());
            stmt.setString(4, business.getName());
            stmt.setDouble(5, business.getSpace());
            stmt.setString(6, business.getBusinessType().name());
            stmt.setString(7,  business.getVentilation().name());

            stmt.executeUpdate();
            
            stmt.close();
            con.close();
                     
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
        try {
        	System.out.println("\nTrying to create Record table:");
        	createTable(business.getBusinessID());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

	}//end of register
	
	/**
	 * Fetch Businesses from Database.
	 *
	 * @return ArrayList<Business>, which contains all the Business objects on the Database.
	 * @throws Exception, if encounter any error.
	 */
	public static ArrayList<Business> getBusinesses() throws Exception {

		ArrayList<Business> businesses =  new ArrayList<Business>();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM Business;";

		try {

            con = DB_Connection.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();

			while(rs.next()) {
							
							Business business = new Business(rs.getString("Business.BusinessID"),
												rs.getString("Business.Email"),
												rs.getString("Business.Password"),
												rs.getString("Business.Name"),
												rs.getLong("Business.Space"),
												BusinessType.valueOf(rs.getString("Business.BusinessType")),
												AER.valueOf(rs.getString("Business.AER")));

							businesses.add(business);

			}

			rs.close();
			stmt.close();
			con.close();
			return businesses;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	} //End of getBusinesses
	
	/**
	 * This method is used to authenticate a Business.
	 *
	 * @param BusinessID, String
	 * @param password, String
	 * @return business, the Business object
	 * @throws Exception, if the credentials are not valid
	 */
	public static Business authenticateBusiness(String BusinessID, String password) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM Business WHERE BusinessID=? AND Password=?;";

        try {

            con = DB_Connection.getConnection();
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, BusinessID);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();
                throw new Exception("Wrong BusinessID or password");
            }

            Business business = new Business(rs.getString("Business.BusinessID"),
					rs.getString("Business.Email"),
					rs.getString("Business.Password"),
					rs.getString("Business.Name"),
					rs.getLong("Business.Space"),
					BusinessType.valueOf(rs.getString("Business.BusinessType")),
					AER.valueOf(rs.getString("Business.AER")));

            rs.close();
            stmt.close();
            con.close();

            return business;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
	} //End of authenticateBusiness
	
	/**
	 * Search Business by BusinessID.
	 *
	 * @param businessID, String
	 * @return business, the Business object
	 * @throws Exception, if user not found
	 */
	public static Business findBusiness(String businessID) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM Business WHERE BusinessID=?;";

		try {

            con = DB_Connection.getConnection();
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, businessID);

            rs = stmt.executeQuery();

            Business business = null;

            if (!rs.next()) {
            	rs.close();
                stmt.close();
                return business;
            }

            Business testBusiness = new Business(rs.getString("Business.BusinessID"),
					rs.getString("Business.Email"),
					rs.getString("Business.Password"),
					rs.getString("Business.Name"),
					rs.getLong("Business.Space"),
					BusinessType.valueOf(rs.getString("Business.BusinessType")),
					AER.valueOf(rs.getString("Business.AER")));
            rs.close();
            stmt.close();
            con.close();

            business = testBusiness;
            return business;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

	} //End of findBusiness
	
	/**
	 * Creates Record Table (javavirus_DB.record`businessID`) on Database for the businessID given.
	 * 
	 * Automatically used by register(business) method.
	 * 
	 * @param businessID
	 * @throws Exception
	 */
	public static void createTable(String businessID) throws Exception {
		
		Connection con = null;
        PreparedStatement stmt = null;
        String tableName = "isandalis_database_dmst.record" + businessID;
        String sqlTable = "CREATE TABLE " + tableName + " (" +
        		"	 Number int NOT NULL AUTO_INCREMENT," +
        		"    UserID int NOT NULL," +
        		"    MaskType varchar(20) NOT NULL," +
        		"    EntryDate datetime NOT NULL," +
        		"    ExitDate datetime DEFAULT NULL," +
        		"  	 PRIMARY KEY (Number)," +
        		"	 FOREIGN KEY (UserID) REFERENCES isandalis_database_dmst.Person (UserID)" +
        		");";
        
        try {
        	con = DB_Connection.getConnection();
        	
        	stmt = con.prepareStatement(sqlTable);
            stmt.executeUpdate();
            
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
	} //End of createTable
	
	/**
	 * Stores userID, maskType and date (EntryDate) on the given businessID record table (javavirus_DB.record`businessID`)
	 * 
	 * @param businessID, String
	 * @param userID, String
	 * @param date, java.util.Date
	 * @param maskType, Mask (enum)
	 * @throws Exception
	 */
	public static void checkIn (String businessID, String userID, java.util.Date date, Mask maskType) throws Exception {
		
		String tableName = "isandalis_database_dmst.record" + businessID;
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO " + tableName + " (UserID, MaskType, EntryDate, ExitDate) VALUES (?, ?, ?, ?);";

		try {
			
			con = DB_Connection.getConnection();
			
			stmt = con.prepareStatement(sql);
			
            stmt.setString(1, userID);
            stmt.setString(2, maskType.name());
            stmt.setTimestamp(3, new Timestamp(date.getTime()));
            stmt.setDate(4, null);
            stmt.executeUpdate();
            
            stmt.close();
            con.close();
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	} //End of checkIn
	
	
	/**
	 * Stores date (ExitDate) on the given businessID record table (javavirus_DB.record`businessID`) where ExitDate of given UserID is null
	 * 
	 * @param businessID, String
	 * @param userID, String
	 * @param date, java.util.Date
	 * @throws Exception
	 */
	public static void checkOut(String businessID, String userID, Date date) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;		
		String tableName = "isandalis_database_dmst.record" + businessID;
		String sql = "SELECT * FROM " + tableName + " WHERE UserID = ? AND ExitDate IS NULL;";
		String updateSql = "UPDATE " + tableName + " SET ExitDate = ?;";
		
		try {
			
			con = DB_Connection.getConnection();
			
            stmt = con.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                rs.close();
                stmt.close();
                stmt = con.prepareStatement(updateSql);
                stmt.setTimestamp(1, new Timestamp(date.getTime()));
                stmt.executeUpdate();
               	System.out.println("CHECK OUT SUCCESSFULL\n");
               	stmt.close();
            } else {
            	System.out.println("AN ERROR HAS OCCURED");
            }
			
            rs.close();
            con.close();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	} //End of checkOut

	/**
	 * Fetch records of the given businessID record table (javavirus_DB.record`businessID`)
	 * 
	 * @param businessID, String
	 * @return list, ArrayList<Record>
	 * @throws Exception
	 */
	public static ArrayList<Record> getRecords(String businessID) throws Exception {
		
		ArrayList<Record> list = new ArrayList<Record>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String rsTable = "record" + businessID;
		String sqlQuery = "SELECT * FROM isandalis_database_dmst.record" + businessID + ";";

		try {

            con = DB_Connection.getConnection();
			stmt = con.prepareStatement(sqlQuery);
			rs = stmt.executeQuery();

			while(rs.next()) {
							
							Record record = new Record(rs.getString(rsTable + ".UserID"),
												Mask.valueOf(rs.getString(rsTable + ".MaskType")),
												rs.getTimestamp(rsTable + ".EntryDate"),
												rs.getTimestamp(rsTable + ".ExitDate"));

							list.add(record);

			}

			rs.close();
			stmt.close();
			con.close();
			return list;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	} //End of getRecords

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
			System.out.println("AN ERROR HAS OCCURED: ");
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
    public static Record getPersonsRecord(Person person, Business business) throws NullPointerException {
        ArrayList<Record> records ;
		try {
			records = getRecords(business.getBusinessID());
			for (Record r : records) {
            if (r.getUserID().equals(person.getUserID())) {
                return r;
            }
        }
		} catch (Exception e) {
			System.out.println("AN ERROR HAS OCCURED: ");
			e.printStackTrace();
		}
        
        return null;
    } //End of getPersonsRecord

}
