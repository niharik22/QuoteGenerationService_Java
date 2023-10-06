package com.cg.qgs.daoagent.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.qgs.dao.QGSDaoAgentImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Policy;

class QGSDaoAgentImplTest {

	static QGSDaoAgentImpl daoAgent = null;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	
		daoAgent = new QGSDaoAgentImpl();
	}

	
	@Test
	void testQuickViewPolicyAgent1() {
		
		String username = "insured1004";		
		List<Policy> policy = new ArrayList<Policy>();
		
		try {
			policy = daoAgent.quickViewPolicyAgent(username);
			assertNotNull(policy);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	void testQuickViewPolicyAgent2() {
		String username = "rajesh123";		
		List<Policy> policy = new ArrayList<Policy>();
		
		try {
			policy = daoAgent.quickViewPolicyAgent(username);
			assertNotNull(policy);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testViewInsuredUnderAgent1() {
		String agentUsername = "agent1001";
		
		List<String> userslist = new ArrayList<>();
		
		try {
			userslist = daoAgent.viewInsuredUnderAgent(agentUsername);
			assertNotNull(userslist);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	void testViewInsuredUnderAgent2() {
		String agentUsername = "agent1";
		
		List<String> userslist = new ArrayList<>();
		
		try {
			userslist = daoAgent.viewInsuredUnderAgent(agentUsername);
			assertNotNull(userslist);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		daoAgent = null;
	}

	

}
