package com.example.medicinereminder;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseAnalytics;

public class MainActivity extends Activity {
	public static final int LoginActivity_ID = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Parse.initialize(this, "q14R25Covh35iR5YriM6dfBKQA2dccIgc4ymdr1T", "vABJVijqAyuZSgDq3kY0ScL4ZX3TQZS8KMg330Fk"); 
			ParseAnalytics.trackAppOpened(getIntent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_main, menu);
			return true;
	}

	public void onStartButtonClick(View view){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
	}
	
}


