/**
 * 
 */
package com.tcs.dao;

import java.util.List;

import com.tcs.model.Student;
import com.tcs.model.StudentGrades;
import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
public interface ProfessorDAO {
	
	public List<List<Student>> fetchStudents(long id,UserManagement user);
	public int addGradesByProfessor(int id, StudentGrades grades);

}
