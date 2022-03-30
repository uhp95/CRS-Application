/**
 * 
 */
package com.tcs.model;

/**
 * @author springuser05
 *
 */
public class UserManagement {
	
	private String username;
	private String password;
	private int roleId;
	private String status;
	private long studentid;
	
	
	
	
	public UserManagement(String username, String password, int roleId, String status, long studentid) {
		super();
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.status = status;
		this.studentid = studentid;
	}




	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}




	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}




	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}




	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}




	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}




	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}




	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}




	/**
	 * @return the studentid
	 */
	public long getStudentid() {
		return studentid;
	}




	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}




	public UserManagement()
	{
		
	}

}
