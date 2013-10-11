package com.example.medicinereminder;

import java.util.Calendar;

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
		welcome = (TextView)findViewById(R.id.textView3);
		welcome.setText("Welcome back, " + firstname + " " + lastname);
		avatar = (ImageView)findViewById(R.id.imageView1);
		avatarnumber = data.avatarnumber;
		if (avatarnumber == 1)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar1));
		else if (avatarnumber == 2)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
		else if (avatarnumber == 3)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar3));
		else if (avatarnumber == 4)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar4));
		else if (avatarnumber == 5)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar5));
		else if (avatarnumber == 6)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar6));
		else if (avatarnumber == 7)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar7));
		else if (avatarnumber == 8)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar8));
		else if (avatarnumber == 9)
			avatar.setImageDrawable(getResources().getDrawable(R.drawable.avatar9));
		
		appointment = (TextView)findViewById(R.id.textView6);
		refill = (TextView)findViewById(R.id.textView7);
		appointment.setText(data.appointmentsMonth + "/" + data.appointmentsDay + "/" + data.appointmentsYear);
		refill.setText(data.refillMonth + "/" + data.refillDay + "/" + data.refillYear);
		mediStreaks = (TextView)findViewById(R.id.mediStreaks);
		mediStreaks.setText("Medi Streaks: " + data.takenCount);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}
	
	public void onEditInformationClick(View view){
		Intent i = new Intent(this, UserInfoActivity.class);
		startActivityForResult(i, UserInfoActivity_ID);
	}
	
	
	public void onTakeMedsClick(View view){
		Intent i = new Intent(this, TakeOption.class);
		startActivityForResult(i, TakeOptionActivity_ID);
	}

}
