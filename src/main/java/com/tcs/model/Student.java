package com.tcs.model;

import java.io.Serializable;

public class Student  implements Serializable {

	
	private long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private long mobileNo;
	private String address;
	private String dob;
	
	
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}



	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	/**
	 * @return the mobileNo
	 */
	public long getMobileNo() {
		return mobileNo;
	}



	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}



	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}



	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}



	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}





	
	


	public Student(long id, String firstName, String lastName, String emailId, long mobileNo, String address,
			String dob, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
		this.dob = dob;
		
	}



	public Student()
	{
		
	}
	
	
	
	
}
