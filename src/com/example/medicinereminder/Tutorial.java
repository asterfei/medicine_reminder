package com.example.medicinereminder;

import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Tutorial extends Activity {
	public static final int MainActivity_ID = 1;
	public static final int TakeOption_ID = 1;
	private Database data = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tutorial, menu);
		return true;
	}

	public void onTSUpContinueButtonClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "Tutorial");
		userLog.put("To", "MainActivity");
		userLog.saveInBackground();

		Intent i = new Intent(this, MainActivity.class);
		startActivityForResult(i, MainActivity_ID);
	}

	public void onTTMContinueButtonClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "Tutorial");
		userLog.put("To", "TakeOption");
		userLog.saveInBackground();

		Intent i = new Intent(this, TakeOption.class);
		startActivityForResult(i, TakeOption_ID);

	}
}
