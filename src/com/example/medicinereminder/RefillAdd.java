package com.example.medicinereminder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class RefillAdd extends Activity {

	private final int PICK_1 = 1;

	private Date refillTime = new Date();
	private Database data = Database.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.refill_add);

		View.OnClickListener dateBtnListener = new BtnOnClickListener(PICK_1);

		Button btnDate = (Button) findViewById(R.id.add_refill_pick1);
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
					EditText editText = (EditText) findViewById(R.id.add_refill);
					editText.setText((month + 1) + "/" + dayOfMonth + "/"
							+ year);
					refillTime.setDate(dayOfMonth);
					refillTime.setMonth(month);
					refillTime.setYear(year);
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

	public void onContinueClick(View view) {

		EditText e1 = (EditText) findViewById(R.id.add_refill);

		if (e1.getText().toString().equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please pick a time for next refill time")
					.setNeutralButton("close", null).show();
		} else {
			setUserData();
			addRefill();
//			setMedicationAlarm();
			NextActivity();
		}
	}

	public void setUserData() {
		data.refillTime = refillTime;

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

	public void addRefill() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("refillTime", refillTime);
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
		userLog.put("from", "RefillAdd");
		userLog.put("to", "AvatarImageAdd");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(RefillAdd.this, AvatarImageAdd.class);
		startActivity(intent);
	}

//	@SuppressWarnings("deprecation")
//	public void setMedicationAlarm() {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.HOUR_OF_DAY, data.medicationTime1.getHours());
//		cal.set(Calendar.MINUTE, data.medicationTime1.getMinutes());
//		cal.set(Calendar.SECOND, 05);
//
//		Intent intent = new Intent(this, Mote.class);
//		intent.putExtra("Reminder message for " + data.medication1,
//				data.message);
//		PendingIntent pendingIntent = PendingIntent.getActivity(
//				this.getApplicationContext(), 0,
//				new Intent(this.getApplicationContext(), AlarmActivity.class),
//				0);
//		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//		alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
//				pendingIntent);
//	}
}