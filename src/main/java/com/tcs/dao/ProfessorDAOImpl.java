/**
 * 
 */
package com.tcs.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.mapper.ProfessorCourseMapping;
import com.tcs.mapper.StudentEnrollementMapping;
import com.tcs.mapper.StudentMapping;
import com.tcs.mapper.UserManagementMapping;
import com.tcs.model.AdminApproval;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.ProfessorCourses;
import com.tcs.model.Student;
import com.tcs.model.StudentEnrollment;
import com.tcs.model.StudentGrades;
import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
@Repository
public class ProfessorDAOImpl implements ProfessorDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = LoggerFactory.getLogger(ProfessorDAOImpl.class);
	
	private static List<Student> students;
	{
		students = new ArrayList();
	}
	private static List<StudentEnrollment> studentEnrollment;
	{
		studentEnrollment = new ArrayList();
	}
	
	
	private static List<List<Student>> studentsDetails;
	{
		studentsDetails = new ArrayList();
	}
	private static List<ProfessorCourses> professorcourses;
	{
		professorcourses = new ArrayList();
	}
	
	private static List<Courses> courses;
	{
		courses = new ArrayList();
	}
	
	private static List<List<Professor>> professor;
	{
		professor = new ArrayList();
	}
	
	private static List<Grades> grades;
	{
		grades = new ArrayList();
	}

	private static List<PayFee> fees;
	{
		fees = new ArrayList();
	}
	private static List<AdminApproval> approval;
	{
		approval = new ArrayList();
	}
	
	private static List<UserManagement> users;
	{
		users = new ArrayList<UserManagement>();
	}
	
	/*
	 * Fetch Student Details for course will be used by professor
	 * @Param professor id
	 * @Throws
	 */
	@Override
	@Transactional
	public List<List<Student>> fetchStudents(long id,UserManagement user) throws NullPointerException
	{
		String Sql3= "select * from usermanagement where userid =" +id+" and roleid="+2;
		users = jdbcTemplate.query(Sql3, new UserManagementMapping());
		System.out.println(users+" "+user.getUsername());
		
		for(UserManagement u: users)
		{ 
			System.out.println(user.getUsername());
	
				if(u.getUsername().toString().equals(user.getUsername().toString()))
							{
								if(u.getPassword().equals(user.getPassword()))
								{
									String Sql = "select * from professorcourses where professorid =" +id;
									professorcourses = jdbcTemplate.query(Sql,new ProfessorCourseMapping());
									
									for(ProfessorCourses p: professorcourses)
									{
										String Sql1 = "select * from studentenrollment where courseId ="+ p.getCourseId();
										studentEnrollment = jdbcTemplate.query(Sql1, new StudentEnrollementMapping());
										for (StudentEnrollment s: studentEnrollment)
										{
											String Sql2 = "select * from studentregistration where Id =" + s.getStudentId();
											students = jdbcTemplate.query(Sql2, new StudentMapping());
											studentsDetails.add(students);
										}
									}
									return studentsDetails;
								}
								else return null;
							}
				else
			return null;
	
		}
		
		return null;
		
	
	}
	
	/*
	 * Add grades to students by professor
	 * @Param grades and professor id
	 * @Throws
	 */

	@Override
	@Transactional
	public int addGradesByProfessor(int id, StudentGrades grades)
	{
		String Sql = "insert into studentgrade (studentid,gradeid,sem,professorid)"
				+ "values(?,?,?,?)";
		int add = jdbcTemplate.update(Sql,grades.getStudentId(),grades.getGradeId(),grades.getSem(),id);
				
		return add;
	}
	
}
