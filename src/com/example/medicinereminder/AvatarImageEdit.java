package com.example.medicinereminder;

import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class AvatarImageEdit extends Activity {

	private AvatarInformation avatar = AvatarInformation.getInstance();

	private Database data = Database.getInstance();

	private int imagenumber = avatar.imageNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_image_edit);
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

	public void onSubmitClick(View view) {
		avatar.imageNum = imagenumber;
		EditAvatarImage();
		NextActivity();
	}

	public void onReturnClick(View view) {
		NextActivity();
	}

	public void EditAvatarImage() {
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

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Avatars");
		query.getInBackground(avatar.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				Log.i("objectId", avatar.objectId);
				if (e == null) {
					object.put("imageNum", avatar.imageNum);
					object.saveInBackground();
				} else {
					Log.i("Error", e.getMessage());
				}
			}
		});
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarImageEdit");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarImageEdit.this, MainActivity.class);
		startActivity(intent);
	}

}