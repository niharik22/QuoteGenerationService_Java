package com.cg.qgs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.cg.qgs.dao.IQGSDaoAdmin;
import com.cg.qgs.dao.IQGSDaoAgent;
import com.cg.qgs.dao.IQGSDaoCommon;
import com.cg.qgs.dao.IQGSDaoInsured;
import com.cg.qgs.dao.QGSDaoAdminImpl;
import com.cg.qgs.dao.QGSDaoAgentImpl;
import com.cg.qgs.dao.QGSDaoCommonImpl;
import com.cg.qgs.dao.QGSDaoInsuredImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.PolicyDetails;
import com.cg.qgs.model.PolicyQuestions;
import com.cg.qgs.model.UserRole;

public class QGSServiceImpl implements IQGSService {
	
	IQGSDaoCommon commonDao=new QGSDaoCommonImpl();
	IQGSDaoAdmin adminDao=new QGSDaoAdminImpl();
	IQGSDaoInsured insuredDao=new QGSDaoInsuredImpl();
	IQGSDaoAgent agentDao = new QGSDaoAgentImpl();

	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#verifyCredentials(java.lang.String, java.lang.String)
	 * Used to berify login credentials of users
	 */
	@Override
	public boolean verifyCredentials(String loginId, String password) throws QGSException {
		
		if(loginId.equals("")) {
			throw new QGSException("Login Id cannot be blank");
		}
		if (password.equals("")) {
			throw new QGSException("Password cannot be blank");
		}
		String Password=commonDao.getCredentials(loginId);
		boolean credentialFlag=false;
		
		
		if (password.equals(Password)) {
			credentialFlag=true;
		}else {
			throw new QGSException("Login Failed entered wrong password "); 
		}
		
		return credentialFlag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#getRoleCode(java.lang.String)
	 * To get the role code of user logged in to the system
	 */
	@Override
	public String getRoleCode(String loginId) throws QGSException {

		return commonDao.getRoleCode(loginId);
		
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#createAccount(com.cg.qgs.model.Accounts)
	 * Takes details from user and sends it to DAO Layer for account creation
	 */
	@Override
	public int createAccount(Accounts account) throws QGSException {
		
		return commonDao.createAccount(account);
	}
	
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkInsuredName(java.lang.String)
	 * Validation method for name used in account creation
	 */
	@Override
	public boolean checkInsuredName(String insuredName) throws QGSException {
		
		boolean insuredNameFlag =false;
		String NameRegex=("[A-Z]{1}[A-Za-z\\s]{2,29}");
		if(Pattern.matches(NameRegex,insuredName)){
			insuredNameFlag =true;
		}
		else {
			insuredNameFlag=false;
			throw new QGSException("First letter must be capital and maximum length is 30 characters");
		}
		return insuredNameFlag;
		
		
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#getPolicyQuestions(java.lang.String)
	 * To get questions required for quote generation
	 */
	@Override
	public List<PolicyQuestions> getPolicyQuestions(String busSegId) throws QGSException {
		return commonDao.getPolicyQuestions(busSegId);
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkInsuredStreet(java.lang.String)
	 * Validation method for street value during account creation
	 */
	@Override
	public boolean checkInsuredStreet(String insuredStreet) throws QGSException {
		boolean insuredStreetFlag = false;
		String streetRegex = ("[A-Za-z0-9\\s.+]{1,40}");
		if (Pattern.matches(streetRegex, insuredStreet)) {
			insuredStreetFlag = true;
		} else {
			insuredStreetFlag = false;
			throw new QGSException("Street name should start with Capital letter only and should be less than 15 charecters");
		}
		return insuredStreetFlag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkCity(java.lang.String)
	 * Validation method for city value during account creation
	 */
	@Override
	public boolean checkCity(String insuredCity) throws QGSException {
		boolean cityFlag = false;
		String cityRegex = ("^[A-Z]{1}[A-Za-z].+{2,15}");
		if (Pattern.matches(cityRegex, insuredCity)) {
			cityFlag = true;
		} else {
			cityFlag = false;
			throw new QGSException("City name should start with Capital letter only and should be less than 15 charecters");
		}

		return cityFlag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkState(java.lang.String)
	 * Validation method for state value during account creation
	 */
	@Override
	public boolean checkState(String insuredState) throws QGSException {
		boolean stateFlag = false;
		String stateRegex = ("[A-Z]{1}[A-Za-z.+\\s.+]{2,15}");
		if (Pattern.matches(stateRegex, insuredState)) {
			stateFlag = true;
		} else {
			stateFlag = false;
			throw new QGSException("State name should start with Capital letter only and should be less than 15 charecters");
		}
		return stateFlag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkInsuredZip(int)
	 * Validation method for zipcode value during account creation
	 */
	@Override
	public boolean checkInsuredZip(int insuredZip) throws QGSException {
		boolean insuredZipFlag = false;
		String zipRegex = ("[0-9]{5}");

		if (Pattern.matches(zipRegex, String.valueOf(insuredZip))) {
			insuredZipFlag = true;
		} else {
			insuredZipFlag = false;
			throw new QGSException("Zip code must have 5 digits");
		}
		return insuredZipFlag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#getBusinessSegmentDetails()
	 * Returns map with business segment name and id as the key and value
	 */
	@Override
	public HashMap<String, String> getBusinessSegmentDetails() throws QGSException {

		return commonDao.getBusinessSegmentDetails();
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#validateBusinessSegmentName(java.lang.String, java.util.Set)
	 * Validation method for business segment during account creation
	 */
	@Override
	public boolean validateBusinessSegmentName(String busSegName,Set<String> businessName) throws QGSException {
		boolean businessSegmentFlag=false;
		
		if(busSegName==null)
		{
			throw new QGSException("No input entered");
		}
		
		for (Iterator<String> iterator = businessName.iterator(); iterator.hasNext();) {
			String businessSegmentName = (String) iterator.next();
			if(businessSegmentName.equalsIgnoreCase(busSegName)) {
				businessSegmentFlag=true;
				break;
			}
		}
		if (businessSegmentFlag==false) {
			throw new QGSException("Select from the dispalyed list");
		}
		return businessSegmentFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#InsertUser(com.cg.qgs.model.UserRole)
	 * Method to insert users into the system
	 */
	@Override
	public void InsertUser(UserRole userRole) throws QGSException {
		
		adminDao.InsertUser(userRole);
		
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#ValidateUserName(java.lang.String)
	 * Validation method for usernames for profile creation
	 */
	@Override
	public boolean ValidateUserName(String userName) throws QGSException {
		List<String> list= new ArrayList<String>();
		boolean result;
		boolean flag=false;
		
		list= adminDao.GetUserName();
		if(list.contains(userName)) {
			throw new QGSException("Username already exists");
		}
		else {
			String userNameRegex="([A-Za-z]+[0-9]+[A-Za-z]*{5,20})";
			result= Pattern.matches(userNameRegex,userName);
			if(result) {
				flag=true;
			}
			else {
				throw new QGSException("Username length must be between 5 and 20 and it must contain atleast one number along with letters");
			}
	}
		return flag;
	

	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#ValidatePassword(java.lang.String)
	 * Validation method for password during profile creation
	 */
	@Override
	public boolean ValidatePassword(String password) throws QGSException {
		
		boolean flag= false;
		boolean result;
		if(password.length()>=8 && password.length()<=12) {
			
			String passwordRegex= "((?=.*[A-Z])(?=.*[a-z])(?=.*[$_%@])(?=.*[0-9]).{8,12})";
			result= Pattern.matches(passwordRegex, password);
			if(result) {
				flag= true;
			}
			else {
				flag= false;
				throw new QGSException("Password should contain atleast one uppercase, one lowercase, one digit and one special character among ($%_@)");
			}
	}
		else {
			flag= false;
			throw new QGSException("Password length should be between 8 and 12");
		}
		return flag;
}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#ValidateRoleCode(java.lang.String)
	 * Validation method for role code during profile creation
	 */
	@Override
	public boolean ValidateRoleCode(String roleCode) throws QGSException {
		
		boolean flag= false;
		if(roleCode.equalsIgnoreCase("admin") || roleCode.equalsIgnoreCase("insured") || roleCode.equalsIgnoreCase("agent"))
			flag= true;
			else {
				flag= false;
				throw new QGSException("Enter valid role code");
			}
		return flag;
		
		}


	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#createPolicy(com.cg.qgs.model.Policy)
	 * Method to send policy creation data to DAO layer
	 */
	@Override
	public int createPolicy(Policy policy) throws QGSException {
		
		return commonDao.createPolicy(policy);
	}

	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#createPolicyDetails(com.cg.qgs.model.PolicyDetails)
	 * Method to send data about policy for insertion into database
	 */
	@Override
	public long createPolicyDetails(PolicyDetails policyDetails) throws QGSException {
		
		return commonDao.createPolicyDetails(policyDetails);
	}
	

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#fetchBusinessSegName(long)
	 * To get business segment name for a particular user
	 */
	@Override
	public String fetchBusinessSegName(long accountNumber) throws QGSException {
		return commonDao.fetchBusinessSegName(accountNumber);
		
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#validateUserNameForInsured(java.lang.String)
	 * for username validation from database
	 */
	@Override
	public boolean validateUserNameForInsured(String userName) throws QGSException {
		boolean userNameFlag=false;
		if (userName.equals("")) {
			throw new QGSException("User Name cannot be blank");
		}
		
		List<String>userNames=commonDao.getUserNames();
		
		for (Iterator<String> iterator = userNames.iterator(); iterator.hasNext();) {
			String username = (String) iterator.next();
			if(userName.equalsIgnoreCase(username)) {
				userNameFlag=true;
				break;
			}
		}
		
		if (!userNameFlag) {
			throw new QGSException("User Name Not Found");
		}
		
		return userNameFlag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkAccountHolder(java.lang.String)
	 * To check of user has account or not
	 */
	@Override
	public boolean checkAccountHolder(String userName) throws QGSException{
		
		List<String> userNames=new LinkedList<String>();
		boolean checkAccountHolderFlag=false;
		
		try {
			userNames=commonDao.getAccountHolders();
			
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
		for (Iterator<String> iterator = userNames.iterator(); iterator.hasNext();) {
			String username = (String) iterator.next();
			if (userName.equalsIgnoreCase(username)) {
				checkAccountHolderFlag= true;
			}
		}
		return checkAccountHolderFlag;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#validateUserNameForAdminOrAgent(java.lang.String)
	 * To check if a user already has an account
	 */
	@Override
	public boolean validateUserNameForAdminOrAgent(String userName) throws QGSException {
		
		boolean validateUserNameflag=false;
		boolean checkAccountFlag;
		boolean checkInUserRoleFlag;
		
		
		checkInUserRoleFlag=validateUserNameForInsured(userName);

		checkAccountFlag = checkAccountHolder(userName);
		if (checkInUserRoleFlag) {
			if (checkAccountFlag == true) {
				throw new QGSException("This user already has an account");
			} else {
				validateUserNameflag = true;
			}

		}
		return validateUserNameflag;
	}

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#checkAccountNumber(long)
	 * To check if an account number exists
	 */
	@Override
	public boolean checkAccountNumber(long accountNumber) throws QGSException {
		
		boolean validateAccountNumber=false;
		List<Long>accountList=commonDao.getAccountNumbers();
		for (Iterator<Long> iterator = accountList.iterator(); iterator.hasNext();) {
			Long accNumber= (Long) iterator.next();
			if(accNumber==accountNumber) {
				validateAccountNumber= true;
				break;
			}
		}
		if(validateAccountNumber == false) {
			throw new QGSException("Account number not found. Please create an account");
		}
		
		return validateAccountNumber;
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#quickViewPolicy(java.lang.String)
	 * View policy for insured user
	 */
	public List<Policy> quickViewPolicy(String username) throws QGSException
	{
		List<Policy> policy = null;
		policy = insuredDao.quickViewPolicy(username);
		return policy;
		
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#quickViewPolicyAgent(java.lang.String)
	 * View policy for agent
	 */
	public List<Policy> quickViewPolicyAgent(String username) throws QGSException
	{
		List<Policy> policy = null;
		policy = agentDao.quickViewPolicyAgent(username);
		return policy;
	}
	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#quickViewPolicyAdmin()
	 * View policy for admin
	 */
	public List<Policy> quickViewPolicyAdmin() throws QGSException
	{
		List<Policy> policy = null;
		policy = adminDao.quickViewPolicyAdmin();
		return policy;
		
	}

	
	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#generateReport(long, long)
	 * Generate detailed policy report for admin
	 * 
	 */
	public void generateReport(long accNum,long policy_num) throws QGSException
	{
		adminDao.generateReport(accNum,policy_num);
		
	}
	

	/* (non-Javadoc)
	 * @see com.cg.qgs.service.IQGSService#updateProfileCreatorId(java.lang.String, long)
	 * To update profile creator id in accounts when an agent creates policy for that insured user
	 */
	@Override
	public void updateProfileCreatorId(String loginId,long accountNumber) throws QGSException {
		commonDao.updateProfileCreatorId(loginId,accountNumber);
		
	}

	
}