/**
 * 
 */
package com.tcs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.model.PayFee;
import com.tcs.model.ProfessorCourses;

/**
 * @author springuser05
 *
 */
public class PayFeeMapping implements RowMapper<PayFee>{

	@Override
	public PayFee mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		PayFee pf = new PayFee();
		pf.setAmount(rs.getFloat("amount"));
		pf.setCardNumber(rs.getLong("CardNumber"));
		pf.setCardType(rs.getString("CardType"));
		pf.setDop(rs.getDate("DateOfPayment"));
		pf.setModeOfPayment(rs.getString("ModeOfPayment"));
		pf.setTransactionId(rs.getLong("id"));
		return pf;
	}

}
