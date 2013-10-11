package com.example.medicinereminder;

import static org.junit.Assert.*;

import org.junit.Test;


public class AppointmentsTest {
	
	private Database data = Database.getInstance();
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private Time time = null;
	
	public AppointmentsTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		day = data.appointmentsDay;
		month = data.appointmentsMonth;
		year = data.appointmentsYear;
		time = data.appointmentsTime;
	}
	
	@Test
	public void testDate(){
		assertEquals(day, 01);
		assertEquals(month, 04);
		assertEquals(year, 2013);
	}
	
	@Test
	public void testHour(){
		assertEquals(time.getHour(), 11);
		assertEquals(time.getMinute(), 0);
	}
	
	

}
