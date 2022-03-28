/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.Student;

/**
 * @author springuser05
 *
 */
public class StudentMapping implements RowMapper<Student>{
	
	@Override
	public Student mapRow(ResultSet r, int rows) throws SQLException
	{
		Student student = new Student();
		student.setAddress(r.getString("address"));
		student.setDob(r.getString("dob"));
		student.setEmailId(r.getString("email"));
		student.setFirstName(r.getString("first_name"));
		student.setLastName(r.getString("last_name"));
		student.setId(r.getLong("id"));
		student.setMobileNo(r.getLong("mobile"));
		return student;
	}
	
	
}
