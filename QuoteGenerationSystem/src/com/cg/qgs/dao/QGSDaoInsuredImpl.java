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

public class QGSDaoInsuredImpl implements IQGSDaoInsured{

	public QGSDaoInsuredImpl() {
	}
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	static Logger logger = Logger.getLogger(QGSDaoCommonImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoInsured#quickViewPolicy(java.lang.String)
	 * View policy of the insured user himself
	 */
	@Override
	public List<Policy> quickViewPolicy(String username) throws QGSException {
		
		List<Policy> policy = new ArrayList<Policy>();

		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		try {
			statement = connection.prepareStatement(QueryMapper.VIEW_POLICY_QUERY);
			logger.debug("statement created");
			
			statement.setString(1,username);
			resultSet = statement.executeQuery();
			logger.debug("statement executed");
			while(resultSet.next())
			{
				
				long policyNumber = (resultSet.getLong(1));
				double policyPremium = (resultSet.getDouble(2));
				long accountNumber = (resultSet.getLong(3));
				
				Policy policy2 = new Policy(policyNumber, policyPremium, accountNumber);
				
				policy.add(policy2);
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
		
	
		return policy;
	}

	

}
