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
	private int gradeId;
	private long professorId;
	private String sem;
	private int id;
	
	
	



public long getStudentId() {
		return studentId;
	}



	public void setStudentId(long student_Id) {
		this.studentId = student_Id;
	}



	public int getGradeId() {
		return gradeId;
	}



	public void setGradeId(int grade_id) {
		this.gradeId = grade_id;
	}



	public long getProfessorId() {
		return professorId;
	}



	public void setProfessorId(long professor_id) {
		this.professorId = professor_id;
	}



	public String getSem() {
		return sem;
	}



	public void setSem(String sem) {
		this.sem = sem;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



public StudentGrades(long student_Id, int grade_id, long professor_id, String sem, int id) {
		super();
		this.studentId = student_Id;
		this.gradeId = grade_id;
		this.professorId = professor_id;
		this.sem = sem;
		this.id = id;
	}



public StudentGrades()
{}
	

}
