/**
 * 
 */
package com.tcs.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
@Repository
public class UserManagementDAOImpl implements UserManagementDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = LoggerFactory.getLogger(UserManagementDAOImpl.class);
	
	/*
	 * user logIn credential Details will be captured
	 * @Param user object
	 * @Throws
	 */
	@Override
	@Transactional
	public String AddUser(UserManagement user, long id, int i, String status)
	{
		Date date = new Date();
		String Sql = "update usermanagement set username= ?, password= ?, status= ?,Logintime = ? where userid =? and roleId =?";
		jdbcTemplate.update(Sql,user.getUsername(),user.getPassword(),status,date,id,i);
		return "Successful";
	}

}
