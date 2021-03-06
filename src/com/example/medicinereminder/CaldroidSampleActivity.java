package com.example.medicinereminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.parse.ParseObject;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class CaldroidSampleActivity extends FragmentActivity {
	private boolean undo = false;
	private CaldroidFragment caldroidFragment;
	private CaldroidFragment dialogCaldroidFragment;
	private final Database data = Database.getInstance();

	protected void setCustomResourceForDates() {

		// Min date is last 7 days
		// cal.add(Calendar.DATE, -18);
		List<Date> tookDates = data.tookDates;
		for (Date da : tookDates) {
			caldroidFragment.setBackgroundResourceForDate(R.color.blue, da);
		}
		
		List<Date> picDates = data.picDates;
		for (Date da : picDates) {
			caldroidFragment.setBackgroundResourceForDate(R.color.green, da);
		}
		
		caldroidFragment.setBackgroundResourceForDate(R.color.red, data.appointmentsTime);
		
		caldroidFragment.setBackgroundResourceForDate(R.color.purple, data.refillTime);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_calendar);

		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

		// Setup caldroid fragment
		// **** If you want normal CaldroidFragment, use below line ****
		caldroidFragment = new CaldroidFragment();

		// //////////////////////////////////////////////////////////////////////
		// **** This is to show customized fragment. If you want customized
		// version, uncomment below line ****
		// caldroidFragment = new CaldroidSampleCustomFragment();


		// If Activity is created after rotation
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState,
					"CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
			args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

			// Uncomment this to customize startDayOfWeek
			// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
			// CaldroidFragment.TUESDAY); // Tuesday
			caldroidFragment.setArguments(args);
		}

		// setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();

		setCustomResourceForDates();

		// Setup listener
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Toast.makeText(getApplicationContext(), formatter.format(date),
						Toast.LENGTH_LONG).show();
					showDialog(date);

			}

			@Override
			public void onChangeMonth(int month, int year) {
				/*String text = "month: " + month + " year: " + year;
				Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT).show();*/
			}

			@Override
			public void onLongClickDate(Date date, View view) {
				/*Toast.makeText(getApplicationContext(),
						"Long click " + formatter.format(date),
						Toast.LENGTH_SHORT).show();*/
			}

			@Override
			public void onCaldroidViewCreated() {
				/*if (caldroidFragment.getLeftArrowButton() != null) {
					Toast.makeText(getApplicationContext(),
							"Caldroid view is created", Toast.LENGTH_SHORT)
							.show();
				}*/
			}

		};

		caldroidFragment.setCaldroidListener(listener);

	}
	
	public void onRnturnClick(View view) {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "AvatarImageEdit");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(CaldroidSampleActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void showDialog(final Date date){
		new AlertDialog.Builder(this)
		.setTitle("Camera")
		.setMessage("Do you want to check the picture you took on this day?")
		.setNegativeButton("continue",
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface dialoginterface, int k) {
						Intent intent = new Intent();
						intent.setClass(CaldroidSampleActivity.this, ImageDisplayActivity.class);
						intent.putExtra("month", date.getMonth());
						intent.putExtra("day", date.getDate());
						intent.putExtra("year", date.getYear()+1900);
						startActivity(intent);
					}
				}).setPositiveButton("cancel", null).show();
	}

	/**
	 * Save current states of the Caldroid here
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

		if (dialogCaldroidFragment != null) {
			dialogCaldroidFragment.saveStatesToKey(outState,
					"DIALOG_CALDROID_SAVED_STATE");
		}
	}

}
