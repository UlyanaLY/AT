package ru.stqa.at.addressbook.test;

import com.mysql.cj.protocol.Resultset;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
	@Test
	public void  dbConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
			// Do something with the Connection

			Groups groups = new Groups();
			while(rs.next()){
				groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
								.withHeader(rs.getString("group_header")).withHeader(rs.getString("group_header")));
			}
			rs.close();
			st.close();
			conn.close();
			System.out.println(groups);

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
