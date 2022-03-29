/**
 * 
 */
package com.tcs.model;

/**
 * @author springuser05
 *
 */
public class AdminApproval {
	
	private long studentId;
	private Boolean status;
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public AdminApproval(long studentId, Boolean status) {
		super();
		this.studentId = studentId;
		this.status = status;
	}
	
	public AdminApproval()
	{
		
	}

}
