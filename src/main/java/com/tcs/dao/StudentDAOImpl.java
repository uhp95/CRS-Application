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

import com.tcs.mapper.CourseMapping;
import com.tcs.mapper.StudentEnrollementMapping;
import com.tcs.mapper.StudentMapping;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.Student;



@Repository
public class StudentDAOImpl implements StudentDAO {
	
	Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	/*
	 * Register Student Details
	 * @Param student
	 * @Throws
	 */
	public   String studentRegister(Student student) {
		
		String Sql = "insert into Student(first_name,last_name,address,email,mobile,DoB) "
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
	
	
	 private static AtomicInteger i = new AtomicInteger(0);
	 private  int a;
	
	private static List<Student> students;
	{
		students = new ArrayList();
	}
	
	private static List<Courses> courses;
	{
		courses = new ArrayList();
	}
	
	private static List<Grades> grades;
	{
		grades = new ArrayList();
	}

	private static List<PayFee> fees;
	{
		fees = new ArrayList();
	}

	

	/*
	 * Add Course Details
	 * @Param course
	 * @Throws
	 */
	@Override
	@Transactional
	public List<Courses> fetchCourse() 
	{
		CourseMapping r = new CourseMapping();
		String Sql = "select * from courses";
		courses = jdbcTemplate.query(Sql, r);
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
			String Sql1 = "Delete from student_enrollment where student_id =? and course_id = ?" ;
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
	 * View Grade Details
	 * @Param id
	 * @Throws
	 */

	public float viewgrades(int id) 
	{
		
		
		
		return 0;
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
	 * View Courses
	 * @Param course
	 * @Throws
	 */
	@Override
	@Transactional
	public String viewCourse(long id) 
	
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");  
		   Date date = new Date(); 
		
		   
		String Sql = "SELECT * FROM student where id = " + id;
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
				String Sql2 = "insert into student_enrollment(student_id,course_id,date_of_enrollment) "
						+ "values(?,?,?)";
				jdbcTemplate.update(Sql2,id,c.getId(),formatter.format(date));
			}
			
		}
			
		return "Successful";
		
	}





	

}
