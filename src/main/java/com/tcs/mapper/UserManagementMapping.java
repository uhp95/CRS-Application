/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
public class UserManagementMapping implements RowMapper<UserManagement>{

	@Override
	public UserManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserManagement u = new UserManagement();
		u.setPassword(rs.getString("Password"));
		u.setRoleId(rs.getInt("RoleId"));
		u.setStatus(rs.getString("Status"));
		u.setStudentid(rs.getLong("UserId"));
		u.setUsername(rs.getString("username"));
		return u;
	}

}
