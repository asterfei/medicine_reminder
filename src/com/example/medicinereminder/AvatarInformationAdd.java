package com.example.medicinereminder;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class AvatarInformationAdd extends Activity {

	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_information_add);
	}

	public void AlertDialog6() {
		new AlertDialog.Builder(this)
				.setTitle("Congratulations")
				.setMessage("You Have Finished Your Registration")
				.setNegativeButton("continue",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int k) {
								NextActivity();
							}
						}).show();
	}

	
	public void onFinishClick(View view) {
		EditText nickNameEdit = (EditText) findViewById(R.id.nameText);
		EditText dreamJobEdit = (EditText) findViewById(R.id.jobtext);
		EditText hobbyEdit = (EditText) findViewById(R.id.hobbytext);

		avatar.hobby = hobbyEdit.getText().toString().trim();
		avatar.nickName = nickNameEdit.getText().toString().trim();
		avatar.dreamJob = dreamJobEdit.getText().toString().trim();

		setAvatar();
		AlertDialog6();
		//NextActivity();

	}

	public void setAvatar() {
		Log.i("objectId", avatar.objectId);
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
				if (e == null) {
					object.put("imageNum", avatar.imageNum);
					object.put("nickName", avatar.nickName);
					object.put("hobby", avatar.hobby);
					object.put("dreamJob", avatar.dreamJob);
					object.saveInBackground();
				} else {
					Log.i("Error", e.getMessage());
				}
			}
		});
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "AvatarInformationAdd");
		userLog.put("to", "AvatarInformationDisplayRegistration");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationAdd.this, AvatarInformationDisplayRegistration.class);
		//intent.setClass(AvatarInformationAdd.this, MainActivity.class);
		startActivity(intent);
	}

}
