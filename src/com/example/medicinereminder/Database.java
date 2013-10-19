package com.example.medicinereminder;

import java.util.Date;

public class Database {

	private static Database singletonInstance = null;
	protected String objectId = "";
	protected String userName = "";
	protected String firstName = ""; 
	protected String lastName = "";
	protected int avatarnumber = 0;
	protected String dateOfDiagnosis = "";
	protected String viralLoad = "";
	protected String phone = "";
	protected String providerPhone = "";
	protected String medication1 = "";
	protected String medication2 = "";
	protected Date medicationTime1 = null;
	protected Date medicationTime2 = null;
	protected String message = "";
	protected Date appointmentsTime = null;
	protected Date refillTime = null;
	protected int buck = 0;
	protected int snoozeTime = 0;
	protected int mins = 0;
	protected int notTakenCount = 0;
	protected int takenCount = 0;

	private Database(){
		
	}
	
	public static Database getInstance(){
		if (singletonInstance == null)
			singletonInstance = new Database();
		return singletonInstance;
	}
	

}
