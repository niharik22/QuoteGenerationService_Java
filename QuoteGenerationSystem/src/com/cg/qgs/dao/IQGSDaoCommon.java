package com.cg.qgs.dao;

import java.util.HashMap;
import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.PolicyDetails;
import com.cg.qgs.model.PolicyQuestions;

public interface IQGSDaoCommon {
	
	String getCredentials(String loginId) throws QGSException;

	String getRoleCode(String loginId) throws QGSException;

	List<PolicyQuestions> getPolicyQuestions(String busSegId) throws QGSException;

	int createPolicy(Policy policy) throws QGSException;
	
	HashMap<String, String> getBusinessSegmentDetails() throws QGSException;
	
	int createAccount(Accounts account) throws QGSException;

	long createPolicyDetails(PolicyDetails policyDetails) throws QGSException;
	
	String fetchBusinessSegName(long accountNumber) throws QGSException;

	List<String> getUserNames() throws QGSException;

	List<String> getAccountHolders() throws QGSException;

	List<Long> getAccountNumbers() throws QGSException;

	void updateProfileCreatorId(String loginId, long accountNumber) throws QGSException;
}
