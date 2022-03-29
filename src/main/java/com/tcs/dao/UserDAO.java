/**
 * 
 */
package com.tcs.dao;



import java.sql.ResultSet;
import java.util.List;

import com.tcs.mapper.CourseMapping;
import com.tcs.model.Courses;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.Student;
import com.tcs.model.StudentGrades;

/**
 * @author springuser05
 *
 */
public interface UserDAO {
	
	
	public String studentRegister(Student student);	
	public String professorRegister(Professor professor);	
	public List fetchCourse(int id); 
	public int deletecourse(int courseId,int id);
	public List viewgrades(int id);
	public String fees(PayFee fee);
	public String viewCourse(long id);
	public String addCourse(Courses course);
	public List fetchCourse();
	public int addGrades(int id, StudentGrades grades);
	
}
