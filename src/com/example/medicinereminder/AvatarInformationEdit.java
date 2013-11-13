package com.example.medicinereminder;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class AvatarInformationEdit extends Activity {

	private AvatarInformation avatar = AvatarInformation.getInstance();
	private Database data = Database.getInstance();

	private String nickName = avatar.nickName;
	private String hobby = avatar.hobby;
	private String dreamJob = avatar.dreamJob;

	private int imagenum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_information_edit);

		TextView textView1 = (TextView) findViewById(R.id.edit_nameText);
		textView1.setText(avatar.nickName);

		TextView textView2 = (TextView) findViewById(R.id.edit_hobbytext);
		textView2.setText(avatar.hobby);

		TextView textView3 = (TextView) findViewById(R.id.edit_jobtext);
		textView3.setText(avatar.dreamJob);

		ImageView avatarinput = (ImageView) findViewById(R.id.personal_image);
		imagenum = avatar.imageNum;
		if (imagenum == 1)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.spider1));
		else if (imagenum == 2)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.iron2));
		else if (imagenum == 3)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.superman3));
		else if (imagenum == 4)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.batman4));
		else if (imagenum == 5)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.cat6));
		else if (imagenum == 6)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.captain5));
		else if (imagenum == 7)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.greenman7));
		else if (imagenum == 8)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.thor8));
		else if (imagenum == 9)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.loki9));
		else if (imagenum == 10)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_1));
		else if (imagenum == 11)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_2));
		else if (imagenum == 12)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_3));
		else if (imagenum == 13)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_4));
		else if (imagenum == 14)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_5));
		else if (imagenum == 15)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_6));
		else if (imagenum == 16)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_7));
		else if (imagenum == 17)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_8));
		else if (imagenum == 18)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_9));
		else if (imagenum == 19)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_1));
		else if (imagenum == 20)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_2));
		else if (imagenum == 21)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_3));
		else if (imagenum == 22)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_4));
		else if (imagenum == 23)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_5));
		else if (imagenum == 24)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_6));
		else if (imagenum == 25)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_7));
		else if (imagenum == 26)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_8));
		else if (imagenum == 27)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.ali_9));


	}

	public void onSubmitClick(View view) {
		EditText e1 = (EditText) findViewById(R.id.edit_nameText);
		nickName = e1.getText().toString().trim();
		Log.i("nickName", nickName);

		EditText e2 = (EditText) findViewById(R.id.edit_hobbytext);
		hobby = e2.getText().toString().trim();
		Log.i("hobby", hobby);

		EditText e3 = (EditText) findViewById(R.id.edit_jobtext);
		dreamJob = e3.getText().toString().trim();
		Log.i("dreamJob", dreamJob);

		setAvatar();

		NextActivity();

	}

	public void onReturnClick(View view) {
		NextActivity();
	}

	public void setAvatar() {
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
					object.put("nickName", nickName);
					object.put("hobby", hobby);
					object.put("dreamJob", dreamJob);
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
		userLog.put("from", "AvatarInformationEdit");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationEdit.this, MainActivity.class);
		startActivity(intent);
	}

}
