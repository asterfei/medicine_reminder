package com.example.medicinereminder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

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
	private String medication1 = "";
	private String medication2 = "";
	private Date medicationTime1 = new Date();
	private Date medicationTime2 = new Date();
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

	public void onMContinueButtonClick(View view) {
		EditText e1 = (EditText) findViewById(R.id.editText1);
		medication1 = e1.getText().toString();
		Log.i("Info", medication1);
		data.medication1 = medication1;

		EditText e2 = (EditText) findViewById(R.id.editText2);
		medication2 = e2.getText().toString();
		Log.i("Info", medication2);
		data.medication2 = medication2;

		if (medication1.equals("") && medication2.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a medication")
					.setNeutralButton("close", null).show();
			return;
		}

		TimePicker d1 = (TimePicker) findViewById(R.id.timePicker1);
		medicationTime1.setHours(d1.getCurrentHour());
		medicationTime1.setMinutes(d1.getCurrentMinute());
		Log.i("Info", medicationTime1.toString());
		data.medicationTime1 = medicationTime1;

		TimePicker d2 = (TimePicker) findViewById(R.id.timePicker2);
		medicationTime2.setHours(d2.getCurrentHour());
		medicationTime2.setMinutes(d2.getCurrentMinute());
		Log.i("Info", medicationTime2.toString());
		data.medicationTime2 = medicationTime2;

		if (data.objectId.trim().equals("")) {
			ParseQuery<ParseObject> query2 = ParseQuery.getQuery("UserInfo");
			try {
				List<ParseObject> results2 = query2.find();
				for (ParseObject user : results2) {
					String tempUser = user.getString("Username");
					if (tempUser.equals(data.userName)) {
						Log.i("Info3", user.getObjectId());
						data.objectId = user.getObjectId();
					}
				}
			} catch (ParseException e) {
				Log.i("Info", "Error: " + e.getMessage());
			}
		}

		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("medication1", medication1);
					object.put("medication2", medication2);
					object.put("medicationTime1", medicationTime1);
					object.put("medicationTime2", medicationTime2);
					object.saveInBackground();
				} else {

				}
			}
		});

		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "MedicationActivity");
		userLog.put("To", "ReminderTimeActivity");
		userLog.saveInBackground();

		Intent i = new Intent(this, ReminderTimeActivity.class);
		startActivityForResult(i, ReminderTimeActivity_ID);
	}

}
