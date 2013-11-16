package com.example.medicinereminder;

import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserEdit extends Activity {

	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();

	private String firstName;
	private String lastName;
	private String dateOfDiagnosis = data.dateOfDiagnosis;
	private String phone = data.phone;
	private String providerPhone = data.providerPhone;
	private String nickName ;
	private String hobby ;
	private String dreamJob ;
	private int takenCount;
	private int shoutbuck;
	private int imagenum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_edit);
		
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Avatars");
		query2.whereEqualTo("userName", data.userName);
		try {
			ParseObject result = query2.find().get(0);
			nickName= result.getString("nickName");
			dreamJob= result.getString("dreamJob");
			hobby= result.getString("hobby");
			imagenum= result.getInt("imageNum");
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}
		
		ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Users");
		query3.whereEqualTo("username", data.userName);
		try {
			ParseObject result2 = query3.find().get(0);	
			shoutbuck= result2.getInt("buck");
			takenCount= result2.getInt("takenCount");			
			firstName = result2.getString("firstName");
			lastName = result2.getString("lastName");
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}
		
		data.buck = shoutbuck;
		data.takenCount = takenCount;
		avatar.nickName = nickName;
		avatar.dreamJob = dreamJob;
		avatar.hobby = hobby;
		avatar.imageNum = imagenum;
		

		TextView textView1 = (TextView) findViewById(R.id.firstname);
		textView1.setText(firstName);

		TextView textView2 = (TextView) findViewById(R.id.lastname);
		textView2.setText(lastName);

		TextView textView3 = (TextView) findViewById(R.id.date);
		textView3.setText(data.dateOfDiagnosis);

		TextView textView4 = (TextView) findViewById(R.id.phone);
		textView4.setText(data.phone);

		TextView textView5 = (TextView) findViewById(R.id.docphone);
		textView5.setText(data.providerPhone);
	}

	public void onSubmitClick(View view) {

		EditText e3 = (EditText) findViewById(R.id.firstname);
		firstName = e3.getText().toString().trim();
		Log.i("Info", firstName);

		EditText e4 = (EditText) findViewById(R.id.lastname);
		lastName = e4.getText().toString().trim();
		Log.i("Info", lastName);

		EditText e5 = (EditText) findViewById(R.id.date);
		dateOfDiagnosis = e5.getText().toString().trim();
		Log.i("Info", dateOfDiagnosis);

		EditText e7 = (EditText) findViewById(R.id.phone);
		phone = e7.getText().toString().trim();
		Log.i("Info", phone);

		EditText e8 = (EditText) findViewById(R.id.docphone);
		providerPhone = e8.getText().toString().trim();
		Log.i("Info", providerPhone);

		if (firstName.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter first name")
					.setNeutralButton("close", null).show();
		} else if (lastName.equals("")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter last name")
					.setNeutralButton("close", null).show();
		} else if (dateOfDiagnosis.equals("")
				|| !dateOfDiagnosis.matches("[\\d]{2}/[\\d]{2}/[\\d]{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter date of diagnosis")
					.setNeutralButton("close", null).show();
		} else if (phone.equals("") || !phone.matches("(\\d{3}-?){1,2}\\d{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a valid phone number")
					.setNeutralButton("close", null).show();
		} else if (providerPhone.equals("")
				|| !providerPhone.matches("(\\d{3}-?){1,2}\\d{4}")) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please enter a valid provider phone number")
					.setNeutralButton("close", null).show();
		} else {
			setUserData();
			editUser();
			NextActivity_Next();
		}

	}

	public void NextActivity_Back() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "UserEdit");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(UserEdit.this, MainActivity.class);
		startActivity(intent);
	}

	public void NextActivity_Next() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "UserEdit");
		userLog.put("to", "AvatarInformationDisplay");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(UserEdit.this, AvatarInformationDisplayContinue.class);
		startActivity(intent);
	}
	
	public void onReturnClick(View view) {
		NextActivity_Back();
	}

	public void setUserData() {

		data.firstName = firstName;
		data.lastName = lastName;
		data.dateOfDiagnosis = dateOfDiagnosis;
		data.phone = phone;
		data.providerPhone = providerPhone;

		if (data.objectId.trim().equals("")) {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
			try {
				List<ParseObject> results = query.find();
				for (ParseObject user : results) {
					String tempUser = user.getString("username");
					if (tempUser.equals(data.userName)) {
						data.objectId = user.getObjectId();
					}
				}
			} catch (ParseException e) {
				Log.i("Error", e.getMessage());
			}
		}
	}

	public void editUser() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("firstName", data.firstName);
					object.put("lastName", data.lastName);
					object.put("dateOfDiagnosis", dateOfDiagnosis);
					object.put("phone", phone);
					object.put("providerPhone", providerPhone);
					object.saveInBackground();
				} else {
					Log.i("Error", e.getMessage());
				}
			}
		});
	}
}
