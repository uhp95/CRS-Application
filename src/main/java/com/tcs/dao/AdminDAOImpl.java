/**
 * 
 */
package com.tcs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.mapper.ProfessorMapping;
import com.tcs.mapper.StudentMapping;
import com.tcs.model.AdminApproval;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.ProfessorCourses;
import com.tcs.model.Student;
import com.tcs.model.StudentEnrollment;

/**
 * @author springuser05
 *
 */
@Repository
public class AdminDAOImpl implements AdminDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	private static List<Student> students;
	{
		students = new ArrayList();
	}
	private static List<StudentEnrollment> studentEnrollment;
	{
		studentEnrollment = new ArrayList();
	}
	private static List<Professor> prof;
	{
		prof = new ArrayList();
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
	
	/*
	 * Admin will register professor details through UI and registered professor status will beset to 'In Active' until username and password is set.
	 * @Param professor object
	 * @Throws
	 */
	@Override
	@Transactional
	public String professorRegister(Professor professor) {
		
		Date date = new Date();
		String Sql = "insert into professor (name,mobile,email) "
				+ "values(?,?,?)";	
		jdbcTemplate.update(Sql,professor.getName(),professor.getMobile(),professor.getEmail());
		logger.debug("Debugging");
		String Sql1 = "select *  from professor where id in (select max(id) from professor)";
		prof=jdbcTemplate.query(Sql1, new ProfessorMapping());
		for(Professor s: prof)
		{
		
			String Sql3 = "insert into usermanagement(userid,status,roleid,logintime) values(?,?,?,?)";
			jdbcTemplate.update(Sql3,s.getId(),"In Active",2,date);
			
		}
		return "Successful";
	}
	
	/*
	 * Admin will add new courses whcih can be viewed by students.
	 * @Param course object
	 * @Throws
	 */
	@Override
	@Transactional
	public String addCourseByAdmin(Courses course)
	{
		String Sql = "insert into courses(name,description) values(?,?)";
		jdbcTemplate.update(Sql,course.getName(),course.getDescription());
		logger.debug("value",jdbcTemplate.update(Sql,course.getName(),course.getDescription()));
		
		return "Successful";
	}
	
	/*
	 * Add New Grades By Admin which will be available for professors to grade the students.
	 * @Param grade object
	 * @Throws
	 */

	@Override
	@Transactional
	public String addGradesByAdmin(Grades grade) {
	
		String Sql = "insert into grade(grade,result,percentage) "
				+ "values(?,?,?)";
		jdbcTemplate.update(Sql,grade.getGrade(),grade.getResult(),grade.getPercent());
		return "Successful";
	}
	
	/*
	 * Remove Courses By Admin
	 * @Param course id
	 * @Throws
	 */

	@Override
	@Transactional
	public int deleteCoursesByAdmin(int id) {
		
		String Sql = "Delete from courses where id =?";
		int response = jdbcTemplate.update(Sql,id);
		return response;
	}
	
	/*
	 * Admin Approval for students, without admin approval student cannot login 
	 * @Param course
	 * @Throws
	 */
	@Override
	@Transactional
	public int studentApprovalByAdmin(int id) {
		
		String Sql = "update Adminapproval set status =? where studentid =?";
		int response = jdbcTemplate.update(Sql,"Approved",id);
		String Sql1 = "update usermanagement set status = ? where userid =?";
		jdbcTemplate.update(Sql1,"Active",id);
		return response;
	}
		

}
