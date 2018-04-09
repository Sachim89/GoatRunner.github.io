package com.GoatRunner.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.Cab;
import com.GoatRunner.model.CurrentRides;
import com.GoatRunner.model.Driver;

public class DriverService {
	private static ConnectionService connection = new ConnectionService();

	public static Driver login_driver(int driver_id, String password) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); // class for
																// connection

		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("select * from driver where driverid=?");
		st.setInt(1, driver_id);

		ResultSet rs = st.executeQuery();
		String checkDriver = "";
		String checkpwd = "";
		String name = "";
		String phoneNumber = "";
		if (rs != null) {
			while (rs.next()) {
				checkDriver = rs.getString(1);
				checkpwd = rs.getString(4);
				name = rs.getString(2);
				phoneNumber = rs.getString(3);

			}
		}
		Driver driver = new Driver();

		// if driver exists and password is correct
		if (checkDriver.equalsIgnoreCase(Integer.toString(driver_id)) && checkpwd.equalsIgnoreCase(password)) {

			driver.setDriver_id(driver_id);
			driver.setName(name);
			driver.setPhone_number(phoneNumber);

			return driver; // successful
		}

		// to check if password is incorrect
		if (checkDriver.equals(driver_id) && !(checkpwd.equals(password))) {
			throw new GoatRunnerException("Incorrect Password");
		}
		// if driver doesn't exist in database
		throw new GoatRunnerException("You are not a driver");

		// con.close();
	}

	// allocate a Cab to the driver and set driver id in cab table
	public static Cab cabToDriver(int driver_id) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); // class for
																// connection
		Connection con = connection.createConnection();
		Cab cab = new Cab();
		int cab_id = 0;
		PreparedStatement st1 = con.prepareStatement("select cabno from CAB where assigned = 0");
		ResultSet rs1 = st1.executeQuery();
		if (rs1 != null) {
			while (rs1.next())
				cab_id = rs1.getInt(1);
			
		}
		PreparedStatement st2 = con.prepareStatement("UPDATE CAB SET driverId = ?, assigned=1 where cabno=?");
		st2.setInt(1, driver_id);
		st2.setInt(2, cab_id);

		cab.setCabno(cab_id);
		cab.setDriverid(driver_id);
//		cab.setCurrent_capacity(rs1.getInt(3));
//		cab.setLatitude(rs1.getDouble(6));
//		cab.setLongitude(rs1.getDouble(5));
		return cab;
	}

	// get all current rides for that driver and send it to the front end
	public static List<CurrentRides> getRides(int driver_id) throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); // class for
																// connection
		Connection con = connection.createConnection();
		//CurrentRides current = new CurrentRides();
		PreparedStatement st = con.prepareStatement("Select * from CURRENTRIDES where driverid = ?");
		st.setInt(1, driver_id);
		ResultSet rs = st.executeQuery();
		List<CurrentRides> list = new ArrayList<CurrentRides>();
		//rs.beforeFirst();
		if (rs != null) {
			while (rs.next()) {
				CurrentRides current = new CurrentRides();
				current.setBookingId(rs.getInt(1));
				current.setStudentId(rs.getInt(2));
				current.setSource(rs.getString(3));
				current.setDestination(rs.getString(4));
				current.setDriverId(rs.getInt(5));
				list.add(current);
			}
		}		return list;
	}

	// to update current location the CAB
	public static void updateLocation(int driver_id, double latitude, double longitude)
			throws GoatRunnerException, SQLException {
		ConnectionService connection = new ConnectionService(); // class for
																// connection
		Connection con = connection.createConnection();
		PreparedStatement st = con.prepareStatement("UPDATE CAB SET latitude =?, longitude= ? where driverId=?");
		st.setDouble(1, latitude);
		st.setDouble(2, longitude);
		st.setInt(3, driver_id);
		st.executeQuery();
	}

	// logout functionality for driver
	public static boolean driverValidation(int driverId) throws SQLException, GoatRunnerException {
		Connection con = connection.createConnection();
		PreparedStatement st;

		st = con.prepareStatement("select * from driver where driverId=?");
		st.setInt(1, driverId);
		ResultSet rs = st.executeQuery();
		if (rs != null) {

			if (rs.next() == true) {
				// DeAllcate the cab that was assigned to this driver
				int cab_id = 0;
				PreparedStatement st1 = con.prepareStatement("select cabno from CAB where driverid = ?");
				st1.setInt(1, driverId);
				ResultSet rs1 = st1.executeQuery();
				if (rs1 != null) {
					cab_id = rs1.getInt(1);
				}
				PreparedStatement st2 = con.prepareStatement("UPDATE CAB SET assigned = 0 where cabno=?");
				st2.setInt(1, cab_id);
				// logout successful
				return true;
			} else {
				throw new GoatRunnerException("Driver does not exist");
			}
		}

		return false;
	}

}
