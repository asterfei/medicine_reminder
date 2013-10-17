package com.example.medicinereminder;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Avatar extends Activity {
	public static final int TakeOption_ID = 1;
	private int imagenumber = 0;
	private Database data = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_avatar, menu);
		return true;
	}

	public void onAvatarImage1ButtonClick(View view) {
		imagenumber = 1;
	}

	public void onAvatarImage2ButtonClick(View view) {
		imagenumber = 2;
	}

	public void onAvatarImage3ButtonClick(View view) {
		imagenumber = 3;
	}

	public void onAvatarImage4ButtonClick(View view) {
		imagenumber = 4;
	}

	public void onAvatarImage5ButtonClick(View view) {
		imagenumber = 5;
	}

	public void onAvatarImage6ButtonClick(View view) {
		imagenumber = 6;
	}

	public void onAvatarImage7ButtonClick(View view) {
		imagenumber = 7;
	}

	public void onAvatarImage8ButtonClick(View view) {
		imagenumber = 8;
	}

	public void onAvatarImage9ButtonClick(View view) {
		imagenumber = 9;
	}

	public void onAvatarContinueButtonClick(View view) {
		if (imagenumber == 0) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please select an avatar")
					.setNeutralButton("close", null).show();
		} else {
			data.avatarnumber = imagenumber;

			ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
			query.getInBackground(data.objectId,
					new GetCallback<ParseObject>() {
						public void done(ParseObject object, ParseException e) {
							if (e == null) {
								object.put("avatar", imagenumber);
								object.saveInBackground();
							} else {

							}
						}
					});

			ParseObject userLog = new ParseObject("UserLog");
			userLog.put("UserName", data.userName);
			userLog.put("From", "Avatar");
			userLog.put("To", "Tutorial");
			userLog.saveInBackground();

			Intent i = new Intent(this, TakeOption.class);
			startActivityForResult(i, TakeOption_ID);
		}

	}

}
