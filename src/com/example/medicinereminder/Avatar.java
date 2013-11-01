package com.example.medicinereminder;

import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Avatar extends Activity {
	public static final int Tutorial_ID = 1;
	private int imagenumber = 0;
	final static int totalImages = 10;
	private int[] prices = new int[totalImages];
	private Database data = Database.getInstance();
	private int takenCount = 0;
	private AvatarInformation avatarInformation = AvatarInformation
			.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar);
		takenCount= data.takenCount;
		for (int index = 0; index < totalImages; index++) {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("AvatarShop");
			query.whereEqualTo("imageNum", index+1);
			try {
				ParseObject result = query.find().get(0);
				prices[index]= result.getInt("price"); 
			} catch (ParseException e) {
				Log.i("Info", "Error: " + e.getMessage());
			}
		}
		
		TextView block1 = (TextView) findViewById(R.id.textView21);
		if(takenCount>=0){
			Log.i("count: ",""+takenCount);
			block1.setText("unlocked");
			block1.setTextColor(0xff00ff00);
		}
		else {
			block1.setText("locked");
			block1.setTextColor(0xffff0000);
		}
		TextView block2 = (TextView) findViewById(R.id.textView31);
		if(takenCount>=10){
			block2.setText("unlocked");
			block2.setTextColor(0xff00ff00);
		}
		else {
			block2.setText("locked");
			block2.setTextColor(0xffff0000);
			lockLevelOne();
		}
		
		setPrice();
		
		
		
	}

	public void lockLevelOne() {
		ImageButton image10 = (ImageButton) findViewById(R.id.ImageButton010);
		image10.setEnabled(false);
	}

	public void setPrice() {
		TextView price1 = (TextView) findViewById(R.id.textView01);
		price1.setText("Price: "+prices[0] + "SBs");
		
		TextView price2 = (TextView) findViewById(R.id.textView02);
		price2.setText("Price: "+prices[1] + "SBs");
		
		TextView price3 = (TextView) findViewById(R.id.textView03);
		price3.setText("Price: "+prices[2] + "SBs");
		
		TextView price4 = (TextView) findViewById(R.id.textView04);
		price4.setText("Price: "+prices[3] + "SBs");
		
		TextView price5 = (TextView) findViewById(R.id.textView05);
		price5.setText("Price: "+prices[4] + "SBs");
		
		TextView price6 = (TextView) findViewById(R.id.textView06);
		price6.setText("Price: "+prices[5] + "SBs");
		
		TextView price7 = (TextView) findViewById(R.id.textView07);
		price7.setText("Price: "+prices[6] + "SBs");
		
		TextView price8 = (TextView) findViewById(R.id.textView08);
		price8.setText("Price: "+prices[7] + "SBs");
		
		TextView price9 = (TextView) findViewById(R.id.textView09);
		price9.setText("Price: "+prices[8] + "SBs");
		
		TextView price10 = (TextView) findViewById(R.id.textView010);
		price10.setText("Price: "+prices[9] + "SBs");
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
	
	public void onAvatarImage10ButtonClick(View view) {
		imagenumber = 10;
	}

	public void onAvatarContinueButtonClick(View view) {
		if (imagenumber == 0) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please select an avatar")
					.setNeutralButton("close", null).show();
		} else {
			if (avatarInformation.objectId.trim().equals("")) {
				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Avatar");
				try {
					List<ParseObject> results3 = query3.find();
					for (ParseObject ava : results3) {
						String tempUser2 = ava.getString("userName");
						if (tempUser2.equals(data.userName)) {
							avatarInformation.objectId = ava.getObjectId();
						}
					}
				} catch (ParseException e) {
					Log.i("Info", "Error: " + e.getMessage());
				}
			}
			avatarInformation.imageNum = imagenumber;
			avatarInformation.userName = data.userName;
			Intent intent = new Intent();
			intent.setClass(Avatar.this, AvatarEdit.class);

			ParseObject userLog = new ParseObject("UserLog");
			userLog.put("UserName", data.userName);
			userLog.put("From", "Avatar");
			userLog.put("To", "AvatarEdit");
			userLog.saveInBackground();

			startActivity(intent);
		}

	}

}
