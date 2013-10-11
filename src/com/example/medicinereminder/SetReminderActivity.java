package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SetReminderActivity extends Activity {
	
	public static final int Appointments_ID = 1;
	private String mes = "";
	private Database data = Database.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_reminder);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_set_reminder, menu);
		return true;
	}
	
	public void onRMContinueButtonClick(View view){
		EditText e1 = (EditText)findViewById(R.id.message);
		mes = e1.getText().toString();
		Log.i("Info", mes);
		data.message = mes.toString();
		
		if (mes.equals("")){
			new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a reminder message").setNeutralButton("close",null).show();
		}
		else{
			Intent i = new Intent(this, AppointmentsActivity.class);
			startActivityForResult(i, Appointments_ID);
		}
	}

}
