package com.cg.qgs.daoinsured.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.qgs.dao.QGSDaoInsuredImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Policy;

class QGSDaoInsuredImplTest {

	QGSDaoInsuredImpl daoInsured = null;
	@BeforeEach
	void setUp() throws Exception {
		
		daoInsured = new QGSDaoInsuredImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		daoInsured = null;
	}

	@Test
	void testQuickViewPolicy1() {
		String username = "insured1004";
		List<Policy> policy = new ArrayList<Policy>();
		try {
			policy = daoInsured.quickViewPolicy(username);
			assertNotNull(policy);
		} catch (QGSException e) {
			System.err.println(e.getMessage());		}
	
	}
	
	@Test
	void testQuickViewPolicy2() {
		
		String username = "insured1003";
		List<Policy> policy = new ArrayList<Policy>();
		try {
			policy = daoInsured.quickViewPolicy(username);
			assertNotNull(policy);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	void testQuickViewPolicy3() {
		
		String username = "insuredu1002";
		List<Policy> policy = new ArrayList<Policy>();
		try {
			policy = daoInsured.quickViewPolicy(username);
			if(policy==null)
			{
				throw new NullPointerException();
			}
		} catch (QGSException e) {
			fail("Should not have thrown exception");
		}
		
	}
	
}
