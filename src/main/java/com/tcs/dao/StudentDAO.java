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

/**
 * @author springuser05
 *
 */
public interface StudentDAO {
	
	
	public String studentRegister(Student student);	
	public String professorRegister(Professor professor);	
	public List fetchCourse(); 
	public int deletecourse(int courseId,int id);
	public float viewgrades(int id);
	public String fees(PayFee fee);
	public String viewCourse(long id);
	
}
