/**
 * 
 */
package com.tcs.model;

/**
 * @author springuser05
 *
 */
public class StudentGrades {
	
	private long studentId;
	private int grade_id;
	private long professor_id;
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public long getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(long professor_id) {
		this.professor_id = professor_id;
	}
	public StudentGrades(long studentId, int grade_id, long professor_id) {
		super();
		this.studentId = studentId;
		this.grade_id = grade_id;
		this.professor_id = professor_id;
	}
	
	
public StudentGrades()
{}
	

}
