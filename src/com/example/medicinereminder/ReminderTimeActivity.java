package com.example.medicinereminder;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class ReminderTimeActivity extends Activity {
	public static int SetReminderActivity_ID = 1;
	private int mins = 0;
	private Database data = Database.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder_time);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_reminder_time, menu);
		return true;
	}
	
	public void onRContinueButtonClick(View view){
		EditText time = (EditText)findViewById(R.id.editText1);
		try {
			mins = Integer.parseInt(time.getText().toString());
		}
		catch (Exception e){
			new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a number").setNeutralButton("close",null).show();
			return;
		}
		
		Log.i("Info", String.valueOf(mins));
		data.mins = mins;
		
		Intent i = new Intent(this, SetReminderActivity.class);
		startActivityForResult(i, SetReminderActivity_ID);
	}
	
	

}
