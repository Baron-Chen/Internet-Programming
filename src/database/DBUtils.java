package database;

import java.sql.Connection;
import java.util.ResourceBundle;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
public class DBUtils {
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	public static String DRIVER;
	
	private static ResourceBundle rb = ResourceBundle.getBundle("database.db-config");
	private DBUtils(){}
	
	
	static {
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
		DRIVER = rb.getString("jdbc.driver");
		try {
			Class.forName(DRIVER);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// connect the database
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connect failed");
		}
		return conn;
	} 
	
	
	     /**
	      * close the db connection
	      * @param rs
	      * @param stat
	      * @param conn
	      */
	public static void close(ResultSet rs,Statement stat,Connection conn) {
		try {
			if(rs!=null)rs.close();
			if(stat!=null)stat.close();
			if(conn!=null)conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
