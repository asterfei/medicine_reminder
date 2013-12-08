package com.example.medicinereminder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class AppointmentEdit extends Activity {

	private final int PICK_1 = 1;

	private Database data = Database.getInstance();

	private Date appointmentsTime = data.appointmentsTime;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appointment_edit);

		int day = data.appointmentsTime.getDate();
		int month = data.appointmentsTime.getMonth();
		int year = data.appointmentsTime.getYear()+1900;
		
		Log.i("day", Integer.toString(day));

		TextView textView = (TextView) findViewById(R.id.appointment);
		textView.setText(Integer.toString(month + 1) + "/" + Integer.toString(day)
				+ "/" + Integer.toString(year));

		View.OnClickListener dateBtnListener = new BtnOnClickListener(PICK_1);

		Button btnDate = (Button) findViewById(R.id.appointment_pick1);
		btnDate.setOnClickListener(dateBtnListener);
	}

	protected Dialog onCreateDialog(int id) {

		Calendar calendar = Calendar.getInstance();

		Dialog dialog = null;
		switch (id) {
		case PICK_1:
			DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
				@SuppressWarnings("deprecation")
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					EditText editText = (EditText) findViewById(R.id.appointment);
					editText.setText((month + 1) + "/" + dayOfMonth + "/"
							+ year);
					appointmentsTime.setDate(dayOfMonth);
					appointmentsTime.setMonth(month);
					appointmentsTime.setYear(year-1900);
				}
			};
			dialog = new DatePickerDialog(this, dateListener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			break;

		default:
			break;
		}
		return dialog;
	}

	private class BtnOnClickListener implements View.OnClickListener {

		private int dialogId = 0;

		public BtnOnClickListener(int dialogId) {
			this.dialogId = dialogId;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View view) {
			showDialog(dialogId);
		}

	}

	public void onSubmitClick(View view) {

		EditText e1 = (EditText) findViewById(R.id.appointment);

		if (e1.getText().toString().equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please pick a time for next appointment time")
					.setNeutralButton("close", null).show();
		} else {
			setUserData();
			EditAppointment();
			NextActivity();
		}
	}

	public void setUserData() {
		
		data.appointmentsTime = appointmentsTime;

		if (data.objectId.trim().equals("")) {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
			try {
				List<ParseObject> results = query.find();
				for (ParseObject user : results) {
					String tempUser = user.getString("username");
					if (tempUser.equals(data.userName)) {
						data.objectId = user.getObjectId();
					}
				}
			} catch (ParseException e) {
				Log.i("Error", e.getMessage());
			}
		}
	}

	public void EditAppointment() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("appointmentsTime", appointmentsTime);
					object.saveInBackground();
				} else {
					Log.i("Error", e.getMessage());
				}
			}
		});
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "AppointmentEdit");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AppointmentEdit.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void onReturnClick(View view) {
		NextActivity();
	}
	

}