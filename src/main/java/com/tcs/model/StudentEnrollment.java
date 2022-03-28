/**
 * 
 */
package com.tcs.model;

import java.sql.Date;

/**
 * @author springuser05
 *
 */
public class StudentEnrollment {
	
	private long studentId;
	private long courseId;
	private Date doe;
	
	
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public Date getDoe() {
		return doe;
	}
	public void setDoe(Date doe) {
		this.doe = doe;
	}
	public StudentEnrollment(long studentId, long courseId, Date doe) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.doe = doe;
	}
	
	public StudentEnrollment()
	{
		
	}

}
