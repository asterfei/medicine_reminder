package com.example.medicinereminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseAnalytics;

public class Welcome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		Parse.initialize(this, "q14R25Covh35iR5YriM6dfBKQA2dccIgc4ymdr1T",
				"vABJVijqAyuZSgDq3kY0ScL4ZX3TQZS8KMg330Fk");
		ParseAnalytics.trackAppOpened(getIntent());
	}

	public void onLoginClick(View v) {
		Intent intent = new Intent();
		intent.setClass(Welcome.this, Login.class);
		startActivity(intent);
	}
	
	public void onRegisterClick(View v) {
		Intent intent = new Intent();
		intent.setClass(Welcome.this, Register.class);
		startActivity(intent);
	}

}
