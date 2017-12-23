import java.sql.*;
import javax.swing.*;

public class TestCon {
	static Connection con = null;
	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserAccounts", "root", "951126");
			JOptionPane.showMessageDialog(null, "Welcome to Unscramble That Word!");
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}