/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.Courses;

/**
 * @author springuser05
 *
 */
public class CourseMapping implements RowMapper<Courses>{
	
	@Override
	public Courses mapRow(ResultSet r, int rows) throws SQLException
	{
		Courses course = new Courses();
		course.setId(r.getInt("id"));
		course.setName(r.getString("name"));
		course.setDescription(r.getString("description"));
		return course;
	}
	

}
