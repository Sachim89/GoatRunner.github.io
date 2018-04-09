package com.GoatRunner.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8779683036950344716L;
	/**
	 * 
	 */
	private String name;
	private int student_id;
	private String password;
	private String phone_number;
	private String address;
	private String favourite_location;
	private String email_id;
	private String security_question;
	private String answer;

	
   
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getStudent_id() {
			return student_id;
		}
		public void setStudent_id(int student_id) {
			this.student_id = student_id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getFavourite_location() {
			return favourite_location;
		}
		public void setFavourite_location(String favourite_location) {
			this.favourite_location = favourite_location;
		}
		public String getSecurity_question() {
			return security_question;
		}
		public void setSecurity_question(String security_question) {
			this.security_question = security_question;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
			public String getEmail_id() {
			return email_id;
		}
		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}
	    
	    

}
