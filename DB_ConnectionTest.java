import java.sql.Connection;

import org.junit.jupiter.api.Test;

class DB_ConnectionTest {

	@Test
	void test() {
		DB_Connection jUnit = new DB_Connection();
		System.out.println("Trying to establish connection with MySQL Database:\n");
		try {
			Connection con = jUnit.getConnection();
			System.out.println("CONNECTION SUCCESSFUL");
		} catch (Exception e) {
			System.out.println("AN ERROR HAS OCCURED:");
			e.printStackTrace();
		}
	}

}
