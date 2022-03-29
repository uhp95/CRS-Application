/**
 * 
 */
package com.tcs.dao;



import java.sql.ResultSet;
import java.util.List;

import com.tcs.mapper.CourseMapping;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
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
	public int fees(PayFee fee, long id);
	public String viewCourse(long id);
	public String addCourse(Courses course);
	public List fetchCourse();
	public int addGradesByProfessor(int id, StudentGrades grades);
	public List<List<Student>> fetchStudents(long id);
	public String addGradesByAdmin(Grades grade);
	public int deleteCoursesByAdmin(int id);
	public int studentApprovalByAdmin(int id);
	
}
