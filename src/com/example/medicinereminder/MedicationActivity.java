package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class MedicationActivity extends Activity {
	public static final int ReminderTimeActivity_ID = 1;
	private String[] medication = {"","",""};
	private Time[] time = {null,null};
	private Database data = Database.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_medication, menu);
		return true;
	}
	
	public void onMContinueButtonClick(View view){
		EditText e1 = (EditText)findViewById(R.id.editText1);
		medication[0] = e1.getText().toString();
		Log.i("Info", medication[0]);
		data.medication1 = medication[0];
		
		
		EditText e2 = (EditText)findViewById(R.id.editText2);
		medication[1] = e2.getText().toString();
		Log.i("Info", medication[1]);
		data.medication2 = medication[1];
	
		if (medication[0].equals("") && medication[1].equals("") && medication[2].equals("")){
			new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a medication").setNeutralButton("close",null).show();
			return;
		}
		
		TimePicker d1 = (TimePicker)findViewById(R.id.timePicker1);
		time[0] = new Time(d1.getCurrentHour(),d1.getCurrentMinute());
		Log.i("Info", time[0].toString());
		data.medicationTime1 = time[0];
		
		TimePicker d2 = (TimePicker)findViewById(R.id.timePicker1);
		time[1] = new Time(d2.getCurrentHour(),d2.getCurrentMinute());
		Log.i("Info", time[1].toString());
		data.medicationTime2 = time[1];
		
		
		Intent i = new Intent(this, ReminderTimeActivity.class);
		startActivityForResult(i, ReminderTimeActivity_ID);
	}

}
