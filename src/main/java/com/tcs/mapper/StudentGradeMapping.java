/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tcs.model.StudentGrades;

/**
 * @author springuser05
 *
 */
public class StudentGradeMapping implements RowMapper<StudentGrades>

{
@Override
public StudentGrades mapRow(ResultSet r, int rows) throws SQLException
{
	StudentGrades sg = new StudentGrades();
	sg.setGradeId(r.getInt("gradeid"));
	sg.setProfessorId(r.getLong("professorid"));
	sg.setSem(r.getString("sem"));
	sg.setStudentId(r.getLong("studentid"));
	
	return sg;
	
}

}
