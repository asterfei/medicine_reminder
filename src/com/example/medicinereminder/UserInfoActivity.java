package com.example.medicinereminder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
	private String firstName = "";
	private String lastName = "";
	private String dateOfDiagnosis = "";
	private String viralLoad = "";
	private String phone = "";
	private String providerPhone = "";
	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();

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
		username = e1.getText().toString().trim();
		Log.i("Info", username);

		EditText e2 = (EditText) findViewById(R.id.editText2);
		password = e2.getText().toString().trim();
		Log.i("Info", password);

		EditText e3 = (EditText) findViewById(R.id.editText3);
		firstName = e3.getText().toString().trim();
		Log.i("Info", firstName);
		data.firstName = firstName;

		EditText e4 = (EditText) findViewById(R.id.editText4);
		lastName = e4.getText().toString().trim();
		Log.i("Info", lastName);
		data.lastName = lastName;

		EditText e5 = (EditText) findViewById(R.id.editText5);
		dateOfDiagnosis = e5.getText().toString().trim();
		Log.i("Info", dateOfDiagnosis);
		data.dateOfDiagnosis = dateOfDiagnosis;

		EditText e6 = (EditText) findViewById(R.id.editText6);
		viralLoad = e6.getText().toString().trim();
		Log.i("Info", viralLoad);
		data.viralLoad = viralLoad;

		EditText e7 = (EditText) findViewById(R.id.editText7);
		phone = e7.getText().toString().trim();
		Log.i("Info", phone);
		data.phone = phone;

		EditText e8 = (EditText) findViewById(R.id.editText8);
		providerPhone = e8.getText().toString().trim();
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

		else if (firstName.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (lastName.equals("")) {
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
			boolean userExist = false;
			ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
			query.selectKeys(Arrays.asList("Username"));
			try {
				List<ParseObject> results = query.find();
				for (ParseObject user : results) {
					String tempUser = user.getString("Username");
					if (tempUser.equals(username)) {
						userExist = true;
					}
				}
			} catch (ParseException e) {
				Log.i("Info", "Error: " + e.getMessage());
			}

			if (userExist) {
				new AlertDialog.Builder(this).setTitle("Error")
						.setMessage("The userName is existed!")
						.setNeutralButton("close", null).show();
			} else {
				ParseObject userInfo = new ParseObject("UserInfo");
				userInfo.put("Username", username);
				userInfo.put("Password", password);
				userInfo.put("firstName", firstName);
				userInfo.put("lastName", lastName);
				userInfo.put("dateOfDiagnosis", dateOfDiagnosis);
				userInfo.put("viralLoad", viralLoad);
				userInfo.put("phone", phone);
				userInfo.put("providerPhone", providerPhone);
				userInfo.put("notTakenCount", 0);
				userInfo.put("takenCount", 0);
				userInfo.put("buck", 0);
				userInfo.saveInBackground();
				Log.i("I1", userInfo.getString("Username"));
			

				ParseObject newAvatar = new ParseObject("Avatar");
				newAvatar.put("userName", username);
				newAvatar.saveInBackground();
				avatar.userName = username;

				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Avatar");
				try {
					List<ParseObject> results3 = query3.find();
					for (ParseObject ava : results3) {
						String tempUser2 = ava.getString("userName");
						if (tempUser2.equals(username)) {
							avatar.objectId = ava.getObjectId();
						}
					}
				} catch (ParseException e) {
					Log.i("Info", "Error: " + e.getMessage());
				}

				ParseQuery<ParseObject> query2 = ParseQuery
						.getQuery("UserInfo");
				try {
					List<ParseObject> results2 = query2.find();
					for (ParseObject user : results2) {
						String tempUser = user.getString("Username");
						if (tempUser.equals(username)) {
							Log.i("Info3", user.getObjectId());
							data.objectId = user.getObjectId();
						}
					}
				} catch (ParseException e) {
					Log.i("Info", "Error: " + e.getMessage());
				}
				
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

}
