package com.tcs.controller;

import java.util.HashMap;

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
import java.util.Map;

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

import com.tcs.dao.StudentDAOImpl;
import com.tcs.mapper.CourseMapping;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.PayFee;
import com.tcs.model.Professor;
import com.tcs.model.Student;
import com.tcs.model.StudentGrades;
import com.tcs.model.UserManagement;




@Controller
@RestController
public class StudentControllerAPI {

	@Autowired
	private StudentDAOImpl crsdao;
	
	/*
	 * Student will register his/her own details
	 * @Param student object
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
	 * Student payment details for the courses applied
	 * @Param PayFee object and student id 
	 * @Throws
	 */
	@RequestMapping(value ="/PayFee/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity Fee(@RequestBody PayFee fee, @PathVariable("id") long id)
	{
		
		int response = crsdao.fees(fee,id);
		if(response == 0)
		{
			
			 return new ResponseEntity("Server Busy. Try Again Later",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity("Loading...",HttpStatus.OK);
	}
	
	
	/*
	 * fetches courses details enrolled by students
	 * @Param student id
	 * @Throws
	 */
	
	@RequestMapping(value = "/AddCourse/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity AddCourseByStudent(@PathVariable("id") long id)
	{
		if(crsdao.viewCourse(id).equals(null)) 
		{
			return new ResponseEntity("No Student Exists",HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity("Courses Added Successfully",HttpStatus.OK);
	}
	
	
	/*
	 * Shows Professor Details for course will be us used by students
	 * @Param professor id
	 * @Throws
	 */
	@RequestMapping(value = "/FetchProfessor/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  fetchCourse(@PathVariable("id") int id, @RequestBody UserManagement user)
	{
		
		List<List<Professor>> professor =  crsdao.fetchCourse(id,user);
		if(professor.isEmpty())
		{
			return new ResponseEntity("Invalid Credentials",HttpStatus.NOT_FOUND) ;
		}
		
		else
		return new ResponseEntity(professor,HttpStatus.OK) ;
		
	}
	
	
	/*
	 * Fetch  Details of course By Students 
	 * @Param 
	 * @Throws
	 */
	@RequestMapping(value = "/FetchCourseDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
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
	 * Drop Courses  By Students
	 * @Param courseId and Student Id
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
	 * View grades by students
	 * @Param grades
	 * @Throws
	 */
	@RequestMapping(value = "/ViewGrades/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddGrades(@PathVariable("id") int id) throws NullPointerException
	{
		
		Map response = new HashMap<String,List<List<Grades>>>();
				
			response=	crsdao.viewgrades(id);
		
		if(response.isEmpty())
		{
			
			return new ResponseEntity("Grade not set. Please try again...",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			//for (Map.Entry<String,List<List<Grades>>> e: response.entrySet())
			{
				
				//	return new ResponseEntity("You have Scored "+e.getGrade()+" with percentage in between "+e.getPercent()+" in "+e.ge,HttpStatus.OK) ;
				
			
			}
		
		return null;
		
	}
	
	
	

	
	
	
	
	

}
