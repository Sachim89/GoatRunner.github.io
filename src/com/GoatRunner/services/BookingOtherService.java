package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.GoatRunner.exception.GoatRunnerException;

public class BookingOtherService {

//to cancel a booking
	@Path("/cancel")
	@GET
	public static void cancel(int bookingId) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();

		PreparedStatement st = con.prepareStatement("UPDATE bookingdetails SET status =1 where BOOKINGID=?");
		st.setInt(1, bookingId);
		st.executeQuery();
		st.close();
	}
	
//to complete a booking	
		@Path("/complete")
		@GET
		public static void complete(int bookingId) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService();
		Connection con = connection.createConnection();

		PreparedStatement st = con.prepareStatement("UPDATE BOOKINGDETAILS SET status =1 where bookingId=?");
		st.setInt(1, bookingId);
		st.executeQuery();
		st.close();
	}

}
