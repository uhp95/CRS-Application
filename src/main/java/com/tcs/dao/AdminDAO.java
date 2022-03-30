/**
 * 
 */
package com.tcs.dao;

import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.Professor;

/**
 * @author springuser05
 *
 */
public interface AdminDAO {
	
	public String professorRegister(Professor professor);
	public String addCourseByAdmin(Courses course);
	public String addGradesByAdmin(Grades grade);
	public int deleteCoursesByAdmin(int id);
	public int studentApprovalByAdmin(int id);

}
