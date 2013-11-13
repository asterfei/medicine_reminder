package com.example.medicinereminder;

import java.util.Date;

import com.parse.ParseObject;

public class Database {

	private static Database singletonInstance = null;
	protected String objectId = "";
	protected String userName = "";
	protected String firstName = "";
	protected String lastName = "";
	//protected int avatarnumber = 0;
	protected String dateOfDiagnosis = "";
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
	protected int mins = 0;
	protected int snoozeTime = 0;
	protected int notTakenCount = 0;
	protected int takenCount = 0;

	private Database() {

	}

	public static Database getInstance() {
		if (singletonInstance == null)
			singletonInstance = new Database();
		return singletonInstance;
	}

	public void setUserData(ParseObject user) {
		objectId = user.getObjectId();
		userName = user.getString("username");
		firstName = user.getString("firstName");
		lastName = user.getString("lastName");
		dateOfDiagnosis = user.getString("dateOfDiagnosis");
		phone = user.getString("phone");
		providerPhone = user.getString("providerPhone");

		medication1 = user.getString("medication1");
		medication2 = user.getString("medication2");
		medicationTime1 = user.getDate("medicationTime1");
		medicationTime2 = user.getDate("medicationTime2");

		message = user.getString("message");
		appointmentsTime = user.getDate("appointmentsTime");
		refillTime = user.getDate("refillTime");

		mins = user.getInt("mins");
		snoozeTime = user.getInt("snoozeTime");
		notTakenCount = user.getInt("notTakenCount");
		takenCount = user.getInt("takenCount");
		buck = user.getInt("buck");
	}

}
