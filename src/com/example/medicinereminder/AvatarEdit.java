package com.example.medicinereminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class AvatarEdit extends Activity {

	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public void onFinishClick(View view) {
		EditText nickNameEdit = (EditText) findViewById(R.id.nameText);
		EditText dreamJobEdit = (EditText) findViewById(R.id.jobtext);
		EditText hobbyEdit = (EditText) findViewById(R.id.hobbytext);
		avatar.hobby = hobbyEdit.getText().toString();
		avatar.nickName = nickNameEdit.getText().toString();
		avatar.dreamJob = dreamJobEdit.getText().toString();

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Avatar");
		query.getInBackground(avatar.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("imageNum", avatar.imageNum);
					object.put("userName", avatar.userName);
					object.put("nickName", avatar.nickName);
					object.put("hobby", avatar.hobby);
					object.put("dreamJob", avatar.dreamJob);
					object.saveInBackground();
				} else {

				}
			}
		});

		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "AvatarEdit");
		userLog.put("To", "TakeOption");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarEdit.this, TakeOption.class);
		startActivity(intent);

		// Intent intent = new Intent();
		// intent.setClass(AvatarEdit.this, AvatarDisplay.class);
		// startActivity(intent);

	}

}
