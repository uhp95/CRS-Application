package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.AdminApproval;
import com.tcs.model.PayFee;

public class AdminApprovalMapping implements  RowMapper<AdminApproval>{

	@Override
	public AdminApproval mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AdminApproval ap = new AdminApproval();
		ap.setStudentId(rs.getLong("studentId"));
		ap.setStatus(rs.getBoolean("status"));
		return ap;
	}

}
