package com.example.medicinereminder;

public class Database {

	private static Database singletonInstance = null;
	protected String firstName = ""; 
	protected String lastName = "";
	protected int avatarnumber = 0;
	protected String dateOfDiagnosis = "";
	protected String viralLoad = "";
	protected String phone = "";
	protected String providerPhone = "";
	protected String medication1 = "";
	protected String medication2 = "";
	protected String medication3 = "";
	protected Time medicationTime1 = null;
	protected Time medicationTime2 = null;
	protected String message = "";
	protected int appointmentsYear = 0;
	protected int appointmentsMonth = 0;
	protected int appointmentsDay = 0;
	protected Time appointmentsTime = null;
	protected int refillYear = 0;
	protected int refillMonth = 0;
	protected int refillDay = 0;
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
