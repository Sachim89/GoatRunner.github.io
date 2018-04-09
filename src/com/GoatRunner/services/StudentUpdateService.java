package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.GoatRunner.model.User;

public class StudentUpdateService {
	private static ConnectionService connection = new ConnectionService();

	// to update user profile details
	public static User updateInfo(User user) throws  SQLException  {
		// TODO Auto-generated method stub
		
		Connection con = connection.createConnection();
		
		int userId = user.getStudent_id();
		
		PreparedStatement st = con.prepareStatement("UPDATE PASSENGER SET studentname=?,pwd=?,phone_number=?,address=?,favlocation=?,"
				+ "email_address=? WHERE studentId = ?");
		st.setString(1, user.getName());
		st.setString(2, user.getPassword());
		st.setString(3, user.getPhone_number());
		st.setString(4, user.getAddress());
		st.setString(5, user.getFavourite_location());
		st.setString(6, user.getEmail_id());
		st.setInt(7, userId);
		st.executeQuery();
				
		return user;
}
	
	// to update user password
	public static void updatePassword(int userid, String password) throws SQLException  {
		Connection con = connection.createConnection();
		
			PreparedStatement st = con.prepareStatement("UPDATE PASSENGER SET pwd=? WHERE studentId = ?");
			st.setString(1, password);
			st.setInt(2, userid);
			st.executeQuery();
			
		
	}
	
}
	

