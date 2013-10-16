package com.example.medicinereminder;


import java.util.Arrays;
import java.util.List;

import com.parse.GetCallback;
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

		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.selectKeys(Arrays.asList("Username", "Password"));
		;
		try {
			List<ParseObject> results = query.find();
			for (ParseObject user : results) {
				String tempUser = user.getString("Username");
				String tempPass = user.getString("Password");
				if (tempPass.equals(password) && tempUser.equals(username)) {
					flag = true;
				}
			}
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}

		if (username.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (password.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave any fields blank")
					.setNeutralButton("close", null).show();
		} else if (!flag) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Wrong username or password!")
					.setNeutralButton("close", null).show();
		} else {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, MedicationActivity.class);
			startActivity(intent);
		}
	}

}
