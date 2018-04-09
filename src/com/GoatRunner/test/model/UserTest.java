package com.GoatRunner.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.GoatRunner.model.User;
/**
 * Test cases for all the functionalities related to User class.
 * @author Apoorva, Jui, Rishitha, Saranya
 *
 */
public class UserTest {
	/**Test to verify the functionalities for assigning a user's name*/
	@Test
	public void testSetUserName() {
		User u = new User();
		u.setName("abc");
		assertEquals(u.getName(), "abc");
	}
	
	/**Test to verify the functionalities for getting a user's name*/
	@Test
	public void testGetUserName() {
		User u = new User();
		u.setName("abc");
		String result=u.getName();
		assertEquals(result, "abc");
	}

	/**Test to verify the functionalities for assigning a student_id*/
	@Test
	public void testSetStudentId() {
		User u = new User();
		u.setStudent_id(123);
		assertEquals(u.getStudent_id(), 123);
	}
	/**Test to verify the functionalities for getting a student_id */
	@Test
	public void testGetStudentId() {
		User u = new User();
		u.setStudent_id(123);
		int result=u.getStudent_id();
		assertEquals(result, 123);
	}
	/**Test to verify the functionalities for assigning a user's password*/
	@Test
	public void testSetPassword() {
		User u = new User();
		u.setPassword("abc");
		assertEquals(u.getPassword(), "abc");
	}
	
	/**Test to verify the functionalities for getting a user's password*/
	@Test
	public void testGetPassword() {
		User u = new User();
		u.setPassword("abc");
		String result=u.getPassword();
		assertEquals(result, "abc");
	}
	/**Test to verify the functionalities for assigning a user's phone_number*/
	@Test
	public void testSetPhoneNumber() {
		User u = new User();
		u.setPhone_number("123456789");
		assertEquals(u.getPhone_number(), "123456789");
	}
	
	/**Test to verify the functionalities for getting a user's phone_number*/
	@Test
	public void testGetPhoneNumber() {
		User u = new User();
		u.setPhone_number("123456789");
		String result=u.getPhone_number();
		assertEquals(result, "123456789");
	}
	
	/**Test to verify the functionalities for assigning a user's Address*/
	@Test
	public void testSetAddress() {
		User u = new User();
		u.setAddress("abc");
		assertEquals(u.getAddress(), "abc");
	}
	
	/**Test to verify the functionalities for getting a user's Address*/
	@Test
	public void testGetAddress() {
		User u = new User();
		u.setAddress("abc");
		String result=u.getAddress();
		assertEquals(result, "abc");
	}
	
	/**Test to verify the functionalities for assigning a user's favourite_location*/
	@Test
	public void testSetFavouriteLocation() {
		User u = new User();
		u.setFavourite_location("abc");
		assertEquals(u.getFavourite_location(), "abc");
	}
	
	/**Test to verify the functionalities for getting a user's favourite_location*/
	@Test
	public void testGetFavouriteLocation() {
		User u = new User();
		u.setFavourite_location("abc");
		String result=u.getFavourite_location();
		assertEquals(result, "abc");
	}
	
	/**Test to verify the functionalities for assigning a user's email_id*/
	@Test
	public void testSetEmailId() {
		User u = new User();
		u.setEmail_id("xyz@abc");
		assertEquals(u.getEmail_id(), "xyz@abc");
	}
	
	/**Test to verify the functionalities for getting a user's email_id*/
	@Test
	public void testGetEmailId() {
		User u = new User();
		u.setEmail_id("xyz@abc");
		String result=u.getEmail_id();
		assertEquals(result, "xyz@abc");
	}
	
	/**Test to verify the functionalities for assigning a user's security_question*/
	@Test
	public void testSetSecurityQuestion() {
		User u = new User();
		u.setSecurity_question("lmn");
		assertEquals(u.getSecurity_question(), "lmn");
	}
	
	/**Test to verify the functionalities for getting a user's security_question*/
	@Test
	public void testGetSecurityQuestion() {
		User u = new User();
		u.setSecurity_question("lmn");
		String result=u.getSecurity_question();
		assertEquals(result, "lmn");
	}
	
	/**Test to verify the functionalities for assigning a user's answer to security_question*/
	@Test
	public void testSetSecurityAnswer() {
		User u = new User();
		u.setAnswer("pqr");
		assertEquals(u.getAnswer(), "pqr");
	}
	
	/**Test to verify the functionalities for getting a user's answer to security_question*/
	@Test
	public void testGetSecurityAnswer() {
		User u = new User();
		u.setAnswer("pqr");
		String result=u.getAnswer();
		assertEquals(result, "pqr");
	}
	

	

}
