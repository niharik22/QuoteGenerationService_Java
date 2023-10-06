package com.cg.qgs.dao;

public interface QueryMapper {

	String GET_USER_PASSWORD_QUERY = "SELECT * FROM USER_ROLE WHERE USER_NAME=?";

	String GET_ROLE_CODE_QUERY = "SELECT ROLE_CODE FROM USER_ROLE WHERE USER_NAME=? ";

	String ACCOUNT_CREATION_QUERY = "INSERT INTO ACCOUNTS VALUES(account_number_seq.nextval,?,?,?,?,?,?,?,?)";

	String GET_POLICY_QUESTIONS_QUERY = "SELECT * FROM POLICY_QUESTIONS WHERE BUS_SEG_ID=?";

	String GET_ACCOUNT_NUMBER_QUERY = "SELECT account_number_seq.currval FROM DUAL";

	String GET_BUSINESS_SEGMENT_QUERY = "SELECT bus_seg_id, bus_seg_name FROM business_segment";

	String GET_USERNAME_QUERY= "SELECT user_name FROM USER_ROLE ";
	
	String INSERT_USER_QUERY="INSERT INTO user_role VALUES(?,?,?)";
	
	String CREATE_POLICY_QUERY="INSERT INTO policy VALUES(policy_number_seq.nextval,?,?)";
	
	String GET_POLICY_NUMBER_QUERY = "SELECT policy_number_seq.currval FROM DUAL";
	
	String CREATE_POLICY_DETAILS_QUERY="INSERT INTO policy_details VALUES(?,?,?)";

	String FETCH_BUS_SEGNAME_QUERY = "SELECT business_segment FROM accounts WHERE account_number=?";
	
	String GET_ACCOUNT_HOLDERS_QUERY= "SELECT user_name FROM ACCOUNTS ";
	
	String GET_ACCOUNT_NUMBERS_QUERY= "SELECT ACCOUNT_NUMBER FROM ACCOUNTS ";
	
	String VIEW_POLICY_QUERY = "SELECT * FROM policy WHERE account_number=(SELECT account_number FROM accounts WHERE user_name=?)";
	
	String VIEW_POLICY_AGENT_QUERY = "SELECT * FROM policy WHERE account_number IN(SELECT account_number FROM accounts WHERE POLICY_CREATOR_ID=?)";

	String VIEW_POLICY_ADMIN_QUERY = "SELECT * FROM policy";

	String GENERATE_POLICY_REPORT1_QUERY = "SELECT a.insured_name,a.insured_street,a.insured_city,a.insured_state,a.insured_zip, a.business_segment FROM accounts a WHERE a.account_number = ?";
	String GET_POLICY_DETAILS_QUERY = "SELECT policy_number, policy_premium FROM policy WHERE policy_number = ?";
	String GENERATE_POLICY_REPORT2_QUERY = "SELECT q.pol_ques_desc, p.answer FROM policy_details p JOIN policy_questions q ON q.pol_ques_id = p.question_id WHERE p.policy_number= ?";

	String GET_AGENT_ID_QUERY = "SELECT POLICY_CREATOR_ID FROM accounts WHERE username =?";

	String GET_USERS_UNDER_AGENT = "SELECT user_name FROM accounts WHERE POLICY_CREATOR_ID= ?";
	
	String UPDATE_POLICY_CREATOR_ID= "UPDATE  ACCOUNTS SET POLICY_CREATOR_ID=? WHERE ACCOUNT_NUMBER=?" ;
}
