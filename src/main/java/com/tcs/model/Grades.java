package com.tcs.model;

public class Grades {
	
	private String grade;
	private String Result;
	private int Id;
	private String percent;
	
	

	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getResult() {
		return Result;
	}



	public void setResult(String result) {
		Result = result;
	}



	public int getId() {
		return Id;
	}



	public void setId(int id) {
		Id = id;
	}



	public String getPercent() {
		return percent;
	}



	public void setPercent(String percent) {
		this.percent = percent;
	}


	

	public Grades(String grade, String result, int id, String percent) {
		super();
		this.grade = grade;
		Result = result;
		Id = id;
		this.percent = percent;
	}



	public Grades()
	{
		
	}
	
	
	
	
	
	

}
