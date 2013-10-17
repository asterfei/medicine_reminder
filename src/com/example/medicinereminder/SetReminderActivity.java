package com.example.medicinereminder;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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

	public void onRMContinueButtonClick(View view) {
		EditText e1 = (EditText) findViewById(R.id.message);
		mes = e1.getText().toString();
		Log.i("Info", mes);
		data.message = mes.toString();

		if (mes.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a reminder message")
					.setNeutralButton("close", null).show();
		} else {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
			query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
				public void done(ParseObject object, ParseException e) {
					if (e == null) {
						object.put("message", mes);
						object.saveInBackground();
					} else {
						
					}
				}
			});
			
			ParseObject userLog = new ParseObject("UserLog");
			userLog.put("UserName", data.userName);
			userLog.put("From", "SetRemainderActivity");
			userLog.put("To", "AppointmentsActivity");
			userLog.saveInBackground();

			Intent i = new Intent(this, AppointmentsActivity.class);
			startActivityForResult(i, Appointments_ID);
		}
	}

}
