package com.example.medicinereminder;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserInfoTest {
	
	private String first_name = "";
	private String last_name = "";
	private String dateOfDiagnosis = "";
	private String viralLoad = "";
	private String phone = "";
	private String providerPhone = "";
	private Database data = Database.getInstance();

	public UserInfoTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		first_name = data.firstName;
		last_name = data.lastName;
		dateOfDiagnosis = data.dateOfDiagnosis;
		viralLoad = data.viralLoad;
		phone = data.phone;
		providerPhone = data.providerPhone;
		
	}
	@Test
	public void testFirstName(){
		assertEquals(first_name, "John");
	}
	@Test
	public void testLastName(){
		assertEquals(last_name, "Brown");
	}
	@Test
	public void testDate(){
		assertEquals(dateOfDiagnosis, "03/27/13");
	}
	@Test
	public void testViralLoad(){
		assertEquals(viralLoad, "10000");
	}
	@Test
	public void testPhone(){
		assertEquals(phone, "2155887785");
	}
	@Test
	public void testProviderPhone(){
		assertEquals(providerPhone, "2157677281");
	}
	

}
