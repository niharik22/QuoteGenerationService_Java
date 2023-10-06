package com.cg.qgs.service.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.qgs.dao.IQGSDaoAdmin;
import com.cg.qgs.dao.QGSDaoAdminImpl;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.service.QGSServiceImpl;

class QGSServiceImplProfileValidationsTest {

	QGSServiceImpl service= null;
	IQGSDaoAdmin adminDao=new QGSDaoAdminImpl();
	
	@BeforeEach
	void setUp() throws Exception {
		service= new QGSServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		service= null;
	}

	@Test
	void testValidatePasswordPass1() {
		
		boolean flag= false;
		String userName= "Sahithi45%";
		try {
			flag= service.ValidatePassword(userName);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
		 
	}
	
	@Test
	void testValidatePasswordPass2() {
		
		boolean flag= false;
		String password= "firstDs$32";
		try {
			flag= service.ValidatePassword(password);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testValidatePasswordPass3() {
		
		boolean flag= false;
		String password= "12Drive@43";
		try {
			flag= service.ValidatePassword(password);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testValidatePasswordFail1() {
		
		boolean flag= false;
		String password= "second12$";
		try {
			flag= service.ValidatePassword(password);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	void testValidatePasswordFail2() {
		
		boolean flag= false;
		String password= "SECOND@12";
		try {
			flag= service.ValidatePassword(password);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	void testValidatePasswordFail3() {
		
		boolean flag= false;
		String password= "dineOut^5";
		try {
			flag= service.ValidatePassword(password);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	void testValidatePasswordFail4() {
		
		boolean flag= false;
		String password= "admin1001";
		try {
			flag= service.ValidatePassword(password);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
		
	}

	@Test
	void testValidateUserNamePass1() {
		
		boolean flag= false;
		String userName= "Sahithi12";
		try {
			flag= service.ValidateUserName(userName);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testValidateUserNamePass2() {
		
		boolean flag= false;
		String userName= "sampriti1";
		try {
			flag= service.ValidateUserName(userName);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
		
	}

	@Test
	void testValidateUserNameFail1() {
	
	boolean flag= false;
	String userName= "12Devaki";
	try {
		flag= service.ValidateUserName(userName);
	} catch (QGSException e) {
		System.out.println(e.getMessage());
	}
	assertFalse(flag);
	
	}

	@Test
	void testValidateUserNameFail2() {
	
	boolean flag= false;
	String userName= "Ayodhya@1";
	try {
		flag= service.ValidateUserName(userName);
	} catch (QGSException e) {
		System.out.println(e.getMessage());
	}
	assertFalse(flag);
	
	}

	@Test
	void testValidateRoleCodePass1() {
		boolean flag= false;
		String roleCode= "admin";
		try {
			flag= service.ValidateRoleCode(roleCode);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testValidateRoleCodePass2() {
		boolean flag= false;
		String roleCode= "insured";
		try {
			flag= service.ValidateRoleCode(roleCode);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testValidateRoleCodePass3() {
		boolean flag= false;
		String roleCode= "agent";
		try {
			flag= service.ValidateRoleCode(roleCode);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testValidateRoleCodeFail1() {
		boolean flag= false;
		String roleCode= "1234ad";
		try {
			flag= service.ValidateRoleCode(roleCode);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testValidateRoleCodeFail2() {
		boolean flag= false;
		String roleCode= "admin123";
		try {
			flag= service.ValidateRoleCode(roleCode);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testValidateRoleCodeFail3() {
		boolean flag= false;
		String roleCode= "underwriter";
		try {
			flag= service.ValidateRoleCode(roleCode);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}

}
