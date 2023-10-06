
package com.cg.qgs.service.test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.qgs.dao.IQGSDaoCommon;
import com.cg.qgs.dao.QGSDaoCommonImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.service.QGSServiceImpl;


public class QGSServiceImplCredentialsTest {
	
	

	QGSServiceImpl service= null;
	IQGSDaoCommon dao=new QGSDaoCommonImpl();
	
	@Before
	public void setUp() throws Exception {
		service=new QGSServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		service=null;
	
	}

	
	@Test
	public void testVerifyCredentialsPass1() {
		
		boolean flag= false;
		String loginId= "admin1001";
		String password= "admin1";
		try {
			flag= service.verifyCredentials(loginId, password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertTrue(flag);
	}
	@Test
	public void testVerifyCredentialsPass2() {
		
		boolean flag= false;
		String loginId= "insured1001";
		String password= "insured1";
		try {
			flag= service.verifyCredentials(loginId, password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	public void testVerifyCredentialsPass3() {
		
		boolean flag= false;
		String loginId= "agent1001";
		String password= "agent1";
		try {
			flag= service.verifyCredentials(loginId, password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	
	@Test
	public void testVerifyCredentialsFail1() {
		
		boolean flag= false;
		String loginId= "agent1001";
		String password= "agent12";
		try {
			flag= service.verifyCredentials(loginId, password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	public void testVerifyCredentialsFail2() {
		
		boolean flag= false;
		String loginId= "raju32";
		String password= "insured09";
		try {
			flag= service.verifyCredentials(loginId, password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	public void testVerifyCredentialsFail3() {
		
		boolean flag= false;
		String loginId= "12345";
		String password= "234%43";
		try {
			flag= service.verifyCredentials(loginId, password);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	
	
	

}
	