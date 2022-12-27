package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLiBanHang", "sa", "sapassword");
		if (con != null) {
	    System.out.println("Connected succes!!!<3<3<3");
		}
		return con; 
	}
}
