package com.tcs.model;

public class Grades {
	
	private String grade;
	private int Result;
	private int Id;
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getResult() {
		return Result;
	}
	public void setResult(int result) {
		Result = result;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Grades(String grade, int result, int id) {
		super();
		this.grade = grade;
		Result = result;
		Id = id;
	}
	
	
	
	public Grades()
	{
		
	}
	
	
	
	
	
	

}
