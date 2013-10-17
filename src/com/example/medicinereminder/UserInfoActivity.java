package com.example.medicinereminder;

import java.util.Date;

import com.parse.ParseObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class UserInfoActivity extends Activity {

	public static final int MedicationActivity_ID = 1;
	private String username = "";
	private String password = "";
	private String first_name = "";
	private String last_name = "";
	private String dateOfDiagnosis = "";
	private String viralLoad = "";
	private String phone = "";
	private String providerPhone = "";
	private Database data = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_info, menu);
		return true;
	}

	public void onContinueButtonClick(View view) {
		EditText e1 = (EditText) findViewById(R.id.editText1);
		username = e1.getText().toString();
		Log.i("Info", username);

		EditText e2 = (EditText) findViewById(R.id.editText2);
		password = e2.getText().toString();
		Log.i("Info", password);

		EditText e3 = (EditText) findViewById(R.id.editText3);
		first_name = e3.getText().toString();
		Log.i("Info", first_name);

		EditText e4 = (EditText) findViewById(R.id.editText4);
		last_name = e4.getText().toString();
		Log.i("Info", last_name);
		data.lastName = last_name;

		EditText e5 = (EditText) findViewById(R.id.editText5);
		dateOfDiagnosis = e5.getText().toString();
		Log.i("Info", dateOfDiagnosis);
		data.dateOfDiagnosis = dateOfDiagnosis;

		EditText e6 = (EditText) findViewById(R.id.editText6);
		viralLoad = e6.getText().toString();
		Log.i("Info", viralLoad);
		data.viralLoad = viralLoad;

		EditText e7 = (EditText) findViewById(R.id.editText7);
		phone = e7.getText().toString();
		Log.i("Info", phone);
		data.phone = phone;

		EditText e8 = (EditText) findViewById(R.id.editText8);
		providerPhone = e8.getText().toString();
		Log.i("Info", providerPhone);
		data.providerPhone = providerPhone;

		if (username.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (password.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		}

		else if (first_name.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (last_name.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (dateOfDiagnosis.equals("")
				|| !dateOfDiagnosis.matches("[\\d]{2}/[\\d]{2}/[\\d]{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a date of diagnosis")
					.setNeutralButton("close", null).show();
		} else if (viralLoad.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (phone.equals("") || !phone.matches("(\\d{3}-?){1,2}\\d{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a valid phone number")
					.setNeutralButton("close", null).show();
		} else if (providerPhone.equals("")
				|| !providerPhone.matches("(\\d{3}-?){1,2}\\d{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a valid provider phone number")
					.setNeutralButton("close", null).show();
		} else {
			ParseObject userInfo = new ParseObject("UserInfo");
			userInfo.put("Username", username);
			userInfo.put("Password", password);
			userInfo.put("first_name", first_name);
			userInfo.put("last_name", last_name);
			userInfo.put("dateOfDiagnosis", dateOfDiagnosis);
			userInfo.put("viralLoad", viralLoad);
			userInfo.put("phone", phone);
			userInfo.put("providerPhone", providerPhone);
			userInfo.put("notTakenCount", 0);
			userInfo.put("takenCount", 0);
//			userInfo.put("medication1", "");
//			userInfo.put("medication2", "");
//			userInfo.put("medicationTime1", "");
//			userInfo.put("medicationTime2", "");
//			userInfo.put("message", "");
//			userInfo.put("appointmentsTime", "");
//			userInfo.put("refillTime", "");
//			userInfo.put("snoozeTime", 0);
//			userInfo.put("mins", 0);
			userInfo.saveInBackground();
			data.objectId = userInfo.getObjectId();
			
			ParseObject userLog = new ParseObject("UserLog");
			userLog.put("UserName", data.userName);
			userLog.put("From", "UserInfoActivity");
			userLog.put("To", "MedicationActivity");
			userLog.saveInBackground();

			Intent i = new Intent(this, MedicationActivity.class);
			startActivityForResult(i, MedicationActivity_ID);
		}

	}

}
