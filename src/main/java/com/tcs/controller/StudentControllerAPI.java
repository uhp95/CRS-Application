package com.tcs.controller;

/*
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
*/

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dao.UserDAOImpl;
import com.tcs.mapper.CourseMapping;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.Student;
import com.tcs.model.StudentGrades;




@Controller
@RestController
public class StudentControllerAPI {

	@Autowired
	private UserDAOImpl crsdao;
	
	/*
	 * Register Student Details
	 * @Param student
	 * @Throws
	 */
	@RequestMapping(value = "/StudentRegistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  StudentRegister(@RequestBody Student students)
	{
		String response = null;
		response =  crsdao.studentRegister(students);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Details added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	
	/*
	 * Register Professor Details
	 * @Param student
	 * @Throws
	 */
	@RequestMapping(value = "/ProfessorRegistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  ProfessorRegister(@RequestBody Professor professor)
	{
		String response = null;
		response =  crsdao.professorRegister(professor);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Details added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	

	
	/*
	 * Payment Details
	 * @Param fee
	 * @Throws
	 */
	@RequestMapping(value ="/PayFee/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity Fee(@RequestBody PayFee fee, @PathVariable("id") long id)
	{
		String response = null;
		response = crsdao.fees(fee);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Loading...",HttpStatus.OK);
		}
		
		 return new ResponseEntity("Server Busy. Try Again Later",HttpStatus.NOT_FOUND);
	}
	
	/*
	 * Add New Courses
	 * @Param course
	 * @Throws
	 */
	@RequestMapping(value = "/AddCourses", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddCourses(@RequestBody Courses course)
	{
		String response = null;
		response =  crsdao.addCourse(course);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Course added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	
	
	
	
	/*
	 * View Courses
	 * @Param id
	 * @Throws
	 */
	
	@RequestMapping(value = "/AddCourse/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity ViewCourse(@PathVariable("id") long id)
	{
		if(crsdao.viewCourse(id).equals(null)) 
		{
			return new ResponseEntity("No Student Exists",HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity("Courses Added Successfully",HttpStatus.OK);
	}
	
	/*
	 * Fetch Professor Details for course
	 * @Param course
	 * @Throws
	 */
	@RequestMapping(value = "/FetchCourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  fetchCourse(@PathVariable("id") int id)
	{
		
		List<List<Professor>> professor =  crsdao.fetchCourse(id);
		if(professor.isEmpty())
		{
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		}
		
		else
		return new ResponseEntity(professor,HttpStatus.OK) ;
		

	}
	
	
	/*
	 * Fetch Course Details
	 * @Param course
	 * @Throws
	 */
	@RequestMapping(value = "/FetchCourse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  fetchCourse()
	{
		
		List<Courses> course =  crsdao.fetchCourse();
		if(course.isEmpty())
		{
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		}
		
		else
		return new ResponseEntity(course,HttpStatus.OK) ;
		

	}
	

	/*
	 * Delete Course Details
	 * @Param courseId
	 * @Throws
	 */
	@RequestMapping(value = "/DeleteCourse/{id}/{courseId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  DeleteCourse(@PathVariable("id") int id, @PathVariable("courseId") int courseId) throws NullPointerException
	{
		int response = crsdao.deletecourse(id,courseId);
		
		if(response == 0)
		{
			
			return new ResponseEntity("Already Deleted",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			return new ResponseEntity("Course Deleted Successfully",HttpStatus.OK) ;
		
		
		
	}
	
	
	/*
	 * Add grades to students by professor
	 * @Param grades
	 * @Throws
	 */
	@RequestMapping(value = "/AddGrades/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddGrades(@PathVariable("id") int id,@RequestBody StudentGrades grades) throws NullPointerException
	{
		
		int response = crsdao.addGrades(id,grades);
		
		if(response == 0)
		{
			
			return new ResponseEntity("Grade not set. Please try again...",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			return new ResponseEntity("Grade is set for student",HttpStatus.OK) ;
		
		
		
	}
	
	
	/*
	 * View grades by students
	 * @Param grades
	 * @Throws
	 */
	@RequestMapping(value = "/ViewGrades/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddGrades(@PathVariable("id") int id) throws NullPointerException
	{
		
		List<Grades> response = crsdao.viewgrades(id);
		
		if(response.isEmpty())
		{
			
			return new ResponseEntity("Grade not set. Please try again...",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			for (Grades g: response)
			{
			return new ResponseEntity("You have Scored "+g.getGrade()+" with percentage in between "+g.getPercent(),HttpStatus.OK) ;
			}
		
		return null;
		
	}


}
