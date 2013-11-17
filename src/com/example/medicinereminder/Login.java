package com.example.medicinereminder;

import java.util.List;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {
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

	public void onReturnClick(View view) {
		Intent intent = new Intent();
		intent.setClass(Login.this, Welcome.class);
		startActivity(intent);
	}

	public void onLoginClick(View view) {

		EditText e1 = (EditText) findViewById(R.id.login_user);
		username = e1.getText().toString().trim();
		Log.i("Info", username);

		EditText e2 = (EditText) findViewById(R.id.login_passwd);
		password = e2.getText().toString().trim();
		Log.i("Info", password);

		if (username.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave user name blank")
					.setNeutralButton("close", null).show();
		} else if (password.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("You cannot leave password blank")
					.setNeutralButton("close", null).show();
		} else {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
			try {
				List<ParseObject> results = query.find();
				for (ParseObject user : results) {
					if (user.getString("password").equals(password)
							&& user.getString("username").equals(username)) {
						flag = true;
						data.setUserData(user);
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

				ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Avatars");
				try {
					List<ParseObject> results = query2.find();
					for (ParseObject ava : results) {
						if (ava.getString("userName").equals(username)) {
							avatar.setAvatarDate(ava);
						}
					}
				} catch (ParseException e) {
					Log.i("Info", "Error: " + e.getMessage());
				}
//comment
				ParseObject userLog = new ParseObject("Logs");
				userLog.put("username", username);
				userLog.put("from", "Login");
				userLog.put("to", "MainActivity");
				userLog.saveInBackground();

				Intent intent = new Intent();
				intent.setClass(Login.this, MainActivity.class);
				startActivity(intent);
			}
		}
	}

}
