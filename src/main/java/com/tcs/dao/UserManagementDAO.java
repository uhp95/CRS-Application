/**
 * 
 */
package com.tcs.dao;

import com.tcs.model.UserManagement;

/**
 * @author springuser05
 *
 */
public interface UserManagementDAO {

	public String AddUser(UserManagement user, long id, int i,String status);
}
