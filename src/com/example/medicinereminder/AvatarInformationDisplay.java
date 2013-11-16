package com.example.medicinereminder;

import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class AvatarInformationDisplay extends Activity{
	
	public static final int AvatarEdit_ID = 1;
	public static final int Tutorial_ID = 1;
	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();	
	private String nickname;
	private String username;
	private String firstname;
	private String lastname;
	private String dreamjob;
	private String hobbies;
	private int imagenum;
	private int shoutbuck;
	private int takenCount ;
	
	TextView Usernameinput;
	TextView Firstnameinput;
	TextView Lastnameinput;
	TextView nicknameinput;
	TextView dreamjobinput;
	TextView hobbiesinput;
	TextView ShoutBucksinput;
	TextView Levelinput;
	ImageView avatarinput;
	ImageView level0;
	ImageView level1;
	ImageView level2;
	Button continuebutton;
	
    
	@SuppressLint({ "CutPasteId", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_information_display);		
		continuebutton = (Button)findViewById(R.id.returnbutton);
		continuebutton.setText("Return");
		
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Avatars");
		query2.whereEqualTo("userName", data.userName);
		try {
			ParseObject result = query2.find().get(0);
			nickname= result.getString("nickName");
			dreamjob= result.getString("dreamJob");
			hobbies= result.getString("hobby");
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
			username = result2.getString("username");
			firstname = result2.getString("firstName");
			lastname = result2.getString("lastName");
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}
		
		
		avatarinput = (ImageView) findViewById(R.id.personal_image);
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
		
		
		Usernameinput = (TextView) findViewById(R.id.textView11);
		Usernameinput.setText(username);
		
		Firstnameinput = (TextView) findViewById(R.id.textView22);
		Firstnameinput.setText(firstname);
		
		Lastnameinput = (TextView) findViewById(R.id.textView33);
		Lastnameinput.setText(lastname);
		
		nicknameinput = (TextView) findViewById(R.id.textView44);
		nicknameinput.setText(nickname);
		
		hobbiesinput = (TextView) findViewById(R.id.textView55);
		hobbiesinput.setText(hobbies);
		
		dreamjobinput = (TextView) findViewById(R.id.textView66);
		dreamjobinput.setText(dreamjob);
		
		ShoutBucksinput = (TextView) findViewById(R.id.textView88);
		ShoutBucksinput.setText(Integer.toString(shoutbuck));
		
		Levelinput = (TextView) findViewById(R.id.textView77);
		
		level0 = (ImageView) findViewById(R.id.level0);
		level1 = (ImageView) findViewById(R.id.level1);
		level2 = (ImageView) findViewById(R.id.level2);
		
		if (takenCount >= 20)
			Levelinput.setText("2");
		else if (takenCount >= 10) {
			Levelinput.setText("1");
			level2.setImageAlpha(0);
		} else {
			Levelinput.setText("0");
			level2.setImageAlpha(0);
			level1.setImageAlpha(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
   	
	public void onRnturnClick(View view) {
		NextActivity();
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarInformationDisplay");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationDisplay.this, MainActivity.class);
		startActivity(intent);
	}
}
