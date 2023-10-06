package com.cg.qgs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.UserRole;
import com.cg.qgs.utility.JDBCUtility;

public class QGSDaoAdminImpl implements IQGSDaoAdmin {

	public QGSDaoAdminImpl() {}
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	static Logger logger = Logger.getLogger(QGSDaoAdminImpl.class);

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoAdmin#GetUserName()
	 * Get list of existing users
	 */
	@Override
	public List<String> GetUserName() throws QGSException {
		
		List<String> list= new ArrayList<String>();
		connection= JDBCUtility.getConnection();
		logger.info("connection established");
		try {
			statement = connection.prepareStatement(QueryMapper.GET_USERNAME_QUERY);
			logger.debug("statement created");
			resultSet= statement.executeQuery();
			logger.debug("statement executed");
			while(resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			
		} catch (SQLException e) {
			logger.error("Problem while getting usernames");
			throw new QGSException("Problem while getting usernames");
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error("Problem while closing prepared statement");
				throw new QGSException("Problem while closing prepared statement");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Problem while closing connection");
				throw new QGSException("Problem while closing connection");
			}
		}
		
		return list;
		
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoAdmin#InsertUser(com.cg.qgs.model.UserRole)
	 * For profile insertion into database
	 */
	@Override
	public void InsertUser(UserRole userRole) throws QGSException {
		
		connection= JDBCUtility.getConnection();
		logger.info("connection established");
		int result;
		try {
			statement= connection.prepareStatement(QueryMapper.INSERT_USER_QUERY);
			logger.info("Statement created");
			statement.setString(1, userRole.getUserName());
			statement.setString(2, userRole.getPassword());
			statement.setString(3, userRole.getRoleCode());
			result= statement.executeUpdate();
			logger.info("Statement executed");
			connection.commit();
			logger.info(result +"users inserted");
			System.out.println(result +"users inserted");
			
		} catch (SQLException e) {
			logger.error("Problem while inserting user credentials");
			throw new QGSException("Problem while inserting user credentials");
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error("Problem while closing prepared statement");
				throw new QGSException("Problem while closing prepared statement");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Problem while closing connection");
				throw new QGSException("Problem while closing connection");
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoAdmin#generateReport(long, long)
	 * Get all data for generate detailed report
	 * 
	 */
	@Override
	public void generateReport(long accountNum, long policy_number) throws QGSException {
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		String insuredName ="";
		String insuredStreet ="" ;
		String insuredCity ="";
		String insuredState ="";
		int insuredZip =0;
		String business_seg ="";
		double premium =0;
		List<String> questions = new ArrayList<>();
		List<String> answers = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement(QueryMapper.GENERATE_POLICY_REPORT1_QUERY);
			logger.debug("statement created");
			
			statement.setLong(1,accountNum);
			resultSet = statement.executeQuery();
			logger.debug("statement executed");
			resultSet.next();
			{
				insuredName = resultSet.getString(1);
				insuredStreet = resultSet.getString(2);
				insuredCity = resultSet.getString(3);
				insuredState = resultSet.getString(4);
				insuredZip = resultSet.getInt(5);
				business_seg = resultSet.getString(6);
				

			}
			statement = connection.prepareStatement(QueryMapper.GET_POLICY_DETAILS_QUERY);
			logger.debug("statement created 2");
			statement.setLong(1,policy_number);
			resultSet = statement.executeQuery();
			logger.debug("statement executed 2");
			resultSet.next();
			premium = resultSet.getDouble(2);
			
			
			
			statement = connection.prepareStatement(QueryMapper.GENERATE_POLICY_REPORT2_QUERY);
			logger.debug("statement created 3");
			statement.setLong(1,policy_number);
			resultSet = statement.executeQuery();
			logger.debug("statement executed 3"); 
			resultSet.next();
			while(resultSet.next())
			{
				String question = resultSet.getString(1);
				String answer = resultSet.getString(2);
				answers.add(answer);
				questions.add(question);
				logger.debug("Result objects are added");
			}
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new QGSException("Problem while getting policy row" + e);
		}finally {
			try {
				logger.debug("before closing resultset");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problem while closing resultset");
			}
			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problem while closing connection");
			}
		}
		
		
		System.out.println("--------The user details are------- ");
		System.out.println("Insured name: "+ insuredName);
		System.out.println("Insured street: "+ insuredStreet);

		System.out.println("Insured city: "+ insuredCity);
		System.out.println("Insured state: "+ insuredState);
		System.out.println("Insured person zipcode: "+ insuredZip);
		System.out.println("Insured person's business segment: " + business_seg);
		
		System.out.println("--------Policy details are--------");
		System.out.println("Policy premium "+ premium);
		System.out.println("--------Policy questions and coverage as below--------");
		int i =0;
		for(String question: questions)
		{
			System.out.println("Question: " +question);
			System.out.println("Coverage: " + answers.get(i));
			i++;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoAdmin#quickViewPolicyAdmin()
	 * View policy data retrieval from tables
	 */
	@Override
	public List<Policy> quickViewPolicyAdmin() throws QGSException {
		List<Policy> policies = new ArrayList<Policy>();
		
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		
		try {
			statement = connection.prepareStatement(QueryMapper.VIEW_POLICY_ADMIN_QUERY);
			logger.debug("statement created");
			
			resultSet = statement.executeQuery();
			logger.debug("statement executed");
			while(resultSet.next())
			{
				
				long policyNumber = (resultSet.getLong(1));
				double policyPremium = (resultSet.getDouble(2));
				long accountNumber = (resultSet.getLong(3));
				
				Policy policy2 = new Policy(policyNumber, policyPremium, accountNumber);
				
				policies.add(policy2);
				logger.info("Result object is added");
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new QGSException("Problem while getting policy row");
		}finally {
			try {
				logger.debug("before closing resultset");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problem while closing resultset");
			}
			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problem while closing connection");
			}
		}
		
		return policies;
	}


}
