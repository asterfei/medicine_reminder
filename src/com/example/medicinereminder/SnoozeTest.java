package com.example.medicinereminder;

import static org.junit.Assert.*;

import org.junit.Test;


public class SnoozeTest {
	
	private Database data = Database.getInstance();
	private int mins = 0;
	
	public SnoozeTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		mins = data.snoozeTime;
	}
	
	@Test
	public void testMedications(){
		assertEquals(mins, 5);
	}


}