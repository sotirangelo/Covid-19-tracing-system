import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB_Connection {

	/* Database connection settings, change dbName, dbusername, dbpassword */
	//private final static String dbServer = "localhost";
	private final static String dbServerPort = "3306";
	//private final static String dbName = "javavirus_DB";
	//private final static String dbusername = "root";
	//private final static String dbpassword = "8ASI4Jco!Nm!QuOL";
	private final static String dbServer = "prometheus.dmst.aueb.gr";
	private final static String dbName = "isandalis_database_dmst";
	private final static String dbusername = "isandalis";
	private final static String dbpassword = "U8aQEpJ357yz";

	private static Connection con = null;

	/**
	 * Establishes a connection with the database, initializes and returns
	 * the Connection object.
	 *
	 * @return Connection, the Connection object
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {

		/* Step 1 -> Dynamically load the driver's class file into memory */

		try {

			// Dynamically load the driver's class file into memory
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {

			throw new Exception("MySQL Driver error: " + e.getMessage());
		}

		/*
		 * Step 2 -> Establish a connection with the database and initialize
		 * the Connection object (con)
		 */

		try {

			con = DriverManager.getConnection("jdbc:mysql://"
				+ dbServer + ":" + dbServerPort + "/" + dbName, dbusername, dbpassword);

			// Step 3 -> returns the connection object
			return con;

		} catch (Exception e) {

			// throw Exception if any error occurs
			throw new Exception("Could not establish connection with the Database Server: "
				+ e.getMessage());
		}

	} // End of getConnection

	/**
	 * Close database connection. It is very important to close the database connection
	 * after it is used.
	 *
	 * @throws SQLException
	 */
	public void close() throws SQLException {

		try {

			// if connection is (still) open
			if (con != null)
				con.close(); // close the connection to the database to end database session

		} catch (SQLException e) {

			throw new SQLException("Could not close connection with the Database Server: "
				+ e.getMessage());
		}

	}// end of close

} // End of class
