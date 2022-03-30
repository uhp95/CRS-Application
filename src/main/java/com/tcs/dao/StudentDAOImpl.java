package com.tcs.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcs.mapper.AdminApprovalMapping;
import com.tcs.mapper.CourseMapping;
import com.tcs.mapper.GradeMapping;
import com.tcs.mapper.ProfessorCourseMapping;
import com.tcs.mapper.ProfessorMapping;
import com.tcs.mapper.StudentEnrollementMapping;
import com.tcs.mapper.StudentGradeMapping;
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



@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);
	
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
	
	private static List<Grades> grade;
	{
		grade = new ArrayList();
	}

	private static List<List<Grades>> grades;
	{
		grades = new ArrayList();
	}
	
	private static List<PayFee> fees;
	{
		fees = new ArrayList();
	}
	
	private static List<StudentGrades> studentgrade;
	{
		studentgrade = new ArrayList();
	}
	
	private static List<UserManagement> users;
	{
		users = new ArrayList();
	}
	
	
	/*
	 * Student will register his/her own details
	 * @Param student object
	 * @Throws
	 */
	@Override
	@Transactional
	public   String studentRegister(Student student) {
		
		Date date = new Date();
		String Sql = "insert into Studentregistration (firstname,lastname,address,email,mobile,DoB) "
				+ "values(?,?,?,?,?,?)";	
		jdbcTemplate.update(Sql, student.getFirstName(),student.getLastName(),student.getAddress(),student.getEmailId(),student.getMobileNo(),student.getDob());
		logger.debug("Debugging");
		String Sql1 = "select *  from studentregistration where id in (select max(id) from studentregistration)";
		students=jdbcTemplate.query(Sql1, new StudentMapping());
		for(Student s: students)
		{
			String Sql2 = "insert into AdminApproval (studentid,status) values(?,?)";
			jdbcTemplate.update(Sql2,s.getId(),"Not Approved");
			String Sql3 = "insert into usermanagement(userid,status,roleid,logintime) values(?,?,?,?)";
			jdbcTemplate.update(Sql3,s.getId(),"In Active",3,date);
			
		}
		
		
		
		return "Successful";
			
	}
	

	/*
	 * Fetch  Details of course By Students 
	 * @Param
	 * @Throws
	 */
	@Override
	@Transactional
	public List<List<Professor>> fetchCourse(int id,UserManagement user) 
	{
		professor.clear();
		ProfessorCourseMapping r = new ProfessorCourseMapping();
		String Sql3 = "select * from usermanagement where userid= "+id+" and roleid="+3;
		users = jdbcTemplate.query(Sql3, new UserManagementMapping());
		for(UserManagement u : users)
		{
			if(u.getUsername().equals((user.getUsername())))
			{
				if(u.getPassword().equals(user.getPassword()))
				{
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
					
				}
				else 
				{
					professor.clear();
				}
			}
			else 
			{
				professor.clear();
			}
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
	 * Drop Courses  By Students
	 * @Param courseId and Student Id
	 * @Throws
	 */
	@Override
	@Transactional

	public int deletecourse(int id, int courseId) 
	{
		String Sql = "SELECT * FROM studentregistration where id = " + id;
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
	 * fetches courses details enrolled by students
	 * @Param student id
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
	@Override
	@Transactional
	public Map viewgrades(int id) 
	{
		Map m = new HashMap();
		String Sql = "select * from studentgrade where studentid ="+ id;
		studentgrade = jdbcTemplate.query(Sql, new StudentGradeMapping());
		for(StudentGrades s: studentgrade)
		{
			String Sql1 = "select * from grade where id ="+s.getGradeId();
			grade=jdbcTemplate.query(Sql1, new GradeMapping());
			m.put(s.getSem(), grades.add(grade));
		}
		return m;
	}

	/*
	 * Student payment details for the courses applied
	 * @Param PayFee object and student id 
	 * @Throws
	 */
	@Override
	@Transactional
	public int fees(PayFee fee,long id)
		{
		Date date= new Date();
			String Sql = "insert into payment(amount,userid,DateOfPayment,ModeOfPayment,CardType,CardNumber) "
					+ "values(?,?,?,?,?,?)";
			int inserted = jdbcTemplate.update(Sql,fee.getAmount(),id,date,fee.getModeOfPayment(),fee.getCardType(),fee.getCardNumber());
			return inserted;
			
		}
		
	

}
