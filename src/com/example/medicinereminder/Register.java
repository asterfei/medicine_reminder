package com.example.medicinereminder;

import java.util.Arrays;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity {

	private String username = "";
	private String password = "";
	private String firstName = "";
	private String lastName = "";
	private String dateOfDiagnosis = "";
	private String phone = "";
	private String providerPhone = "";
	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	}

	public void onSubmitClick(View view) {
		EditText e1 = (EditText) findViewById(R.id.register_user);
		username = e1.getText().toString().trim();
		Log.i("Info", username);

		EditText e2 = (EditText) findViewById(R.id.register_passwd);
		password = e2.getText().toString().trim();
		Log.i("Info", password);

		EditText e3 = (EditText) findViewById(R.id.register_firstname);
		firstName = e3.getText().toString().trim();
		Log.i("Info", firstName);

		EditText e4 = (EditText) findViewById(R.id.register_lastname);
		lastName = e4.getText().toString().trim();
		Log.i("Info", lastName);

		EditText e5 = (EditText) findViewById(R.id.register_date);
		dateOfDiagnosis = e5.getText().toString().trim();
		Log.i("Info", dateOfDiagnosis);

		EditText e7 = (EditText) findViewById(R.id.register_phone);
		phone = e7.getText().toString().trim();
		Log.i("Info", phone);

		EditText e8 = (EditText) findViewById(R.id.register_docphone);
		providerPhone = e8.getText().toString().trim();
		Log.i("Info", providerPhone);

		if (username.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter username")
					.setNeutralButton("close", null).show();
		} else if (password.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter password")
					.setNeutralButton("close", null).show();
		} else if (firstName.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter first name")
					.setNeutralButton("close", null).show();
		} else if (lastName.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter last name")
					.setNeutralButton("close", null).show();
		} else if (dateOfDiagnosis.equals("")
				|| !dateOfDiagnosis.matches("[\\d]{2}/[\\d]{2}/[\\d]{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter date of diagnosis")
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
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
			query.selectKeys(Arrays.asList("username"));
			try {
				List<ParseObject> results = query.find();
				for (ParseObject user : results) {
					if (user.getString("username").equals(username)) {
						userExist = true;
					}
				}
			} catch (ParseException e) {
				Log.i("Error", e.getMessage());
			}

			if (userExist) {
				new AlertDialog.Builder(this).setTitle("Error")
						.setMessage("The userName is existed!")
						.setNeutralButton("close", null).show();
			} else {
				createNewUser();
				createNewAvatar();
				setAvatar();
				setUser();
				NextActivity();
			}
		}

	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", username);
		userLog.put("from", "Register");
		userLog.put("to", "MedicationAdd");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(Register.this, MedicationAdd.class);
		startActivity(intent);
	}

	public void onReturnClick(View view) {
		Intent intent = new Intent();
		intent.setClass(Register.this, Welcome.class);
		startActivity(intent);
	}

	public void createNewUser() {
		ParseObject userInfo = new ParseObject("Users");
		userInfo.put("username", username);
		userInfo.put("password", password);
		userInfo.put("firstName", firstName);
		userInfo.put("lastName", lastName);
		userInfo.put("dateOfDiagnosis", dateOfDiagnosis);
		userInfo.put("phone", phone);
		userInfo.put("providerPhone", providerPhone);
		userInfo.put("notTakenCount", 0);
		userInfo.put("takenCount", 0);
		userInfo.put("buck", 0);
		userInfo.saveInBackground();
		Log.i("Info", "Created new user for " + username);
	}

	public void createNewAvatar() {
		ParseObject newAvatar = new ParseObject("Avatars");
		newAvatar.put("userName", username);
		newAvatar.saveInBackground();
	}

	public void setAvatar() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Avatars");
		try {
			List<ParseObject> results = query.find();
			for (ParseObject ava : results) {
				if (ava.getString("userName").equals(username)) {
					avatar.objectId = ava.getObjectId();
					avatar.userName = username;
				}
			}
		} catch (ParseException e) {
			Log.i("Error", e.getMessage());
		}
	}

	public void setUser() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		try {
			List<ParseObject> results = query.find();
			for (ParseObject user : results) {
				if (user.getString("username").equals(username)) {
					data.objectId = user.getObjectId();
					data.userName = username;
					data.firstName = firstName;
					data.lastName = lastName;
					data.dateOfDiagnosis = dateOfDiagnosis;
					data.phone = phone;
					data.providerPhone = providerPhone;
				}
			}
		} catch (ParseException e) {
			Log.i("Error", e.getMessage());
		}
	}
}
