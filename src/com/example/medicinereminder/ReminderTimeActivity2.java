package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TimePicker;

public class ReminderTimeActivity2 extends Activity {
	public static int TakeOption_ID = 1;
	private int hour = 0;
	private int mins = 0;
	
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
		TimePicker time = (TimePicker)findViewById(R.id.timePicker1);
		time.clearFocus();
		
		hour = time.getCurrentHour();
		mins = time.getCurrentMinute();
		
		Log.i("Info", String.valueOf(hour) + " : " + String.valueOf(mins));
		
		Intent i = new Intent(this, TakeOption.class);
		startActivityForResult(i, TakeOption_ID);
	}

}