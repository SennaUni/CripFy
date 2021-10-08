package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String hostName = "localhost";
		String dbName = "CripFy";
		String user = "senna";
		String senha = "252569";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		c = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", 
						hostName, dbName, user, senha));
	
		return c;
	}
}
