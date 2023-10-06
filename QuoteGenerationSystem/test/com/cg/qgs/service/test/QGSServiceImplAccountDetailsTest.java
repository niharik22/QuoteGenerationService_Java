package com.cg.qgs.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.service.QGSServiceImpl;

class QGSServiceImplAccountDetailsTest {
	Connection connection= null;
	PreparedStatement statement= null;
	ResultSet resultSet= null;

	QGSServiceImpl service= null;
	@BeforeEach
	void setUp() throws Exception {
		service= new QGSServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		service= null;
	}

	@Test
	void testCheckInsuredNamePass1() {
		
		boolean flag= false;
		String input="Sahithi S";
		try {
			flag= service.checkInsuredName(input);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testCheckInsuredNamePass2() {
		
		boolean flag= false;
		String input="Sampriti";
		try {
			flag= service.checkInsuredName(input);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testCheckInsuredNamePass3() {
		
		boolean flag= false;
		String input="D Raju";
		try {
			flag= service.checkInsuredName(input);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testCheckInsuredNameFail1() {
		
		boolean flag= false;
		String input="raju S";
		try {
			flag= service.checkInsuredName(input);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	void testCheckInsuredNameFail2() {
		
		boolean flag= false;
		String input="Radha12";
		try {
			flag= service.checkInsuredName(input);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	@Test
	void testCheckInsuredNameFail3() {
		
		boolean flag= false;
		String input="Krishna@12";
		try {
			flag= service.checkInsuredName(input);
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		assertFalse(flag);
		
	}
	
	

	

	@Test
	void testCheckCityPass1() {
		
		boolean flag= false;
		String input="Hyderabad";
		
		try {
			flag= service.checkCity(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testCheckCityPass2() {
		
		boolean flag= false;
		String input="Nagpur";
		
		try {
			flag= service.checkCity(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testCheckCityPass3() {
		
		boolean flag= false;
		String input="Bandra";
		
		try {
			flag= service.checkCity(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}

	@Test
	void testCheckCityFail1() {
		
		boolean flag= false;
		String input="vizag";
		
		try {
			flag= service.checkCity(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testCheckCityFail2() {
		
		boolean flag= false;
		String input="mumbai2";
		
		try {
			flag= service.checkCity(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testCheckCityFail3() {
		
		boolean flag= false;
		String input="patna$";
		
		try {
			flag= service.checkCity(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	
	@Test
	void testCheckStatePass1() {
		boolean flag= false;
		String input="Telangana";
		
		try {
			flag= service.checkState(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testCheckStatePass2() {
		boolean flag= false;
		String input="Uttar Pradesh";
		
		try {
			flag= service.checkState(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}

	
	@Test
	void testCheckStatePass3() {
		boolean flag= false;
		String input="Madhya Pradesh";
		
		try {
			flag= service.checkState(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}

	@Test
	void testCheckStateFail1() {
		boolean flag= false;
		String input="andhra pradesh";
		
		try {
			flag= service.checkState(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testCheckStateFail2() {
		boolean flag= false;
		String input="Kerala12";
		
		try {
			flag= service.checkState(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}

	
	@Test
	void testCheckStateFail3() {
		boolean flag= false;
		String input="123AP";
		
		try {
			flag= service.checkState(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}



	@Test
	void testCheckInsuredZipPass1() {
		boolean flag= false;
		int input= 12345;
		try {
			flag= service.checkInsuredZip(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testCheckInsuredZipPass2() {
		boolean flag= false;
		int input= 12345;
		try {
			flag= service.checkInsuredZip(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
		
	}
	
	@Test
	void testCheckInsuredZipFail1() {
		boolean flag= false;
		int input= 1234;
		try {
			flag= service.checkInsuredZip(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testCheckInsuredZipFail2() {
		boolean flag= false;
		int input= 3456;
		try {
			flag= service.checkInsuredZip(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());

			assertFalse(flag);
		}
	}
	
	@Test
	void testCheckInsuredZipFail3() {
		boolean flag= false;
		int input= 567;
		try {
			flag= service.checkInsuredZip(input);
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	

	@Test
	void testValidateBusinessSegmentNamePass1() {
		boolean flag= false;
		
		HashMap<String, String> hashMap=new HashMap<>();
		
		try {
			hashMap=service.getBusinessSegmentDetails();
			Set<String> businessName = hashMap.keySet();
			flag= service.validateBusinessSegmentName("auto", businessName);
			
			
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testValidateBusinessSegmentNamePass2() {
		boolean flag= false;
		
		HashMap<String, String> hashMap=new HashMap<>();
		
		try {
			hashMap=service.getBusinessSegmentDetails();
			Set<String> businessName = hashMap.keySet();
			flag= service.validateBusinessSegmentName("Restaurant", businessName);
			
			
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testValidateBusinessSegmentNamePass3() {
		boolean flag= false;
		
		HashMap<String, String> hashMap=new HashMap<>();
		
		try {
			hashMap=service.getBusinessSegmentDetails();
			Set<String> businessName = hashMap.keySet();
			flag= service.validateBusinessSegmentName("General merchant", businessName);
			
			
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag);
	}
	
	@Test
	void testValidateBusinessSegmentNameFail1() {
		boolean flag= false;
		
		HashMap<String, String> hashMap=new HashMap<>();
		
		try {
			hashMap=service.getBusinessSegmentDetails();
			Set<String> businessName = hashMap.keySet();
			flag= service.validateBusinessSegmentName("house", businessName);
			
			
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testValidateBusinessSegmentNameFail2() {
		boolean flag= false;
		
		HashMap<String, String> hashMap=new HashMap<>();
		
		try {
			hashMap=service.getBusinessSegmentDetails();
			Set<String> businessName = hashMap.keySet();
			flag= service.validateBusinessSegmentName("Land", businessName);
			
			
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}
	
	@Test
	void testValidateBusinessSegmentNameFail3() {
		boolean flag= false;
		
		HashMap<String, String> hashMap=new HashMap<>();
		
		try {
			hashMap=service.getBusinessSegmentDetails();
			Set<String> businessName = hashMap.keySet();
			flag= service.validateBusinessSegmentName("11234", businessName);
			
			
		} catch (QGSException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(flag);
	}

	

}
