package com.cg.qgs.daocommon.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.qgs.dao.QGSDaoCommonImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.PolicyQuestions;

class QGSDaoCommonimplTest {

	static QGSDaoCommonImpl daoCommon = new QGSDaoCommonImpl();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daoCommon = new QGSDaoCommonImpl();
	}

	@Test
	void testGetCredentials1() {
		String username="admin1001";
		
		try {
			String password = daoCommon.getCredentials(username);
			assertEquals(password, "admin1");
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	void testGetCredentials2() {
		String username="admin1007";
		
		try {
			String password = daoCommon.getCredentials(username);
			assertNull(password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	void testGetRoleCode1() {
		String username="admin1001";
		
		try {
			String role = daoCommon.getRoleCode(username);
			assertEquals(role,"admin");
			
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testGetRoleCode2() {
		String username="insured1001";
		
		try {
			String role = daoCommon.getRoleCode(username);
			assertNotEquals(role,"admin");
			
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	void testGetPolicyQuestions1() {
		
		
		List<PolicyQuestions> question = new ArrayList<PolicyQuestions>();
		
		String busSegId ="BS101";
		try {
			question = daoCommon.getPolicyQuestions(busSegId);
			assertFalse(question.isEmpty());
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testGetPolicyQuestions2() {
		List<PolicyQuestions> question = new ArrayList<PolicyQuestions>();

		String busSegId ="BS110";
		try {
			question = daoCommon.getPolicyQuestions(busSegId);
			assertTrue(question.isEmpty());
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	void testGetBusinessSegmentDetails() {
		HashMap<String, String> businessDetails = new HashMap<>();
		try {
			businessDetails = daoCommon.getBusinessSegmentDetails();
			assertFalse(businessDetails.isEmpty());
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
		
	}


	@Test
	void testCreateAccount1() {
		Accounts account = new Accounts();
		int check;
		try {
			check = daoCommon.createAccount(account);
			assertNotEquals(check,0);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
	@Test
	void testCreateAccount2() {
		Accounts account = new Accounts();
		int check;
		try {
			check = daoCommon.createAccount(account);
			assertNotEquals(check,0);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}

	@Test
	void testCreatePolicy1() {
		Policy policy = new Policy(250000, 106);
		int check;
		try {
			check = daoCommon.createPolicy(policy);
			assertNotNull(check);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
	@Test
	void testCreatePolicy2() {
		Policy policy =  new Policy(250000, 109);
		int check;
		try {
			check = daoCommon.createPolicy(policy);
			assertNotEquals(check,0);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}

	@Test
	void testCreatePolicyDetails1() {
		
	}
	
	@Test
	void testCreatePolicyDetails2() {
		
	}

	@Test
	void testFetchBusinessSegName1() {
		long accNum = 106;
		String busName;
		try {
			busName = daoCommon.fetchBusinessSegName(accNum);
			assertNotEquals(busName,"Apartment");
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	void testFetchBusinessSegName2() {
		long accNum = 106;
		String busName;
		try {
			busName = daoCommon.fetchBusinessSegName(accNum);
			assertNotEquals(busName,"");
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}

	@Test
	void testGetUserNames() {
		List<String> userNames = new LinkedList<>();
		try {
			userNames = daoCommon.getUserNames();
			assertFalse(userNames.isEmpty());
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	

	@Test
	void testGetAccountHolders() {
		List<String> userNames = new LinkedList<>();
		try {
			userNames = daoCommon.getAccountHolders();
			assertFalse(userNames.isEmpty());
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	

	@Test
	void testGetAccountNumbers() {
		List<Long> accNumbers = new LinkedList<>();
		try {
			accNumbers = daoCommon.getAccountNumbers();
			assertFalse(accNumbers.isEmpty());
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		daoCommon = null;
	}

}
