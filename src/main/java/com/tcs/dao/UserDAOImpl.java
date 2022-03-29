package com.tcs.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcs.mapper.CourseMapping;
import com.tcs.mapper.GradeMapping;
import com.tcs.mapper.ProfessorCourseMapping;
import com.tcs.mapper.ProfessorMapping;
import com.tcs.mapper.StudentEnrollementMapping;
import com.tcs.mapper.StudentMapping;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.ProfessorCourses;
import com.tcs.model.Student;
import com.tcs.model.StudentGrades;



@Repository
public class UserDAOImpl implements UserDAO {
	
	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	 private static AtomicInteger i = new AtomicInteger(0);
	 private  int a;
	
	private static List<Student> students;
	{
		students = new ArrayList();
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/*
	 * Register Student Details
	 * @Param student
	 * @Throws
	 */
	@Override
	@Transactional
	public   String studentRegister(Student student) {
		
		String Sql = "insert into Student(firstname,lastname,address,email,mobile,DoB) "
				+ "values(?,?,?,?,?,?)";	
		jdbcTemplate.update(Sql, student.getFirstName(),student.getLastName(),student.getAddress(),student.getEmailId(),student.getMobileNo(),student.getDob());
		logger.debug("Debugging");
		return "Successful";
			
	}
	
	/*
	 * Register Professor Details
	 * @Param student
	 * @Throws
	 */
	@Override
	@Transactional
	public String professorRegister(Professor professor) {
		
		String Sql = "insert into professor (name,mobile,email) "
				+ "values(?,?,?)";	
		jdbcTemplate.update(Sql,professor.getName(),professor.getMobile(),professor.getEmail());
		logger.debug("Debugging");
		return "Successful";
		
	}
		

	/*
	 * Fetch Professor Details by course
	 * @Param course
	 * @Throws
	 */
	@Override
	@Transactional
	public List<List<Professor>> fetchCourse(int id) 
	{
		professor.clear();
		ProfessorCourseMapping r = new ProfessorCourseMapping();
		String Sql = "select * from professorCourses where courseid ="+id;
		professorcourses = jdbcTemplate.query(Sql, r);
		List pro = new ArrayList();
		ProfessorMapping rs = new ProfessorMapping();
		for(ProfessorCourses p: professorcourses)
		{
			String Sql1 = "select * from professor where id ="+p.getProfessorId();
			pro = jdbcTemplate.query(Sql1,rs);
			professor.add( pro);
			
		}
		
		return professor;
	}
	
	
	/*
	 * Fetch all course details 
	 * @Param course
	 * @Throws
	 */
	@Override
	@Transactional
	public List<Courses> fetchCourse()
	{
		CourseMapping c = new CourseMapping();
		String Sql = "select * from courses";
		courses = jdbcTemplate.query(Sql,c);
		return courses;
	}
	
	/*
	 * Delete Course Details
	 * @Param courseId
	 * @Throws
	 */
	@Override
	@Transactional

	public int deletecourse(int id, int courseId) 
	{
		String Sql = "SELECT * FROM student where id = " + id;
		List<Student> student =  jdbcTemplate.query(Sql, new StudentMapping());
		logger.debug("value",jdbcTemplate.query(Sql, new StudentMapping()));
		if(student.isEmpty())
		{
			return 0;
		}
		else 
		{
			int del;
			String Sql1 = "Delete from studentenrollment where studentid =? and courseid = ?" ;
			del = jdbcTemplate.update(Sql1, id,courseId);
			logger.debug("value",jdbcTemplate.update(Sql1, id,courseId));
			System.out.println(id+" "+courseId );
			if (del == 0)
			{
				return del;
			}
			else 
			{
				return 1;
			}
		}
		
	
		
	}
	
	/*
	 * Add Course Details
	 * @Param id
	 * @Throws
	 */
	@Override
	@Transactional
	public String addCourse(Courses course)
	{
		String Sql = "insert into courses(name,description) values(?,?)";
		jdbcTemplate.update(Sql,course.getName(),course.getDescription());
		logger.debug("value",jdbcTemplate.update(Sql,course.getName(),course.getDescription()));
		
		return "Successful";
	}
	

	/*
	 * View Courses enrolled by students
	 * @Param course
	 * @Throws
	 */
	@Override
	@Transactional
	public String viewCourse(long id) 
	
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");  
		   Date date = new Date(); 
		
		   
		String Sql = "SELECT * FROM studentRegistration where id = " + id;
		List<Student> student =  jdbcTemplate.query(Sql, new StudentMapping());
		logger.debug("value",jdbcTemplate.query(Sql, new StudentMapping()));
		if(student.isEmpty())
		{
			return null;
		}
		
		else 
		{
			CourseMapping r = new CourseMapping();
			String Sql1 = "select * from courses";
			courses = jdbcTemplate.query(Sql1, r);
			for(Courses c: courses)
			{
				String Sql2 = "insert into studentenrollment(studentid,courseid,dateofenrollment) "
						+ "values(?,?,?)";
				jdbcTemplate.update(Sql2,id,c.getId(),formatter.format(date));
			}
			
		}
			
		return "Successful";
		
	}
	
	/*
	 * View grades by students
	 * @Param grades
	 * @Throws
	 */

	public List viewgrades(int id) 
	{
		
		String Sql = "select * from grade where id ="+ id;
		List grade = jdbcTemplate.query(Sql, new GradeMapping());
		return grade;
	}

	/*
	 * Payment Details
	 * @Param fee
	 * @Throws
	 */

	@Override
	public String fees(PayFee fee)
		{
			fee.setTransactionId(System.currentTimeMillis());
			fees.add(fee);
			return "Successful";
			
		}
		
	
	
	/*
	 * Add grades to students by professor
	 * @Param grades
	 * @Throws
	 */
	

	@Override
	public int addGrades(int id, StudentGrades grades)
	{
		String Sql = "insert into studentgrade (studentid,gradeid,sem,professorid)"
				+ "values(?,?,?,?)";
		int add = jdbcTemplate.update(Sql,grades.getStudentId(),grades.getGradeId(),grades.getSem(),id);
				
		return add;
	}

	





	

}
