package com.cg.qgs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.PolicyDetails;
import com.cg.qgs.model.PolicyQuestions;
import com.cg.qgs.utility.JDBCUtility;

public class QGSDaoCommonImpl implements IQGSDaoCommon {

	public QGSDaoCommonImpl() {
	}

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	static Logger logger = Logger.getLogger(QGSDaoCommonImpl.class);

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getCredentials(java.lang.String)
	 * To get login credentials for verification
	 */
	@Override
	public String getCredentials(String loginId) throws QGSException {
		String password = "";

		try {
			connection = JDBCUtility.getConnection();
			logger.info("connection established");

			statement = connection.prepareStatement(QueryMapper.GET_USER_PASSWORD_QUERY);
			logger.debug("statement created");

			statement.setString(1, loginId);

			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				throw new QGSException("No UserName found");
			}
			logger.info("records selected");
			password = resultSet.getString("password");

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new QGSException("Problem while retrieving user roles");
		}
		return password;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getRoleCode(java.lang.String)
	 * Get role code of the logged in user
	 */
	@Override
	public String getRoleCode(String loginId) throws QGSException {
		String roleCode = "";

		connection = JDBCUtility.getConnection();
		logger.info("connection established");

		try {
			statement = connection.prepareStatement(QueryMapper.GET_ROLE_CODE_QUERY);
			logger.debug("statement created");

			statement.setString(1, loginId);

			resultSet = statement.executeQuery();

			if (resultSet == null) {
				throw new QGSException("No UserName found");
			}

			logger.info("records selected");
			resultSet.next();
			roleCode = resultSet.getString("role_code");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new QGSException("Problem while retrieving role code");
		}

		return roleCode;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getPolicyQuestions(java.lang.String)
	 * To get policy questions data from table using business segment
	 */
	@Override
	public List<PolicyQuestions> getPolicyQuestions(String busSegId) throws QGSException {

		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		List<PolicyQuestions> policyQuestions = new LinkedList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.GET_POLICY_QUESTIONS_QUERY);
			logger.debug("statement-retrieving policy questions created");

			statement.setString(1, busSegId);
			resultSet = statement.executeQuery();

			logger.info("record selected");

			while (resultSet.next()) {

				String polQuesId = resultSet.getString("POL_QUES_ID");
				int polQuesSeq = resultSet.getInt("POL_QUES_SEQ");
				String polQuesDesc = resultSet.getString("POL_QUES_DESC");
				String polQuesAns1 = resultSet.getString("POL_QUES_ANS1");
				int polQuesAns1Weightage = resultSet.getInt("POL_QUES_ANS1_WEIGHTAGE");
				String polQuesAns2 = resultSet.getString("POL_QUES_ANS2");
				int polQuesAns2Weightage = resultSet.getInt("POL_QUES_ANS2_WEIGHTAGE");
				String polQuesAns3 = resultSet.getString("POL_QUES_ANS3");
				int polQuesAns3Weightage = resultSet.getInt("POL_QUES_ANS3_WEIGHTAGE");

				PolicyQuestions policyQuestion = new PolicyQuestions(polQuesId, polQuesSeq, busSegId, polQuesDesc,
						polQuesAns1, polQuesAns1Weightage, polQuesAns2, polQuesAns2Weightage, polQuesAns3,
						polQuesAns3Weightage);
				logger.info("1 policy question  Acquired with options");
				policyQuestions.add(policyQuestion);

			}

		} catch (SQLException e) {
			logger.error("Problem while retrieving policy questions");
			e.printStackTrace();
			throw new QGSException("Problem while retrieving policy questions");
		} finally {
			
			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing connection");
			}
		}
		return policyQuestions;

	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getBusinessSegmentDetails()
	 * To return hashmap with business segment name and id mapping
	 */
	@Override
	public HashMap<String, String> getBusinessSegmentDetails() throws QGSException {

		HashMap<String, String> businessDetails = new HashMap<>();
		connection = JDBCUtility.getConnection();
		try {

			statement = connection.prepareStatement(QueryMapper.GET_BUSINESS_SEGMENT_QUERY);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {

				
				businessDetails.put(resultSet.getString("bus_seg_name").toLowerCase(), resultSet.getString("bus_seg_id"));

			}

		} catch (SQLException e) {

			logger.error("Problem while getting business segment names");
			throw new QGSException("Problem while getting business segment names");

		}try {
			logger.debug("before closing resultset");
			resultSet.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new QGSException("problme while closing resultset");
		}

		return businessDetails;
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#createAccount(com.cg.qgs.model.Accounts)
	 * Insertion of data to accounts table
	 */
	@Override
	public int createAccount(Accounts account) throws QGSException {
		int noOfDatasInserted = 0;
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		int accountNumber=0;
		
		try {
			statement = connection.prepareStatement(QueryMapper.ACCOUNT_CREATION_QUERY);
			
			statement.setString(1, account.getInsuredName());
			statement.setString(2, account.getInsuredStreet());
			statement.setString(3, account.getInsuredCity());
			statement.setInt(4, account.getInsuredZip());
			statement.setString(5, account.getBusSegId());
			statement.setString(6, account.getUserName());
			statement.setString(7, account.getInsuredState());
			statement.setString(8, account.getPolicyCreatorId());
			noOfDatasInserted = statement.executeUpdate();
			connection.commit();
			logger.info(noOfDatasInserted + "record inserted");
			
			statement = connection.prepareStatement(QueryMapper.GET_ACCOUNT_NUMBER_QUERY);
			resultSet = statement.executeQuery();
			resultSet.next();
			accountNumber=resultSet.getInt(1);
			logger.info("Account created with account number:"+accountNumber);
			
		} catch (SQLException e) {
			logger.error("Problem while creating Account");
			throw new QGSException("Problem while creating Account");
		}finally {
			try {
				logger.debug("before closing resultset");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing resultset");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing connection");
			}
		}
		return accountNumber;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#createPolicy(com.cg.qgs.model.Policy)
	 * Insertion of data to Policy and generation of policy number
	 */
	@Override
	public int createPolicy(Policy policy) throws QGSException {

		int noOfDatasInserted = 0;
		int policyNumber=0;
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		try {
			statement = connection.prepareStatement(QueryMapper.CREATE_POLICY_QUERY);
			logger.debug("statement created");
			statement.setDouble(1, policy.getPolicyPremium());
			statement.setLong(2, policy.getAccountNumber());

			noOfDatasInserted = statement.executeUpdate();
			connection.commit();
			logger.info(noOfDatasInserted + "record inserted");
			
			statement = connection.prepareStatement(QueryMapper.GET_POLICY_NUMBER_QUERY);
			resultSet = statement.executeQuery();
			resultSet.next();
			policyNumber=resultSet.getInt(1);
			
			logger.info("Policy created with policy number:"+policyNumber);
			
			
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			throw new QGSException("Problem while creating policy");
		}catch (SQLException e) {
			e.printStackTrace();
			throw new QGSException("Problem while creating policy");
		}finally {
			try {
				logger.debug("before closing resultset");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing resultset");
			}
			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing connection");
			}
		}
		
		return policyNumber;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#createPolicyDetails(com.cg.qgs.model.PolicyDetails)
	 * Policy detail table insertion
	 */
	@Override
	public long createPolicyDetails(PolicyDetails policyDetails) throws QGSException {
		
		int noOfDatasInserted = 0;
		long policyNumber=0;
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		try {
			statement = connection.prepareStatement(QueryMapper.CREATE_POLICY_DETAILS_QUERY);
			statement.setLong(1, policyDetails.getPolicyNumber());
			statement.setString(2, policyDetails.getQuestionId());
			statement.setString(3, policyDetails.getAnswer());
			policyNumber=policyDetails.getPolicyNumber();
			noOfDatasInserted = statement.executeUpdate();
			connection.commit();
			logger.info(noOfDatasInserted + "record inserted");
			
			
			
		} catch (SQLException e) {
			throw new QGSException("Problem While inserting Policy details");
		}
		
		
		return policyNumber;
		
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#fetchBusinessSegName(long)
	 * Get business segment from account number
	 */
	@Override
	public String fetchBusinessSegName(long accountNumber) throws QGSException {
		
		
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		String businessSegName;

		try {
			statement = connection.prepareStatement(QueryMapper.FETCH_BUS_SEGNAME_QUERY);
			statement.setLong(1, accountNumber);
			
			resultSet = statement.executeQuery();
			resultSet.next();
			businessSegName=resultSet.getString(1).toLowerCase();
			
		
		} catch(SQLIntegrityConstraintViolationException e) {
			throw new QGSException("Problem while fetching business segment name");
		}
			catch (SQLException e ) {
		
			throw new QGSException("Problem while fetching business segment name");
		}
		logger.debug("statement created");

		return businessSegName;

}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getUserNames()
	 * Get usernames of existing users in the system
	 */
	@Override
	public List<String> getUserNames() throws QGSException {
		
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		List<String> userNames = new LinkedList<>();
		
		try {
			statement = connection.prepareStatement(QueryMapper.GET_USERNAME_QUERY);
			logger.debug("statement-retrieving user names");
			
			resultSet = statement.executeQuery();

			logger.info("record selected");
			
			while (resultSet.next()) {
				String userName = resultSet.getString("user_name");
				userNames.add(userName);
			}

		} catch (SQLException e) {
			logger.debug(e.getMessage());
			throw new QGSException("Problem while getting UserNames");
		}finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing connection");
			}
		}

		return userNames;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getAccountHolders()
	 * Get existing account holder usernames
	 */
	@Override
	public List<String> getAccountHolders() throws QGSException {

		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		List<String> userNames = new LinkedList<>();
		
		
		
		try {
			statement = connection.prepareStatement(QueryMapper.GET_ACCOUNT_HOLDERS_QUERY);
			logger.debug("statement-retrieving Account Holders");
			resultSet = statement.executeQuery();
			logger.info("record selected");
			
			while (resultSet.next()) {
				String userName = resultSet.getString("user_name");
				userNames.add(userName);
			}
			
		} catch (SQLException e) {
			throw new QGSException("Problem while getting Account Holders");
		}finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing connection");
			}
		}

		
		return userNames;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#getAccountNumbers()
	 * Get existing account numbers
	 */
	@Override
	public List<Long> getAccountNumbers() throws QGSException {
		
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		List<Long> accountNumbers = new LinkedList<>();
		try {
			statement = connection.prepareStatement(QueryMapper.GET_ACCOUNT_NUMBERS_QUERY);
			logger.debug("statement-retrieving Account Numbers");
			resultSet = statement.executeQuery();
			logger.info("account numbers retrieving");
			while (resultSet.next()) {
				long accountNumber = resultSet.getLong("ACCOUNT_NUMBER");
				accountNumbers.add(accountNumber);
			}
			
		} catch (SQLException e) {
			throw new QGSException("Problem while retrieving accountNumbers");
		}finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new QGSException("problme while closing connection");
			}
		}
		return accountNumbers;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.dao.IQGSDaoCommon#updateProfileCreatorId(java.lang.String, long)
	 * Updation of creator id in database
	 */
	@Override
	public void updateProfileCreatorId(String loginId,long accountNumber) throws QGSException {
		
		connection = JDBCUtility.getConnection();
		logger.info("connection established");
		
		try {
			statement = connection.prepareStatement(QueryMapper.UPDATE_POLICY_CREATOR_ID);
			logger.debug("statement-Updating AgentId");
			statement.setString(1,loginId);
			statement.setLong(2,accountNumber);
			int noOfDatasUpdated = statement.executeUpdate();
			connection.commit();
			logger.info(noOfDatasUpdated+ "record updated");
		} catch (SQLException e) {
			logger.error("Problem while updating agentId");
			throw new QGSException("Problem while updating agentId ");
		}
	}
}
