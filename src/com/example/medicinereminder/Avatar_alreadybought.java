package com.example.medicinereminder;

import java.util.List;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.os.Bundle;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;

public class Avatar_alreadybought extends Activity {

	private AvatarInformation avatar = AvatarInformation.getInstance();
	private Database data = Database.getInstance();
	private String mypic;
	private int imagenumber;
	private char ch;
	
	private String firstName;
	private String lastName;
	private String nickName ;
	private String hobby ;
	private String dreamJob ;
	private int takenCount;
	private int shoutbuck;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_alreadybought);

		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Avatars");
		query2.whereEqualTo("userName", data.userName);
		try {
			ParseObject result = query2.find().get(0);
			mypic = result.getString("mystore");
			imagenumber = result.getInt("imageNum");
			nickName= result.getString("nickName");
			dreamJob= result.getString("dreamJob");
			hobby= result.getString("hobby");
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}
		
		ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Users");
		query3.whereEqualTo("username", data.userName);
		try {
			ParseObject result = query3.find().get(0);
			takenCount= result.getInt("takenCount");
			shoutbuck= result.getInt("buck");
			firstName = result.getString("firstName");
			lastName = result.getString("lastName");
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}
		
		data.buck = shoutbuck;
		data.firstName = firstName;
		data.lastName = lastName;
		data.takenCount = takenCount;
		avatar.nickName = nickName;
		avatar.dreamJob = dreamJob;
		avatar.hobby = hobby;
		
		setavatar();
		setcolor();
		setcuttent();
		
	}

	public void setavatar() {

		TextView[] text = new TextView[27];

		text[0] = (TextView) findViewById(R.id.textView01);
		text[1] = (TextView) findViewById(R.id.textView02);
		text[2] = (TextView) findViewById(R.id.textView03);
		text[3] = (TextView) findViewById(R.id.textView04);
		text[4] = (TextView) findViewById(R.id.textView05);
		text[5] = (TextView) findViewById(R.id.textView06);
		text[6] = (TextView) findViewById(R.id.textView07);
		text[7] = (TextView) findViewById(R.id.textView08);
		text[8] = (TextView) findViewById(R.id.textView09);
		text[9] = (TextView) findViewById(R.id.textView10);
		text[10] = (TextView) findViewById(R.id.textView11);
		text[11] = (TextView) findViewById(R.id.textView12);
		text[12] = (TextView) findViewById(R.id.textView13);
		text[13] = (TextView) findViewById(R.id.textView14);
		text[14] = (TextView) findViewById(R.id.textView15);
		text[15] = (TextView) findViewById(R.id.textView16);
		text[16] = (TextView) findViewById(R.id.textView17);
		text[17] = (TextView) findViewById(R.id.textView18);
		text[18] = (TextView) findViewById(R.id.textView19);
		text[19] = (TextView) findViewById(R.id.textView20);
		text[20] = (TextView) findViewById(R.id.textView21);
		text[21] = (TextView) findViewById(R.id.textView22);
		text[22] = (TextView) findViewById(R.id.textView23);
		text[23] = (TextView) findViewById(R.id.textView24);
		text[24] = (TextView) findViewById(R.id.textView25);
		text[25] = (TextView) findViewById(R.id.textView26);
		text[26] = (TextView) findViewById(R.id.textView27);

		/*
		 * for (int i = 0; i < 27; i++) { ch = mypic.charAt(i); if (ch == '1')
		 * {text[i].setText("      SET");
		 * text[i].getPaint().setFakeBoldText(true);
		 * text[i].setTextColor(0xff74c226); text[i].setTextSize(18); } }
		 */
	}

	public void setcuttent() {
		ImageButton C = (ImageButton) findViewById(R.id.ImageButton1);
		C.setClickable(false);
		C.setBackgroundColor(0xffffffff);
		choosepicture(C, imagenumber);
	}

	public void choosepicture(ImageButton Button, int number) {
		if (number == 1)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.spider1));
		else if (number == 2)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.iron2));
		else if (number == 3)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.superman3));
		else if (number == 4)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.batman4));
		else if (number == 5)
			Button.setImageDrawable(getResources().getDrawable(R.drawable.cat6));
		else if (number == 6)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.captain5));
		else if (number == 7)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.greenman7));
		else if (number == 8)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.thor8));
		else if (number == 9)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.loki9));
		else if (number == 10)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_1));
		else if (number == 11)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_2));
		else if (number == 12)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_3));
		else if (number == 13)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_4));
		else if (number == 14)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_5));
		else if (number == 15)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_6));
		else if (number == 16)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_7));
		else if (number == 17)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_8));
		else if (number == 18)
			Button.setImageDrawable(getResources().getDrawable(
					R.drawable.worldcup_9));
		else if (number == 19)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_1));
		else if (number == 20)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_2));
		else if (number == 21)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_3));
		else if (number == 22)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_4));
		else if (number == 23)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_5));
		else if (number == 24)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_6));
		else if (number == 25)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_7));
		else if (number == 26)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_8));
		else if (number == 27)
			Button.setImageDrawable(getResources()
					.getDrawable(R.drawable.ali_9));

	}

	@SuppressLint("NewApi")
	public void setcolor() {
		ImageButton[] B = new ImageButton[27];
		B[0] = (ImageButton) findViewById(R.id.ImageButton01);
		B[1] = (ImageButton) findViewById(R.id.ImageButton02);
		B[2] = (ImageButton) findViewById(R.id.ImageButton03);
		B[3] = (ImageButton) findViewById(R.id.ImageButton04);
		B[4] = (ImageButton) findViewById(R.id.ImageButton05);
		B[5] = (ImageButton) findViewById(R.id.ImageButton06);
		B[6] = (ImageButton) findViewById(R.id.ImageButton07);
		B[7] = (ImageButton) findViewById(R.id.ImageButton08);
		B[8] = (ImageButton) findViewById(R.id.ImageButton09);
		B[9] = (ImageButton) findViewById(R.id.ImageButton10);
		B[10] = (ImageButton) findViewById(R.id.ImageButton11);
		B[11] = (ImageButton) findViewById(R.id.ImageButton12);
		B[12] = (ImageButton) findViewById(R.id.ImageButton13);
		B[13] = (ImageButton) findViewById(R.id.ImageButton14);
		B[14] = (ImageButton) findViewById(R.id.ImageButton15);
		B[15] = (ImageButton) findViewById(R.id.ImageButton16);
		B[16] = (ImageButton) findViewById(R.id.ImageButton17);
		B[17] = (ImageButton) findViewById(R.id.ImageButton18);
		B[18] = (ImageButton) findViewById(R.id.ImageButton19);
		B[19] = (ImageButton) findViewById(R.id.ImageButton20);
		B[20] = (ImageButton) findViewById(R.id.ImageButton21);
		B[21] = (ImageButton) findViewById(R.id.ImageButton22);
		B[22] = (ImageButton) findViewById(R.id.ImageButton23);
		B[23] = (ImageButton) findViewById(R.id.ImageButton24);
		B[24] = (ImageButton) findViewById(R.id.ImageButton25);
		B[25] = (ImageButton) findViewById(R.id.ImageButton26);
		B[26] = (ImageButton) findViewById(R.id.ImageButton27);

		for (int i = 0; i < 27; i++) {
			B[i].setBackgroundColor(0xffffffff);
		}

		char flag;
		char m;
		int currentnum = 0;
		int temp = 0;
		int boughtnumber = 0;
		for (int i = 0; i < 27; i++) {
			flag = mypic.charAt(i);
			if (flag == '1')
				boughtnumber++;
		}
		for (int j = 0; j < boughtnumber; j++) {
			for (int k = currentnum; k < 27; k++) {
				m = mypic.charAt(k);
				if (m == '1') {
					temp = k;
					k = 28;
				}
			}
			currentnum = temp + 1;
			choosepicture(B[j], temp + 1);
		}
		for (int t = boughtnumber; t < 27; t++) {
			B[t].setClickable(false);
			;
		}
	}

	public void AlertDialog(final int number) {
		new AlertDialog.Builder(this)
				.setTitle("Reminder")
				.setMessage("Are you sure to choose this picture as avater?")
				.setNegativeButton("continue",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int k) {
								imagenumber = number;
							}
						}).setPositiveButton("cancel", null).show();
	}

	public void decision(int number) {
		char flag;
		int boughtnumber = 0;
		int temp = 0;
		int index = 0;
		for (int i = 0; i < 27; i++) {
			flag = mypic.charAt(i);
			if (flag == '1')
				boughtnumber++;
		}
		if (boughtnumber >= number)
			for (int j = 0; j < number; j++) {
				temp = mypic.indexOf('1', index);
				index = temp + 1;
			}
		AlertDialog(index);
	}

	public void onAvatarImage1ButtonClick(View view) {
		decision(1);
	}

	public void onAvatarImage2ButtonClick(View view) {
		decision(2);
	}

	public void onAvatarImage3ButtonClick(View view) {
		decision(3);
	}

	public void onAvatarImage4ButtonClick(View view) {
		decision(4);
	}

	public void onAvatarImage5ButtonClick(View view) {
		decision(5);
	}

	public void onAvatarImage6ButtonClick(View view) {
		decision(6);
	}

	public void onAvatarImage7ButtonClick(View view) {
		decision(7);
	}

	public void onAvatarImage8ButtonClick(View view) {
		decision(8);
	}

	public void onAvatarImage9ButtonClick(View view) {
		decision(9);
	}

	public void onAvatarImage10ButtonClick(View view) {
		decision(10);
	}

	public void onAvatarImage11ButtonClick(View view) {
		decision(11);
	}

	public void onAvatarImage12ButtonClick(View view) {
		decision(12);
	}

	public void onAvatarImage13ButtonClick(View view) {
		decision(13);
	}

	public void onAvatarImage14ButtonClick(View view) {
		decision(14);
	}

	public void onAvatarImage15ButtonClick(View view) {
		decision(15);
	}

	public void onAvatarImage16ButtonClick(View view) {
		decision(16);
	}

	public void onAvatarImage17ButtonClick(View view) {
		decision(17);
	}

	public void onAvatarImage18ButtonClick(View view) {
		decision(18);
	}

	public void onAvatarImage19ButtonClick(View view) {
		decision(19);
	}

	public void onAvatarImage20ButtonClick(View view) {
		decision(20);
	}

	public void onAvatarImage21ButtonClick(View view) {
		decision(21);
	}

	public void onAvatarImage22ButtonClick(View view) {
		decision(22);
	}

	public void onAvatarImage23ButtonClick(View view) {
		decision(23);
	}

	public void onAvatarImage24ButtonClick(View view) {
		decision(24);
	}

	public void onAvatarImage25ButtonClick(View view) {
		decision(25);
	}

	public void onAvatarImage26ButtonClick(View view) {
		decision(26);
	}

	public void onAvatarImage27ButtonClick(View view) {
		decision(27);

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
			query.getInBackground(avatar.objectId,
					new GetCallback<ParseObject>() {
						public void done(ParseObject object, ParseException e) {
							if (e == null) {
								object.put("imageNum", avatar.imageNum);
								object.saveInBackground();
							} else {

							}
						}
					});
			NextActivity_Submit();
		}
	}

	public void onAvatarBackButtonClick(View view) {
		NextActivity();
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "Avatar_alreadybought");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(Avatar_alreadybought.this, MainActivity.class);
		startActivity(intent);
	}	
	
	public void NextActivity_Submit() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "Avatar_alreadybought");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(Avatar_alreadybought.this, AvatarInformationDisplayContinue.class);
		startActivity(intent);
	}	

}