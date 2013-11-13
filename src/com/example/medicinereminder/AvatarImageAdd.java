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
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;

public class AvatarImageAdd extends Activity {

	private int imagenumber = 0;
	final static int totalImages = 27;
	private int[] prices = new int[totalImages];
	private int takenCount = 0;
	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation
			.getInstance();
	int index;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_image_add);
		
		takenCount= data.takenCount;
		for (index = 0; index < totalImages; index++) {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("AvatarShop");
			query.whereEqualTo("imageNum", index+1);
			try {
				ParseObject result = query.find().get(0);
				prices[index]= result.getInt("price"); 
			} catch (ParseException e) {
				Log.i("Info", "Error: " + e.getMessage());
			}
		}
		
		TextView block1 = (TextView) findViewById(R.id.textViewlevel0);
		block1.setText("unlocked");
		block1.setTextColor(0xff00ff00);
		
		TextView block2 = (TextView) findViewById(R.id.textViewlevel1);
		block2.setText("locked");
		block2.setTextColor(0xffff0000);
		
		TextView block3 = (TextView) findViewById(R.id.textViewlevel2);
		block3.setText("locked");
		block3.setTextColor(0xffff0000);		
		setPrice();		
	}

public void setPrice() {
		
		TextView[] price = new TextView[27];
		price[0]=(TextView) findViewById(R.id.textView01);
		price[1]=(TextView) findViewById(R.id.textView02);
		price[2]=(TextView) findViewById(R.id.textView03);
		price[3]=(TextView) findViewById(R.id.textView04);
		price[4]=(TextView) findViewById(R.id.textView05);
		price[5]=(TextView) findViewById(R.id.textView06);
		price[6]=(TextView) findViewById(R.id.textView07);
		price[7]=(TextView) findViewById(R.id.textView08);
		price[8]=(TextView) findViewById(R.id.textView09);
		price[9]=(TextView) findViewById(R.id.textView10);
		price[10]=(TextView) findViewById(R.id.textView11);
		price[11]=(TextView) findViewById(R.id.textView12);
		price[12]=(TextView) findViewById(R.id.textView13);
		price[13]=(TextView) findViewById(R.id.textView14);
		price[14]=(TextView) findViewById(R.id.textView15);
		price[15]=(TextView) findViewById(R.id.textView16);
		price[16]=(TextView) findViewById(R.id.textView17);
		price[17]=(TextView) findViewById(R.id.textView18);
		price[18]=(TextView) findViewById(R.id.textView19);
		price[19]=(TextView) findViewById(R.id.textView20);
		price[20]=(TextView) findViewById(R.id.textView21);
		price[21]=(TextView) findViewById(R.id.textView22);
		price[22]=(TextView) findViewById(R.id.textView23);
		price[23]=(TextView) findViewById(R.id.textView24);
		price[24]=(TextView) findViewById(R.id.textView25);
		price[25]=(TextView) findViewById(R.id.textView26);
		price[26]=(TextView) findViewById(R.id.textView27);
		
		for (int i = 0; i<27; i++){
			price[i].setText("Price:"+ prices[i] + "SBs");
			}					
	}
	
	public void AlertDialog1() {
		new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage(
						"Sorry. You are now in Level 0. \n"
								+ "Reach level 1 to unblock this avatar")
				.setNeutralButton("close", null).show();
	}

	public void AlertDialog3() {
		new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage(
						"Sorry. You are now in Level 0. \n"
								+ "Reach level 2 to unblock this avatar")
				.setNeutralButton("close", null).show();
	}

	public void AlertDialog4() {
		new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage(
						"Sorry. Your Shoutbuck is " + data.buck + "\n"
								+ "Get more shoutbucks to buy this avatar")
				.setNeutralButton("close", null).show();
	}

	public void AlertDialog5(final int number) {
		new AlertDialog.Builder(this)
				.setTitle("Reminder")
				.setMessage("Are you sure to choose this avater?")
				.setNegativeButton("continue",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int k) {
								imagenumber = number;
							}
						}).setPositiveButton("cancel", null).show();
	}

	public void onAvatarImage1ButtonClick(View view) {
		if (prices[0] > 0)
			AlertDialog4();
		else
			AlertDialog5(1);
	}

	public void onAvatarImage2ButtonClick(View view) {
		if (prices[1] > 0)
			AlertDialog4();
		else
			AlertDialog5(2);
	}

	public void onAvatarImage3ButtonClick(View view) {
		if (prices[2] > 0)
			AlertDialog4();
		else
			AlertDialog5(3);
	}

	public void onAvatarImage4ButtonClick(View view) {
		if (prices[3] > 0)
			AlertDialog4();
		else
			AlertDialog5(4);
	}

	public void onAvatarImage5ButtonClick(View view) {
		if (prices[4] > 0)
			AlertDialog4();
		else
			AlertDialog5(5);
	}

	public void onAvatarImage6ButtonClick(View view) {
		if (prices[5] > 0)
			AlertDialog4();
		else
			AlertDialog5(6);
	}

	public void onAvatarImage7ButtonClick(View view) {
		if (prices[6] > 0)
			AlertDialog4();
		else
			AlertDialog5(7);
	}

	public void onAvatarImage8ButtonClick(View view) {
		if (prices[7] > 0)
			AlertDialog4();
		else
			AlertDialog5(8);
	}

	public void onAvatarImage9ButtonClick(View view) {
		if (prices[8] > 0)
			AlertDialog4();
		else
			AlertDialog5(9);
	}

	public void onAvatarImage10ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage11ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage12ButtonClick(View view) {
		AlertDialog1();

	}

	public void onAvatarImage13ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage14ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage15ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage16ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage17ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage18ButtonClick(View view) {
		AlertDialog1();
	}

	public void onAvatarImage19ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage20ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage21ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage22ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage23ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage24ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage25ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage26ButtonClick(View view) {
		AlertDialog3();
	}

	public void onAvatarImage27ButtonClick(View view) {
		AlertDialog3();
	}

	
	public void onAvatarContinueButtonClick(View view) {
		if (imagenumber == 0) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please select an avatar")
					.setNeutralButton("close", null).show();
		} else {
			if (avatar.objectId.trim().equals("")) {
				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Avatars");
				try {
					List<ParseObject> results3 = query3.find();
					for (ParseObject ava : results3) {
						String tempUser2 = ava.getString("userName");
						if (tempUser2.equals(data.userName)) {
							avatar.objectId = ava.getObjectId();
						}
					}
				} catch (ParseException e) {
					Log.i("Info", "Error: " + e.getMessage());
				}
			}
			avatar.imageNum = imagenumber;
			avatar.userName = data.userName;
			
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Avatars");
			query.getInBackground(avatar.objectId, new GetCallback<ParseObject>() {
				public void done(ParseObject object, ParseException e) {
					if (e == null) {
						object.put("imageNum", avatar.imageNum);
						object.saveInBackground();
					} else {

					}
				}
			});
			ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Users");
			query2.getInBackground(data.objectId, new GetCallback<ParseObject>() {
				public void done(ParseObject object, ParseException e) {
					if (e == null) {
						object.put("buck", data.buck);
						object.saveInBackground();
					} else {

					}
				}
			});
		}
		NextActivity();
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