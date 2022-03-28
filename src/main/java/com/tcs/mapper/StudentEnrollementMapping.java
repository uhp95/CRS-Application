/**
 * 
 */
package com.tcs.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.Courses;
import com.tcs.model.StudentEnrollment;

/**
 * @author springuser05
 *
 */
public class StudentEnrollementMapping implements RowMapper<StudentEnrollment> {
	
	@Override
	public StudentEnrollment mapRow(ResultSet r, int rows) throws SQLException 
	{
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");  
		   Date date = new Date();  
		StudentEnrollment se = new StudentEnrollment();
		se.setCourseId(r.getLong("course_id"));
		se.setStudentId(r.getLong("student_id"));
		se.setDoe(r.getDate(formatter.format(date)));
		
		return se;
	}

}
