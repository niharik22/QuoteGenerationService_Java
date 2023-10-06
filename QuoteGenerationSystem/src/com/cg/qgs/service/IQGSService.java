package com.cg.qgs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.PolicyDetails;
import com.cg.qgs.model.PolicyQuestions;
import com.cg.qgs.model.UserRole;

public interface IQGSService {

	boolean verifyCredentials(String loginId, String password) throws QGSException;

	String getRoleCode(String loginId) throws QGSException;

	boolean checkInsuredName(String insuredName) throws QGSException;

	int createAccount(Accounts account) throws QGSException;

	List<PolicyQuestions> getPolicyQuestions(String busSegId) throws QGSException;
	
	public List<Policy> quickViewPolicy(String username) throws QGSException;
	
	public List<Policy> quickViewPolicyAgent(String username) throws QGSException;
	
	public List<Policy> quickViewPolicyAdmin() throws QGSException;
	
	public void generateReport(long accNum, long policy_num) throws QGSException;


	boolean checkInsuredStreet(String insuredStreet) throws QGSException;

	boolean checkCity(String insuredCity) throws QGSException;

	boolean checkState(String insuredState) throws QGSException;

	boolean checkInsuredZip(int insuredZip)  throws QGSException;

	HashMap<String, String> getBusinessSegmentDetails() throws QGSException;

	boolean validateBusinessSegmentName(String busSegName, Set<String> businessName) throws QGSException;

	
	
//profile creation
	void InsertUser(UserRole userRole) throws QGSException;

	boolean ValidateUserName(String userName) throws QGSException;

	boolean ValidatePassword(String password) throws QGSException;

	boolean ValidateRoleCode(String roleCode) throws QGSException;
	
	//
	
	//policy creation
	int createPolicy(Policy policy)throws QGSException;
	//
	
	//policy Detail Creation//
	long createPolicyDetails(PolicyDetails policyDetails)throws QGSException;
	
	String fetchBusinessSegName(long accountNumber) throws QGSException;

//Added//
	boolean validateUserNameForInsured(String userName) throws QGSException;

	boolean validateUserNameForAdminOrAgent(String userName) throws QGSException;

	boolean checkAccountHolder(String userName) throws QGSException;

	boolean checkAccountNumber(long accountNumber) throws QGSException;


	void updateProfileCreatorId(String loginId, long accountNumber) throws QGSException;
	
	
}
