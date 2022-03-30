/**
 * 
 */
package com.tcs.dao;



import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.tcs.mapper.CourseMapping;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.Student;
import com.tcs.model.StudentGrades;
import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
public interface StudentDAO

{
	public String studentRegister(Student student);	
	public List<List<Professor>> fetchCourse(int id,UserManagement user); 
	public int deletecourse(int courseId,int id);
	public Map viewgrades(int id);
	public int fees(PayFee fee, long id);
	public String viewCourse(long id);
	public List fetchCourse();
	
}
