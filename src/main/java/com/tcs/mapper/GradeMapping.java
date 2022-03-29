/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.Grades;
import com.tcs.model.ProfessorCourses;

/**
 * @author springuser05
 *
 */
public class GradeMapping implements RowMapper<Grades> {

	@Override
	public Grades mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Grades g = new Grades();
		g.setGrade(rs.getString("grade"));
		g.setId(rs.getInt("id"));
		g.setPercent(rs.getString("percentage"));
		g.setResult(rs.getString("result"));
		return g;
	}

}
