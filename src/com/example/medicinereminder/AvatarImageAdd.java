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
	private Database data = Database.getInstance();
	private AvatarInformation avatar = AvatarInformation.getInstance();
	int index;
	StringBuffer mypicstore = new StringBuffer();
	private int[] avatarprice = new int [totalImages];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_image_add);
		for (int index = 0; index < totalImages; index ++ )
			avatarprice[index] = data.avatarprices[index];
		
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
		setcolor();
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
			price[i].setText("Price:"+ avatarprice[i] + "SBs");
			}					
	}

    @SuppressLint("NewApi")
	public void setcolor() {
    	ImageButton[] B = new ImageButton[27];
    	B[0]=(ImageButton)findViewById(R.id.ImageButton01);
    	B[1]=(ImageButton)findViewById(R.id.ImageButton02);
    	B[2]=(ImageButton)findViewById(R.id.ImageButton03);
    	B[3]=(ImageButton)findViewById(R.id.ImageButton04);
    	B[4]=(ImageButton)findViewById(R.id.ImageButton05);
    	B[5]=(ImageButton)findViewById(R.id.ImageButton06);
    	B[6]=(ImageButton)findViewById(R.id.ImageButton07);
    	B[7]=(ImageButton)findViewById(R.id.ImageButton08);
    	B[8]=(ImageButton)findViewById(R.id.ImageButton09);
    	B[9]=(ImageButton)findViewById(R.id.ImageButton10);
    	B[10]=(ImageButton)findViewById(R.id.ImageButton11);
    	B[11]=(ImageButton)findViewById(R.id.ImageButton12);
    	B[12]=(ImageButton)findViewById(R.id.ImageButton13);
    	B[13]=(ImageButton)findViewById(R.id.ImageButton14);
    	B[14]=(ImageButton)findViewById(R.id.ImageButton15);
    	B[15]=(ImageButton)findViewById(R.id.ImageButton16);
    	B[16]=(ImageButton)findViewById(R.id.ImageButton17);
    	B[17]=(ImageButton)findViewById(R.id.ImageButton18);
    	B[18]=(ImageButton)findViewById(R.id.ImageButton19);
    	B[19]=(ImageButton)findViewById(R.id.ImageButton20);
    	B[20]=(ImageButton)findViewById(R.id.ImageButton21);
    	B[21]=(ImageButton)findViewById(R.id.ImageButton22);
    	B[22]=(ImageButton)findViewById(R.id.ImageButton23);
    	B[23]=(ImageButton)findViewById(R.id.ImageButton24);
    	B[24]=(ImageButton)findViewById(R.id.ImageButton25);
    	B[25]=(ImageButton)findViewById(R.id.ImageButton26);
    	B[26]=(ImageButton)findViewById(R.id.ImageButton27);
    	
    	for (int i = 9; i< 27; i++)
    		B[i].setImageAlpha(100);
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
								JumptoNextPage();
							}
						}).setPositiveButton("cancel", null).show();
	}

	public void AlertDialog_level0(final int number){
		if (avatarprice[number] > 0)
			AlertDialog4();
		else
			AlertDialog5(number+1);
	}
	
	public void onAvatarImage1ButtonClick(View view) {
		AlertDialog_level0(0);
	}

	public void onAvatarImage2ButtonClick(View view) {
		AlertDialog_level0(1);
	}

	public void onAvatarImage3ButtonClick(View view) {
		AlertDialog_level0(2);
	}

	public void onAvatarImage4ButtonClick(View view) {
		AlertDialog_level0(3);
	}

	public void onAvatarImage5ButtonClick(View view) {
		AlertDialog_level0(4);
	}

	public void onAvatarImage6ButtonClick(View view) {
		AlertDialog_level0(5);
	}

	public void onAvatarImage7ButtonClick(View view) {
		AlertDialog_level0(6);
	}

	public void onAvatarImage8ButtonClick(View view) {
		AlertDialog_level0(7);
	}

	public void onAvatarImage9ButtonClick(View view) {
		AlertDialog_level0(8);
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

	public void JumptoNextPage(){
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
		avatar.mystore = "000000000000000000000000000";
		mypicstore.append(avatar.mystore.substring(0, imagenumber-1));
		mypicstore.append("1");
		mypicstore.append(avatar.mystore.substring(imagenumber,27));
		avatar.mystore = mypicstore.toString();
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Avatars");
		query.getInBackground(avatar.objectId, new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					object.put("imageNum", avatar.imageNum);
					object.put("mystore", avatar.mystore);
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
		NextActivity();
	}
	
	public void onAvatarContinueButtonClick(View view) {
		if (imagenumber == 0) {
			new AlertDialog.Builder(this).setTitle("Error")
					.setMessage("Please select an avatar")
					.setNeutralButton("close", null).show();
		} else 
			JumptoNextPage();
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