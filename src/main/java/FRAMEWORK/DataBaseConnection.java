package FRAMEWORK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {

	public static void DataBase(String Si_No, String TestCase_No, String Status, String Screenshot_Path) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apmosys", "root", "system");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from framework_table");
//			String query1 =  "INSERT INTO product (ID, NAME, PRICE, qty)"
//					 + "VALUES ('1', 'Tom', 88, 'good')";
//			stmt.executeUpdate(query1);

			stmt.executeUpdate("insert into framework_table (Si_No,TestCase_No,Status,Screenshot_Path) values ('"+Si_No+"','"+TestCase_No+"','"+Status+"','"+Screenshot_Path+"')");
			System.out.println("Successfully inserted");
//			while (rs.next())
//				System.out.println(
//						rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
