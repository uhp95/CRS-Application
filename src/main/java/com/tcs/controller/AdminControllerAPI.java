/**
 * 
 */
package com.tcs.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dao.AdminDAOImpl;
import com.tcs.dao.StudentDAOImpl;
import com.tcs.model.Courses;
import com.tcs.model.Grades;
import com.tcs.model.Professor;

/**
 * @author springuser05
 *
 */
@Controller
@RestController
public class AdminControllerAPI {
	
	@Autowired
	private AdminDAOImpl crsdao;
	
	/*
	 * Admin will register professor details through UI and registered professor status will beset to 'In Active' until username and password is set.
	 * @Param professor object
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
	 * Admin will add new courses whcih can be viewed by students.
	 * @Param course object
	 * @Throws
	 */
	@RequestMapping(value = "/AddCourses", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddCoursesByAdmin(@RequestBody Courses course)
	{
		
		String response = null;
		response =  crsdao.addCourseByAdmin(course);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Course added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	
	/*
	 * Add New Grades By Admin which will be available for professors to grade the students.
	 * @Param grade object
	 * @Throws
	 */
	@RequestMapping(value = "/AddGradesByAdmin", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddCourses(@RequestBody Grades grade)
	{
		
		String response = null;
		response =  crsdao.addGradesByAdmin(grade);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Grade added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	
	/*
	 * Remove Courses By Admin
	 * @Param course
	 * @Throws
	 */
	@RequestMapping(value = "/DeleteCourse/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  DeleteCoursesByAdmin(@PathVariable("id") int id)
	{
		
	
		int response =  crsdao.deleteCoursesByAdmin(id);
		
		if(response==0)
		{
			
			return new ResponseEntity("No Such Course Exists",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			return new ResponseEntity("Course deleted Successfully",HttpStatus.OK);
			
		
	}
	
	/*
	 * Admin Approval for students, without admin approval student cannot login 
	 * @Param course
	 * @Throws
	 */
	@RequestMapping(value = "/AdminApproval/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  StudentApprovalByAdmin(@PathVariable("id") int id)
	{
		
	
		int response =  crsdao.studentApprovalByAdmin(id);
		
		if(response==0)
		{
			
			return new ResponseEntity("No Student with Id Exists",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			return new ResponseEntity("Approved",HttpStatus.OK);
			
		
	}

}
