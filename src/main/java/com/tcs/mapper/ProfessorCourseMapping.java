/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.ProfessorCourses;
import com.tcs.model.Student;

/**
 * @author springuser05
 *
 */
public class ProfessorCourseMapping implements RowMapper<ProfessorCourses> {

	@Override
	public ProfessorCourses mapRow(ResultSet r, int rows) throws SQLException
	{
		ProfessorCourses pc = new ProfessorCourses();
		pc.setCourseId(r.getInt("courseid"));
		pc.setProfessorId(r.getLong("professorid"));
		
		return pc;
		
	}
}
