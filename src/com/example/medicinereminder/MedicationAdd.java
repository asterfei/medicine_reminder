package com.example.medicinereminder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MedicationAdd extends Activity {

	private final int PICK_1 = 1;

	private final int PICK_2 = 2;

	private String medication1 = "";
	private String medication2 = "";
	private final Date medicationTime1 = new Date();
	private final Date medicationTime2 = new Date();
	private String mins = "";
	private String message = "";

	private final Database data = Database.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medication_add);

		View.OnClickListener timeBtnListener1 = new BtnOnClickListener(PICK_1);

		Button pickTime1 = (Button) findViewById(R.id.add_pick1);
		pickTime1.setOnClickListener(timeBtnListener1);

		View.OnClickListener timeBtnListener2 = new BtnOnClickListener(PICK_2);
		Button pickTime2 = (Button) findViewById(R.id.add_pick2);
		pickTime2.setOnClickListener(timeBtnListener2);
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		Calendar calendar = Calendar.getInstance();

		Dialog dialog = null;
		switch (id) {
		case PICK_1:
			TimePickerDialog.OnTimeSetListener timeListener1 = new TimePickerDialog.OnTimeSetListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onTimeSet(TimePicker timerPicker, int hourOfDay,
						int minute) {

					EditText editText = (EditText) findViewById(R.id.add_med1_time);
					medicationTime1.setHours(hourOfDay);
					medicationTime1.setMinutes(minute);
					String display = setDisplay(hourOfDay, minute);
					editText.setText(display);
				}
			};

			dialog = new TimePickerDialog(this, timeListener1,
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE), false); // æ˜¯å¦ä¸ºäºŒåå››åˆ¶
			break;

		case PICK_2:
			TimePickerDialog.OnTimeSetListener timeListener2 = new TimePickerDialog.OnTimeSetListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onTimeSet(TimePicker timerPicker, int hourOfDay,
						int minute) {

					EditText editText = (EditText) findViewById(R.id.add_med2_time);
					medicationTime2.setHours(hourOfDay);
					medicationTime2.setMinutes(minute);
					String display = setDisplay(hourOfDay, minute);
					editText.setText(display);
				}
			};

			dialog = new TimePickerDialog(this, timeListener2,
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE), false); // æ˜¯å¦ä¸ºäºŒåå››åˆ¶
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

	public void onContinueClick(View view) {
		EditText e1 = (EditText) findViewById(R.id.add_med1);
		medication1 = e1.getText().toString().trim();
		Log.i("medication1", medication1);

		EditText e2 = (EditText) findViewById(R.id.add_med2);
		medication2 = e2.getText().toString().trim();
		Log.i("medication2", medication2);

		EditText e3 = (EditText) findViewById(R.id.add_med1_time);

		EditText e4 = (EditText) findViewById(R.id.add_med2_time);

		EditText e5 = (EditText) findViewById(R.id.add_message);
		message = e5.getText().toString().trim();
		Log.i("message", message);

		EditText e6 = (EditText) findViewById(R.id.add_minute);
		mins = e6.getText().toString().trim();
		Log.i("mins", mins);

		if (medication1.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter medication 1")
					.setNeutralButton("close", null).show();
		} else if (medication2.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter medication 2")
					.setNeutralButton("close", null).show();
		} else if (e3.getText().toString().equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please pick a time for medication 1")
					.setNeutralButton("close", null).show();
		} else if (e4.getText().toString().equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please pick a time for medication 2")
					.setNeutralButton("close", null).show();
		} else if (message.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter message for alarm reminder")
					.setNeutralButton("close", null).show();
		} else if (mins.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter minutes before alarm reminder")
					.setNeutralButton("close", null).show();
		} else {
			setUserData();
			addMedication();
			NextActivity();
		}
	}

	public void setUserData() {
		data.medication1 = medication1;
		data.medication2 = medication2;
		data.medicationTime1 = medicationTime1;
		data.medicationTime2 = medicationTime2;
		data.mins = Integer.parseInt(mins);
		data.message = message;
		//Date medicinealarttime1 = medicationTime1 -; 

		// this is where notification edits begin
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, medicationTime1.getHours());
		cal.set(Calendar.MINUTE, medicationTime1.getMinutes());
		cal.set(Calendar.SECOND, 05);
		cal.add(Calendar.MINUTE, -Integer.parseInt(mins));
		
		Intent intent = new Intent(this, Mote.class);
		int appWidgetId = 0;
		intent.putExtra("intentId", appWidgetId);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				this.getApplicationContext(), appWidgetId, new Intent(this.getApplicationContext(), AlarmActivity.class),0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.HOUR_OF_DAY, medicationTime2.getHours());
		cal2.set(Calendar.MINUTE, medicationTime2.getMinutes());
		cal2.set(Calendar.SECOND, 05);
		cal2.add(Calendar.MINUTE, -Integer.parseInt(mins));

		Intent intent2 = new Intent(this, Mote.class);
		int appWidgetId2 = 1;
		intent2.putExtra("intentId", appWidgetId2);
		PendingIntent pendingIntent2 = PendingIntent.getActivity(
				this.getApplicationContext(), appWidgetId2, new Intent(this.getApplicationContext(), AlarmActivity.class),0);
		AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager2.set(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), pendingIntent2);

		
		// this is where notification edits end

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

	public void addMedication() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("medication1", medication1);
					object.put("medication2", medication2);
					object.put("medicationTime1", medicationTime1);
					object.put("medicationTime2", medicationTime2);
					object.put("message", message);
					object.put("mins", Integer.parseInt(mins));
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
		userLog.put("from", "MedicationAdd");
		userLog.put("to", "AppointmentAdd");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(MedicationAdd.this, AppointmentAdd.class);
		//intent.setClass(MedicationAdd.this, MainActivity.class);
		startActivity(intent);
	}

	public String setDisplay(int hour, int minute) {
		String display = "";
		if (hour < 10) {
			if (minute < 10) {
				display = "0" + hour + " : 0" + minute + " AM";
			} else {
				display = "0" + hour + " : " + minute + " AM";
			}
		} else if (hour < 12) {
			if (minute < 10) {
				display = hour + " : 0" + minute + " AM";
			} else {
				display = hour + " : " + minute + " AM";
			}
		} else if (hour == 12) {
			if (minute < 10) {
				display = hour + " : 0" + minute + " PM";
			} else {
				display = hour + " : " + minute + " PM";
			}
		} else if (hour < 22) {
			hour = hour - 12;
			if (minute < 10) {
				display = "0" + hour + " : 0" + minute + " PM";
			} else {
				display = "0" + hour + " : " + minute + " PM";
			}
		} else {
			hour = hour - 12;
			if (minute < 10) {
				display = hour + " : 0" + minute + " PM";
			} else {
				display = hour + " : " + minute + " PM";
			}
		}

		return display;
	}

}