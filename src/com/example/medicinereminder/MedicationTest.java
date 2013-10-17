package com.example.medicinereminder;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


public class MedicationTest {
	
	private Database data = Database.getInstance();
	private String medication1 = "";
	private String medication2 = "";
	private Date time1 = null;
	private Date time2 = null;
	
	public MedicationTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		medication1 = data.medication1;
		medication2 = data.medication2;
		time1 = data.medicationTime1;
		time2 = data.medicationTime2;
	}
	
	@Test
	public void testMedications(){
		assertEquals(medication1, "advil");
		assertEquals(medication2, "");
	}
	
	@Test
	public void testMedicationTime(){
		assertEquals(time1.getHours(), 8);
		assertEquals(time1.getMinutes(), 30);
	}
	
	

}