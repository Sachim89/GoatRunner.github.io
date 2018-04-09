package com.GoatRunner.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.GoatRunner.model.Driver;

/**
 * Test cases for all the functionalities related to Driver class.
 * @author Apoorva, Jui, Rishitha, Saranya
 *
 */

public class DriverTest {
	/**Test to verify the functionalities for assigning a driverr's name*/
	@Test
	public void testSetDriverrName() {
		Driver d = new Driver();
		d.setName("abc");
		assertEquals(d.getName(), "abc");
	}
	/**Test to verify the functionalities for getting a user's name*/
	@Test
	public void testGetDriverrName() {
		Driver d = new Driver();
		d.setName("abc");
		String result=d.getName();
		assertEquals(result, "abc");
	}
	/**Test to verify the functionalities for assigning a driver_id*/
	@Test
	public void testSetDriverId() {
		Driver d = new Driver();
		d.setDriver_id(123);
		assertEquals(d.getDriver_id(), 123);
	}
	/**Test to verify the functionalities for getting a driver_id */
	@Test
	public void testGetStudentId() {
		Driver d = new Driver();
		d.setDriver_id(123);
		int result=d.getDriver_id();
		assertEquals(result, 123);
	}

	/**Test to verify the functionalities for assigning a driver's phone_number*/
	@Test
	public void testSetPhoneNumber() {
		Driver d = new Driver();
		d.setPhone_number("123456789");
		assertEquals(d.getPhone_number(), "123456789");
	}
	
	/**Test to verify the functionalities for getting a driver's phone_number*/
	@Test
	public void testGetPhoneNumber() {
		Driver d = new Driver();
		d.setPhone_number("123456789");
		String result=d.getPhone_number();
		assertEquals(result, "123456789");
	}
	
	
}
