package com.tcs.crs.model;

public class Courses {

	private String courses;

	public Courses(String courses) {
		super();
		this.courses = courses;
	}
	
	
	public Courses()
	{
		
	}


	/**
	 * @return the courses
	 */
	public String getCourses() {
		return courses;
	}


	/**
	 * @param courses the courses to set
	 */
	public void setCourses(String courses) {
		this.courses = courses;
	}
	
	
	//toString to be used...
}
