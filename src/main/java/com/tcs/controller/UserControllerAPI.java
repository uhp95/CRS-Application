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

import com.tcs.dao.StudentDAOImpl;
import com.tcs.dao.UserManagementDAOImpl;
import com.tcs.model.Professor;
import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
@Controller
@RestController
public class UserControllerAPI {

	@Autowired
	private UserManagementDAOImpl crsdao;
	
	/*
	 * professor logIn credential Details will be captured
	 * @Param user object
	 * @Throws
	 */
	@RequestMapping(value = "/UserDetails/professor/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  UserManagementProfessor(@RequestBody UserManagement user,@PathVariable("id") long id)
	{
		int i = 2;
		String status = "Active";
		String response = null;
		response =  crsdao.AddUser(user,id, i,status);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Details added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	
	/*
	 * student logIn credential Details will be captured
	 * @Param user object
	 * @Throws
	 */
	@RequestMapping(value = "/UserDetails/student/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON )
	@ResponseBody
	public ResponseEntity  UserManagemnetStudent(@RequestBody UserManagement user,@PathVariable("id") long id)
	{
		int i = 3;
		String status = "In Active";
		String response = null;
		response =  crsdao.AddUser(user,id, i,status);
		
		if(response.equals("Successful"))
		{
			return new ResponseEntity("Details added Successfully",HttpStatus.OK) ;
		}
		
		else 
			
			return new ResponseEntity("Error",HttpStatus.NOT_FOUND) ;
		
	}
	

	
}
