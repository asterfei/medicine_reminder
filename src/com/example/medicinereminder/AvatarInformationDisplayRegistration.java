package com.example.medicinereminder;


import com.parse.ParseObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class AvatarInformationDisplayRegistration extends Activity{
	
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
	private int takenCount;
	
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
	ImageButton personalimage;
	
    
	@SuppressLint({ "CutPasteId", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_information_display);
		
		continuebutton = (Button)findViewById(R.id.returnbutton);
		continuebutton.setText("Continue");
		
		nickname = avatar.nickName;
		username = data.userName;
		firstname = data.firstName;
		lastname = data.lastName;
		dreamjob = avatar.dreamJob;
		hobbies = avatar.hobby;
		imagenum = avatar.imageNum;
		shoutbuck = data.buck;
		takenCount = data.takenCount;
		
		ImageButton personalimage = (ImageButton) findViewById(R.id.personalimage);
		choosepicture(personalimage, imagenum);
		
		
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
		
		if (takenCount >= 20){
			Levelinput.setText("2");
			level0.setImageDrawable(getResources().getDrawable(R.drawable.level));
			level1.setImageDrawable(getResources().getDrawable(R.drawable.level));
			level2.setImageDrawable(getResources().getDrawable(R.drawable.level));
		}
		else if (takenCount >= 10) {
			Levelinput.setText("1");
			level0.setImageDrawable(getResources().getDrawable(R.drawable.level));
			level1.setImageDrawable(getResources().getDrawable(R.drawable.level));
		} else {
			Levelinput.setText("0");
			level0.setImageDrawable(getResources().getDrawable(R.drawable.level));
		}
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
	
	
	public void onPersonalImageClick(View view){
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarInformationDisplayRegistration");
		userLog.put("to", "Avatar_alreadybought");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationDisplayRegistration.this, Avatar_alreadybought.class);
		startActivity(intent);
	}
	
	public void ongotomyavatarClick(View view){
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarInformationDisplayRegistration");
		userLog.put("to", "Avatar_alreadybought");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationDisplayRegistration.this, Avatar_alreadybought.class);
		startActivity(intent);
	}
	
	public void ongoshoppingClick(View view){
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarInformationDisplayRegistration");
		userLog.put("to", "AvatarImageEdit");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationDisplayRegistration.this, AvatarImageEdit.class);
		startActivity(intent);
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
		userLog.put("from", "AvatarInformationDisplayContinue");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarInformationDisplayRegistration.this, MainActivity.class);
		startActivity(intent);
	}
}
