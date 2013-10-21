package com.example.medicinereminder;

import java.util.Calendar;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends Activity {

	public static final int UserInfoActivity_ID = 1;
	public static final int TakeOptionActivity_ID = 1;
	public static final int CheckProgressActivity_ID = 1;
	private Database data = Database.getInstance();
	private String firstname;
	private String lastname;
	TextView welcome;
	TextView appointment;
	TextView refill;
	TextView mediStreaks;
	ImageView avatar;
	private int avatarnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		firstname = data.firstName;
		lastname = data.lastName;
		welcome = (TextView) findViewById(R.id.textView3);
		welcome.setText("Welcome back, " + firstname + " " + lastname);
		avatar = (ImageView) findViewById(R.id.imageView1);
		avatarnumber = data.avatarnumber;
		if (avatarnumber == 1)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.spider1));
		else if (avatarnumber == 2)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.iron2));
		else if (avatarnumber == 3)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.superman3));
		else if (avatarnumber == 4)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.batman4));
		else if (avatarnumber == 5)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.captain5));
		else if (avatarnumber == 6)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.cat6));
		else if (avatarnumber == 7)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.greenman7));
		else if (avatarnumber == 8)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.thor8));
		else if (avatarnumber == 9)
			avatar.setImageDrawable(getResources().getDrawable(
					R.drawable.loki9));

		appointment = (TextView) findViewById(R.id.textView6);
		refill = (TextView) findViewById(R.id.textView7);
		appointment.setText(data.appointmentsTime.getMonth() + "/" + data.appointmentsTime.getDay()
				+ "/" + data.appointmentsTime.getYear());
		refill.setText(data.refillTime.getMonth()+ "/" + data.refillTime.getDay() + "/"
				+ data.refillTime.getYear());
		mediStreaks = (TextView) findViewById(R.id.mediStreaks);
		mediStreaks.setText("Medi Streaks: " + data.takenCount);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}

	public void onEditInformationClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "HomeScreen");
		userLog.put("To", "UserInfoActivity");
		userLog.saveInBackground();

		Intent i = new Intent(this, UserInfoActivity.class);
		startActivityForResult(i, UserInfoActivity_ID);
	}

	public void onTakeMedsClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "HomeScreen");
		userLog.put("To", "TakeOption");
		userLog.saveInBackground();

		Intent i = new Intent(this, TakeOption.class);
		startActivityForResult(i, TakeOptionActivity_ID);
	}

}
