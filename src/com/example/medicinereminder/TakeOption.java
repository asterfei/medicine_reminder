package com.example.medicinereminder;

import java.io.File;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class TakeOption extends Activity {
	public static final int SnoozeActivity_ID = 1;
	public static final int HomeScreen_ID = 1;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri imageUri;
	private Database data = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_option);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_take_option, menu);
		return true;
	}

	public void onHomeButtonClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "TakeOption");
		userLog.put("To", "HomeScreen");
		userLog.saveInBackground();

		
		Intent intent = new Intent();
		intent.setClass(TakeOption.this, HomeScreen.class);
		startActivity(intent);
	}

	public void onTookButtonClick(View view) {
		data.notTakenCount = 0;
		data.takenCount += 1;
		data.buck+=1;
		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					//object.put("notTakenCount", 0);
					object.put("takenCount", data.takenCount);
					object.put("buck", data.buck);
					object.saveInBackground();
				} else {

				}
			}
		});
		
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("UserInfo");
		query2.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					int buck = data.buck + 1;
					object.put("buck", buck);
					object.saveInBackground();
				} else {

				}
			}
		});
		
		new AlertDialog.Builder(this).setTitle("Good Job :)")
				.setMessage("Keep it up!").setNeutralButton("close", null)
				.show();

	}

	public void onNotTakenButtonClick(View view) {
		data.takenCount = 0;
		data.notTakenCount += 1;
		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("takenCount", 0);
					object.put("notTakenCount", data.notTakenCount);
					object.saveInBackground();
				} else {

				}
			}
		});
		
		if (data.notTakenCount >= 3) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder.setTitle("Are you ok?");
			alertDialogBuilder
					.setMessage("You have not taken your medication more than 3 times in a row. "
							+ "Would you like to call your provider?");
			alertDialogBuilder.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent callIntent = new Intent(Intent.ACTION_CALL);
							String providerPhone = "tel:" + data.providerPhone;
							callIntent.setData(Uri.parse(providerPhone));
							startActivity(callIntent);
						}
					});
			alertDialogBuilder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(
									getApplicationContext(),
									"Ok.. :( Please call your provider as soon as possible.",
									Toast.LENGTH_LONG).show();
						}
					});
			alertDialogBuilder.show();
		} else {
			new AlertDialog.Builder(this).setTitle("Ok..:(")
					.setMessage("You will do better next time.")
					.setNeutralButton("close", null).show();
		}

	}

	public void onAlarmButtonClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "TakeOption");
		userLog.put("To", "SnoozeActivity");
		userLog.saveInBackground();

		Intent i = new Intent(this, SnoozeActivity.class);
		startActivityForResult(i, SnoozeActivity_ID);

	}

	public void onPillCamButtonClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "TakeOption");
		userLog.put("To", "TakePillPictureActivity");
		userLog.saveInBackground();
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory(),
				"test.jpg");
		imageUri = Uri.fromFile(file);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					int buck = data.buck + 2;
					object.put("buck", buck);
					object.saveInBackground();
				} else {

				}
			}
		});
		
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Picture was not taken",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Picture was not taken",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void onRefillButtonClick(View view) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					int buck = data.buck + 10;
					object.put("buck", buck);
					object.saveInBackground();
				} else {

				}
			}
		});
		new AlertDialog.Builder(this).setTitle("Good Job :)")
				.setMessage("Please set refill for next time!").setNeutralButton("close", null)
				.show();

	}
	
	public void onOfficeButtonClick(View view) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					int buck = data.buck + 10;
					object.put("buck", buck);
					object.saveInBackground();
				} else {

				}
			}
		});
		new AlertDialog.Builder(this).setTitle("Good Job :)")
				.setMessage("Please set next office hour for next time!").setNeutralButton("close", null)
				.show();

	}
	
}
