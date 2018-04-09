package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
	static Connection createConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:ORCL", "arlad",
					"ARLAD");
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to database");
			e.printStackTrace();
		}

		return null;
		// connection.close();

	}

	public static void main(String args[]) {
		createConnection();
	}

}
