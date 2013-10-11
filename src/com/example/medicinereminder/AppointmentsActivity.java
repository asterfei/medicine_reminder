package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker; 
import android.widget.TimePicker;
import android.widget.Toast;

public class AppointmentsActivity extends Activity {
	
	public static final int SetRefillsActivity_ID= 1;
	private DatePicker dpResult;
 
	private int year;
	private int month;
	private int day;
	private Database data = Database.getInstance();
 
	static final int DATE_DIALOG_ID = 999;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointments);
		
		
		
		//this is where notification edits begin
		Database database = Database.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, database.medicationTime1.getHour());
		cal.set(Calendar.MINUTE, database.medicationTime1.getMinute());
		cal.set(Calendar.SECOND, 05);
		
		Intent intent = new Intent(this, Mote.class);
		intent.putExtra("Message", database.message);
		PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, 
				new Intent(this.getApplicationContext(), AlarmActivity.class),0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
		//this is where notification edits end
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_appointments, menu);
		return true;
	}
	
	public void onAContinueButtonClick(View view){
		dpResult = (DatePicker) findViewById(R.id.dpResult);
		data.appointmentsYear = dpResult.getYear();
		data.appointmentsMonth = dpResult.getMonth();
		data.appointmentsDay = dpResult.getDayOfMonth();
		Intent i = new Intent(this, SetRefillsActivity.class);
		startActivityForResult(i, SetRefillsActivity_ID);
		
	}
	
	// display current date
		public void setCurrentDateOnView() {
	 
	 
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into datepicker
			dpResult.init(year, month, day, null);
	 
		}
		 
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case DATE_DIALOG_ID:
			   // set date picker as current date
			   return new DatePickerDialog(this, datePickerListener, 
	                         year, month,day);
			}
			return null;
		}
	 
		private DatePickerDialog.OnDateSetListener datePickerListener 
	                = new DatePickerDialog.OnDateSetListener() {
	 
			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;
	 
				// set selected date into datepicker also
				dpResult.init(year, month, day, null);
				
			}
		};
}
