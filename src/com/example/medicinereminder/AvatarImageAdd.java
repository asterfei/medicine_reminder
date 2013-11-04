package com.example.medicinereminder;

import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class AvatarImageAdd extends Activity {

	private int imagenumber = 0;
	private AvatarInformation avatar = AvatarInformation.getInstance();
	private Database data = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_image_add);
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

	public void onContinueClick(View view) {
		if (imagenumber == 0) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please select an avatar")
					.setNeutralButton("close", null).show();
		} else {
			setAvatar();
			NextActivity();
		}

	}

	public void setAvatar() {
		avatar.imageNum = imagenumber;
		avatar.userName = data.userName;
		Log.i("data.username", data.userName);
		Log.i("avatar.username", avatar.userName);
		if (avatar.objectId.trim().equals("")) {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Avatars");
			try {
				List<ParseObject> results = query.find();
				for (ParseObject ava : results) {
					if (ava.getString("userName").equals(data.userName)) {
						avatar.objectId = ava.getObjectId();
					}
				}
			} catch (ParseException e) {
				Log.i("Error", e.getMessage());
			}
		}
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarImageAdd");
		userLog.put("to", "AvatarInformationAdd");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarImageAdd.this, AvatarInformationAdd.class);
		startActivity(intent);
	}

}