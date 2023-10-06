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
import com.cg.qgs.utility.JDBCUtility;

public class QGSDaoAgentImpl implements IQGSDaoAgent {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	
	
	static Logger logger = Logger.getLogger(QGSDaoCommonImpl.class);
	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoAgent#quickViewPolicyAgent(java.lang.String)
	 * View policy for agent of users under him
	 */
	@Override
	public List<Policy> quickViewPolicyAgent(String username) throws QGSException {
		List<Policy> policies = new ArrayList<Policy>();
		
	
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		
		
		try {
			statement = connection.prepareStatement(QueryMapper.VIEW_POLICY_AGENT_QUERY);
			logger.debug("statement created");
			
			statement.setString(1,username);
		
			resultSet = statement.executeQuery();
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
			e.printStackTrace();
			throw new QGSException("Problem while getting policy row");
		}finally {
			
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
	
	
	/**
	 * @param agentId
	 * @return List<String>
	 * @throws QGSException
	 * Method to check which users come under a particular agent
	 */
	public List<String> viewInsuredUnderAgent(String agentId) throws QGSException
	{
		List<String> accounts = new ArrayList<>();
		try {
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		
		
			statement = connection.prepareStatement(QueryMapper.GET_USERS_UNDER_AGENT);
			logger.info("statement to get users");
			statement.setString(1, agentId);
			
			resultSet = statement.executeQuery();
			logger.info("statement to get users executed");
			
			resultSet.next();
			while(resultSet.next())
			{
				String username = resultSet.getString(1);
				
				accounts.add(username);
			}
			
			
			
		}catch (SQLException e) {
			logger.error(e.getMessage());
			throw new QGSException("Problem while getting users list");
		}finally {
			
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
		
		
		
		return accounts;
	}
}
