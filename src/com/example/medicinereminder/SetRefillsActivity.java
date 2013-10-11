package com.example.medicinereminder;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

public class SetRefillsActivity extends Activity {
		public static final int Avatar_ID = 1;
		private DatePicker dpResult2;
		 
		private int year;
		private int month;
		private int day;
	 
		static final int DATE_DIALOG_ID = 999;
		private Database data = Database.getInstance();
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_set_refills);
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_set_refills, menu);
			return true;
		}
		
		public void onRefillContinueButtonClick(View view){
			dpResult2 = (DatePicker) findViewById(R.id.dpResult2);
			data.refillYear = dpResult2.getYear();
			data.refillMonth = dpResult2.getMonth();
			data.refillDay = dpResult2.getDayOfMonth();
			Intent i = new Intent(this, Avatar.class);
			startActivityForResult(i, Avatar_ID);
		}
		
		// display current date
				public void setCurrentDateOnView() {
			 
					dpResult2 = (DatePicker) findViewById(R.id.dpResult2);
			 
					final Calendar c = Calendar.getInstance();
					year = c.get(Calendar.YEAR);
					month = c.get(Calendar.MONTH);
					day = c.get(Calendar.DAY_OF_MONTH);
			 
					// set current date into datepicker
					dpResult2.init(year, month, day, null);
			 
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
						dpResult2.init(year, month, day, null);
						
			 
					}
				};

}
