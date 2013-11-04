package com.example.medicinereminder;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class AlarmActivity extends Activity {
	// AlarmSet alarmSet;
	String time;
	public static final int TakeOption_ID = 1;
	private Database data = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "");
		userLog.put("To", "AlarmActivity");
		userLog.saveInBackground();
		
		setContentView(R.layout.alarm_page);
		TextView out = (TextView) findViewById(R.id.alarmMessage);
		out.append(data.message);
	}

	public class SpinnerActivity extends Activity implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			// An item was selected. You can retrieve the selected item using
			time = (String) parent.getItemAtPosition(pos);
		}

		public void onNothingSelected(AdapterView<?> parent) {
			time = "5";
		}
	}

	public void onIgnoreButtonClick(View view) {
		finish();
	}

	public void onOpenButtonClick(View view) {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "AlarmActivity");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();
		
		Intent i = new Intent(this, MainActivity.class);
		startActivityForResult(i, TakeOption_ID);

	}

}