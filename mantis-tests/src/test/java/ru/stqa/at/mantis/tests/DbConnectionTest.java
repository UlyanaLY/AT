package ru.stqa.at.mantis.tests;

import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {
	@Test
	public void dbConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select id, username, email  from mantis_user_mantis");
			// Do something with the Connection
			rs.close();
			st.close();
			conn.close();
			System.out.println(rs);

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
