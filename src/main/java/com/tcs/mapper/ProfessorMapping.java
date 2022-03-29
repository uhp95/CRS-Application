/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.Professor;
import com.tcs.model.ProfessorCourses;

/**
 * @author springuser05
 *
 */
public class ProfessorMapping implements RowMapper<Professor>{

	@Override
	public Professor mapRow(ResultSet r, int rows) throws SQLException
	{
		Professor pc = new Professor();
		pc.setId(r.getLong("id"));
		pc.setEmail(r.getString("email"));
		pc.setMobile(r.getLong("mobile"));
		pc.setName(r.getString("name"));
		
		return pc;
		
	}
}
