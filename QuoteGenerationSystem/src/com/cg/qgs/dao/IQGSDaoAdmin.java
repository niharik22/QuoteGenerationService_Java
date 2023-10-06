package com.cg.qgs.dao;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.UserRole;

public interface IQGSDaoAdmin {

	List<String> GetUserName() throws QGSException;

	void InsertUser(UserRole userRole) throws QGSException;

	void generateReport(long accNum, long policy_number) throws QGSException;

	List<Policy> quickViewPolicyAdmin() throws QGSException; 
	
}
