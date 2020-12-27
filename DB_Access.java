/*
 * DB_Access
 *
 * Copyright (not) 2020 Javavirus
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	 * @param user, User
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

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

	}//end of register
	
	/**
	 * Fetch Person objects from Database.
	 *
	 * @throws Exception, if encounter any error.
	 * @return ArrayList<Person>, which contains all the Person objects on the Database
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

							users.add( user );

			}

			rs.close();
			stmt.close();
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
	public static Person authenticate(String UserID, String password) throws Exception {

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
                throw new Exception("Wrong username or password");
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

            return user;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
	} //End of authenticate
	
	/**
	 * Search user by username
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

            user = testUser;
            return user;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

	} //End of findUser
}
