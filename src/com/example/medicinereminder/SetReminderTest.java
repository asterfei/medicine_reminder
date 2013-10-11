package com.example.medicinereminder;

import static org.junit.Assert.*;

import org.junit.Test;


public class SetReminderTest {
	
	private Database data = Database.getInstance();
	private String message = "";
	
	public SetReminderTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		message = data.message;
	}
	
	@Test
	public void testMedications(){
		assertEquals(message, "Take your meds.");
	}
	
	

}