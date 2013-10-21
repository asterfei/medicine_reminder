package com.example.medicinereminder;

import com.parse.ParseObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AvatarDisplay extends Activity{
	
	public static final int AvatarEdit_ID = 1;
	public static final int Tutorial_ID = 1;
	private Database data = Database.getInstance();
	private AvatarInformation avatardata = AvatarInformation.getInstance();	
	private String nickname;
	private String dreamjob;
	private String hobbies;
	private int imagenum;
	private int shoutbuck;
	TextView nicknameinput;
	TextView dreamjobinput;
	TextView hobbiesinput;
	TextView ShoutBucksinput;
	ImageView avatarinput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar_display);
		nickname = avatardata.nickName;
		dreamjob = avatardata.dreamJob;
		hobbies = avatardata.hobby;
		shoutbuck = data.buck;
		
		avatarinput = (ImageView) findViewById(R.id.imageView1);
		imagenum = avatardata.imageNum;
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
					R.drawable.captain5));
		else if (imagenum == 6)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.cat6));
		else if (imagenum == 7)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.greenman7));
		else if (imagenum == 8)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.thor8));
		else if (imagenum == 9)
			avatarinput.setImageDrawable(getResources().getDrawable(
					R.drawable.loki9));
	
		nicknameinput = (TextView) findViewById(R.id.textView3);
		nicknameinput.setText(": " + nickname);
		hobbiesinput = (TextView) findViewById(R.id.textView5);
		hobbiesinput.setText(": " + hobbies);
		dreamjobinput = (TextView) findViewById(R.id.textView7);
		dreamjobinput.setText(": " + dreamjob);
		ShoutBucksinput = (TextView) findViewById(R.id.textView9);
		ShoutBucksinput.setText(": "+ shoutbuck);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.activity_avatar_display, menu);
		return true;
	}

	public void onEditClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "AvatarDisplay");
		userLog.put("To", "AvatarEdit");
		userLog.saveInBackground();
		Intent intent = new Intent();
		intent.setClass(AvatarDisplay.this, AvatarEdit.class);
		startActivity(intent);
	}
	
	public void onContinueClick(View view) {
		ParseObject userLog = new ParseObject("UserLog");
		userLog.put("UserName", data.userName);
		userLog.put("From", "AvatarDisplay");
		userLog.put("To", " Tutorial");
		userLog.saveInBackground();
		Intent intent = new Intent();
		intent.setClass(AvatarDisplay.this, Tutorial.class);
		startActivity(intent);
	}

}
