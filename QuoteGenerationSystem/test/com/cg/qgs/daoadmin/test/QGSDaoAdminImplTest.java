package com.cg.qgs.daoadmin.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.qgs.dao.QGSDaoAdminImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.UserRole;

class QGSDaoAdminImplTest {

	static QGSDaoAdminImpl daoAdmin = null;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daoAdmin = new QGSDaoAdminImpl();
	}

	@Test
	void testGetUserName1() {
		List<String> usersList = new ArrayList<>();
		
		try {
			usersList =daoAdmin.GetUserName();
			assertNotNull(usersList);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	void testGetUserName2() {
		List<String> usersList = new ArrayList<>();
		
		try {
			usersList =daoAdmin.GetUserName();
			assertNotNull(usersList);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}



	@Test
	void testInsertUser1() {
		UserRole userRole = new UserRole("insured1009", "Insured@9", "insured");
		
		try {
			daoAdmin.InsertUser(userRole);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
/*	@Test
	void testInsertUser2() {
		UserRole userRole = new UserRole("insured1010", "insured10", "insured");
		
		try {
			daoAdmin.InsertUser(userRole);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}

	}
*/
	@Test
	void testQuickViewPolicyAdmin1() {
		List<Policy> policy = new ArrayList<Policy>();
		
		try {
			policy = daoAdmin.quickViewPolicyAdmin();
			assertNotNull(policy);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testQuickViewPolicyAdmin2() {
			
		List<Policy> policy = new ArrayList<Policy>();
		
		try {
			policy = daoAdmin.quickViewPolicyAdmin();
			assertNotNull(policy);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		daoAdmin = null;
	}
	

}
