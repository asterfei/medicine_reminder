package com.example.medicinereminder;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SnoozeActivity extends Activity {
	
	public static final int TakeOption_ID = 1;
	private int mins = 0;
	private Database data = Database.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_snooze);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_snooze, menu);
		return true;
	}
	
	public void onRMContinueButtonClick(View view){
		EditText time = (EditText)findViewById(R.id.editText2);
		try {
			mins = Integer.parseInt(time.getText().toString());
		}
		catch (Exception e){
			new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a number").setNeutralButton("close",null).show();
			return;
		}
		
		data.snoozeTime = mins;
		
		Database database = Database.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, database.medicationTime1.getHour());
		cal.set(Calendar.MINUTE, database.medicationTime1.getMinute() + database.snoozeTime);
		cal.set(Calendar.SECOND, 05);
		
		Intent intent = new Intent(this, Mote.class);
		intent.putExtra("Message", database.message);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, 
				new Intent(this.getApplicationContext(), AlarmActivity.class),0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
		
		Intent i = new Intent(this, TakeOption.class);
		startActivityForResult(i, TakeOption_ID);
	}

}
