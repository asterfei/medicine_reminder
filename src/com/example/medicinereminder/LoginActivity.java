package com.example.medicinereminder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	public static final int UserInfoActivity_ID = 1;
	public static final int MedicationActivity_ID = 1;
	private String username = "";
	private String password = "";
	private boolean flag = false;
	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void onNewUserClick(View view) {
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, UserInfoActivity.class);
		startActivity(intent);
	}

	public void onLoginClick(View view) {

		EditText e1 = (EditText) findViewById(R.id.widget33);
		username = e1.getText().toString();
		Log.i("Info", username);

		EditText e2 = (EditText) findViewById(R.id.widget35);
		password = e2.getText().toString();
		Log.i("Info", password);

		if (username.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (password.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
			try {
				List<ParseObject> results = query.find();
				for (ParseObject user : results) {
					String tempUser = user.getString("Username");
					String tempPass = user.getString("Password");
					if (tempPass.equals(password) && tempUser.equals(username)) {
						flag = true;
						data.userName = username;
						data.objectId = user.getObjectId();
						data.firstName = user.getString("firstName");
						data.lastName = user.getString("lastName");
						data.avatarnumber = user.getInt("avatarnumber");
						data.dateOfDiagnosis = user
								.getString("dateOfDiagnosis");
						data.viralLoad = user.getString("viralLoad");
						data.phone = user.getString("phone");
						data.providerPhone = user.getString("providerPhone");

						data.medication1 = user.getString("medication1");
						data.medication2 = user.getString("medication2");
						data.medicationTime1 = user.getDate("medicationTime1");
						data.medicationTime2 = user.getDate("medicationTime2");

						data.message = user.getString("message");
						data.appointmentsTime = user
								.getDate("appointmentsTime");
						data.refillTime = user.getDate("refillTime");

						data.snoozeTime = user.getInt("snoozeTime");
						data.mins = user.getInt("mins");
						data.notTakenCount = user.getInt("notTakenCount");
						data.takenCount = user.getInt("takenCount");
						data.buck = user.getInt("buck");
					}
				}
			} catch (ParseException e) {
				Log.i("Info", "Error: " + e.getMessage());
			}

			if (!flag) {
				new AlertDialog.Builder(this).setTitle("Error")
						.setMessage("Wrong username or password!")
						.setNeutralButton("close", null).show();
			} else {

				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Avatar");
				try {
					List<ParseObject> results = query3.find();
					for (ParseObject ava : results) {
						String tempUser2 = ava.getString("userName");
						if (tempUser2.equals(username)) {
							avatar.objectId = ava.getObjectId();
						}
					}
				} catch (ParseException e) {
					Log.i("Info", "Error: " + e.getMessage());
				}
				
				ParseObject userLog = new ParseObject("UserLog");
				userLog.put("UserName", data.userName);
				userLog.put("From", "LoginActivity");
				userLog.put("To", "MedicationActivity");
				userLog.saveInBackground();

				Intent intent = new Intent();
				// if (data.medication1.equals("")) {
				// intent.setClass(LoginActivity.this,
				// MedicationActivity.class);
				// } else {
				// intent.setClass(LoginActivity.this, HomeScreen.class);
				// }
				intent.setClass(LoginActivity.this, MedicationActivity.class);
				startActivity(intent);
			}
		}
	}

}
