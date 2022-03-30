/**
 * 
 */
package com.tcs.controller;

import java.util.List;

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

import com.tcs.dao.ProfessorDAOImpl;
import com.tcs.dao.StudentDAOImpl;
import com.tcs.model.Student;
import com.tcs.model.StudentGrades;
import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */

@Controller
@RestController
public class ProfessorControllerAPI {
	
	@Autowired
	private ProfessorDAOImpl crsdao;
	
	/*
	 * Fetch Student Details for course will be used by professor
	 * @Param professor id
	 * @Throws
	 */
	@RequestMapping(value = "/FetchStudentDetails/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  fetchCourse(@PathVariable("id") long id, @RequestBody UserManagement user)
	{
		
		List<List<Student>> student =  crsdao.fetchStudents(id,user);
		if(student.isEmpty())
		{
			return new ResponseEntity("Invalid Credentials",HttpStatus.NOT_FOUND) ;
		}
		
		else
		return new ResponseEntity(student,HttpStatus.OK) ;
		

	}
	
	/*
	 * Add grades to students by professor
	 * @Param grades and professor id
	 * @Throws
	 */
	@RequestMapping(value = "/AddGradesByProfessor/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  AddGrades(@PathVariable("id") int id,@RequestBody StudentGrades grades) throws NullPointerException
	{
		
		int response = crsdao.addGradesByProfessor(id,grades);
		
		if(response == 0)
		{
			
			return new ResponseEntity("Grade not set. Please try again...",HttpStatus.NOT_FOUND) ;
		}
		
		else 
			return new ResponseEntity("Grade is set for student",HttpStatus.OK) ;
		
		
		
	}

}
