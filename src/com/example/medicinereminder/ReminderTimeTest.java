package com.example.medicinereminder;

import static org.junit.Assert.*;

import org.junit.Test;


public class ReminderTimeTest {
	
	private Database data = Database.getInstance();
	private int mins = 0;
	
	public ReminderTimeTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		mins = data.mins;
	}
	
	@Test
	public void testMedications(){
		assertEquals(mins, 5);
	}
	
	

}