package com.tcs.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.tcs.mapper.StudentMapping;
import com.tcs.model.AdminApproval;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.ProfessorCourses;
import com.tcs.model.Student;
import com.tcs.model.StudentEnrollment;
import com.tcs.model.StudentGrades;



@Repository
public class UserDAOImpl implements UserDAO {
	
	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/*
	 * Student will register his/her own details
	 * @Param student object
	 * @Throws
	 */
	@Override
	@Transactional
	public   String studentRegister(Student student) {
		
		String Sql = "insert into Studentregistration (firstname,lastname,address,email,mobile,DoB) "
				+ "values(?,?,?,?,?,?)";	
		jdbcTemplate.update(Sql, student.getFirstName(),student.getLastName(),student.getAddress(),student.getEmailId(),student.getMobileNo(),student.getDob());
		logger.debug("Debugging");
		String Sql1 = "select *  from studentregistration where id in (select max(id) from studentregistration)";
		//String Sql1 = "select id as studentid, '' as status from studentregistration";
		students=jdbcTemplate.query(Sql1, new StudentMapping());
		for(Student s: students)
		{
			String Sql2 = "insert into AdminApproval (studentid,status) values(?,?)";
			jdbcTemplate.update(Sql2,s.getId(),"Not Approved");
		}
		
		return "Successful";
			
	}
	
	/*
	 * Admin will register professor details
	 * @Param professor object
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
	 * Fetch Course Details for course By Students 
	 * @Param 
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
	 * Fetch Student Details for course will be used by professor
	 * @Param professor id
	 * @Throws
	 */
	@Override
	@Transactional
	public List<List<Student>> fetchStudents(long id) 
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
	 * Add New Courses By Admin
	 * @Param course
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
	public List viewgrades(int id) 
	{
		
		String Sql = "select * from grade where studentid ="+ id;
		List grade = jdbcTemplate.query(Sql, new GradeMapping());
		return grade;
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
	
	
	/*
	 * Add New Grades By Admin
	 * @Param 
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
	 * Admin approves the student
	 * @Param student id
	 * @Throws
	 */
	@Override
	@Transactional
	public int studentApprovalByAdmin(int id) {
		
		String Sql = "update Adminapproval set status =? where studentid =?";
		int response = jdbcTemplate.update(Sql,"Approved",id);
		return response;
	}

	





	

}
