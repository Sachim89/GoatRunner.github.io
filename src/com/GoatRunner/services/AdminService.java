package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.CurrentRides;

public class AdminService {
	
	
		public static List<CurrentRides> cancel(int cabNo) throws GoatRunnerException, SQLException {
			ConnectionService connection = new ConnectionService();
			Connection con = connection.createConnection();
			//CurrentRides current = new CurrentRides();
			PreparedStatement st= con.prepareStatement("SELECT DRIVERID FROM CAB WHERE CABNO= ?");
			st.setInt(1, cabNo);
			ResultSet rs = st.executeQuery();
			int driverId = 0;
			if(rs!=null) {
				while(rs.next()) {
					driverId= rs.getInt(1);
				}
			}
			
			st.close();
			PreparedStatement st1 = con.prepareStatement("SELECT * FROM CURRENTRIDES WHERE DRIVERID = ? ");
			st1.setInt(1, driverId);
			ResultSet rs1 = st1.executeQuery();
			List<CurrentRides> list = new ArrayList<CurrentRides>();
			//rs1.beforeFirst();
				if (rs1 != null){
					while (rs1.next())
				{
				CurrentRides current = new CurrentRides();
				current.setBookingId(rs1.getInt(1));
				current.setStudentId(rs1.getInt(2));
				current.setSource(rs1.getString(3));
				current.setDestination(rs1.getString(4));
				current.setDriverId(rs1.getInt(5));
				list.add(current);
			}
				}
				return list;
			
		}

}
