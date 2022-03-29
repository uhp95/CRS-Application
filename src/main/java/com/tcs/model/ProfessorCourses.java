/**
 * 
 */
package com.tcs.model;

/**
 * @author springuser05
 *
 */
public class ProfessorCourses {
	
	private int courseId;
	private long professorId;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}
	public ProfessorCourses(int courseId, long professorId) {
		super();
		this.courseId = courseId;
		this.professorId = professorId;
	}
	
	public ProfessorCourses()
	{
		
	}

}
