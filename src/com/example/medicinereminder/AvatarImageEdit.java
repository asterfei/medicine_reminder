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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AvatarImageEdit extends Activity {

	private AvatarInformation avatar = AvatarInformation.getInstance();
	private Database data = Database.getInstance();
	private int imagenumber = avatar.imageNum;
	
	final static int totalImages = 27;
	private int[] prices = new int[totalImages];
	private int takenCount = 0;
	int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_image_edit);
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
		if(takenCount>=0){
			Log.i("count: ",""+takenCount);
			block1.setText("unlocked");
			block1.setTextColor(0xff00ff00);
		}
		else {
			block1.setText("locked");
			block1.setTextColor(0xffff0000);
		}
		TextView block2 = (TextView) findViewById(R.id.textViewlevel1);
		if(takenCount>=10){
			block2.setText("unlocked");
			block2.setTextColor(0xff00ff00);
		}
		else {
			block2.setText("locked");
			block2.setTextColor(0xffff0000);
		}
		TextView block3 = (TextView) findViewById(R.id.textViewlevel2);
		if(takenCount>=20){
			block3.setText("unlocked");
			block3.setTextColor(0xff00ff00);
		}
		else {
			block3.setText("locked");
			block3.setTextColor(0xffff0000);
		}		
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

	public void AlertDialog2() {
		new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage(
						"Sorry. You are now in Level 1. \n"
								+ "Reach level 2 to unblock this avatar")
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
				.setMessage(
						"This will cost you " + prices[number - 1] + " SBs \n"
								+ "Are you sure to continue?")
				.setNegativeButton("continue",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int k) {
								data.buck = data.buck - prices[number - 1];
								imagenumber = number;
							}
						}).setPositiveButton("cancel", null).show();
	}

	public void AlertDialog6(final int number) {
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
		if (prices[0] > 0) {
			if (data.buck >= prices[0])
				AlertDialog5(1);
			else
				AlertDialog4();
		} else
			AlertDialog6(1);
	}

	public void onAvatarImage2ButtonClick(View view) {
		if (prices[1] > 0) {
			if (data.buck >= prices[1])
				AlertDialog5(2);
			else
				AlertDialog4();
		} else
			AlertDialog6(2);
	}

	public void onAvatarImage3ButtonClick(View view) {
		if (prices[2] > 0) {
			if (data.buck >= prices[2])
				AlertDialog5(3);
			else
				AlertDialog4();
		} else
			AlertDialog6(3);
	}

	public void onAvatarImage4ButtonClick(View view) {
		if (prices[3] > 0) {
			if (data.buck >= prices[3])
				AlertDialog5(4);
			else
				AlertDialog4();
		} else
			AlertDialog6(4);
	}

	public void onAvatarImage5ButtonClick(View view) {
		if (prices[4] > 0) {
			if (data.buck >= prices[4])
				AlertDialog5(5);
			else
				AlertDialog4();
		} else
			AlertDialog6(5);
	}

	public void onAvatarImage6ButtonClick(View view) {
		if (prices[5] > 0) {
			if (data.buck >= prices[5])
				AlertDialog5(6);
			else
				AlertDialog4();
		} else
			AlertDialog6(6);
	}

	public void onAvatarImage7ButtonClick(View view) {
		if (prices[6] > 0) {
			if (data.buck >= prices[6])
				AlertDialog5(7);
			else
				AlertDialog4();
		} else
			AlertDialog6(7);
	}

	public void onAvatarImage8ButtonClick(View view) {
		if (prices[7] > 0) {
			if (data.buck >= prices[7])
				AlertDialog5(8);
			else
				AlertDialog4();
		} else
			AlertDialog6(8);
	}

	public void onAvatarImage9ButtonClick(View view) {
		if (prices[8] > 0) {
			if (data.buck >= prices[8])
				AlertDialog5(9);
			else
				AlertDialog4();
		} else
			AlertDialog6(9);
	}

	public void onAvatarImage10ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[9] > 0) {
				if (data.buck >= prices[9])
					AlertDialog5(10);
				else
					AlertDialog4();
			} else
				AlertDialog6(10);
		} else
			AlertDialog1();
	}

	public void onAvatarImage11ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[10] > 0) {
				if (data.buck >= prices[10])
					AlertDialog5(11);
				else
					AlertDialog4();
			} else
				AlertDialog6(11);
		} else
			AlertDialog1();
	}

	public void onAvatarImage12ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[11] > 0) {
				if (data.buck >= prices[11])
					AlertDialog5(12);
				else
					AlertDialog4();
			} else
				AlertDialog6(12);
		} else
			AlertDialog1();

	}

	public void onAvatarImage13ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[12] > 0) {
				if (data.buck >= prices[12])
					AlertDialog5(13);
				else
					AlertDialog4();
			} else
				AlertDialog6(13);
		} else
			AlertDialog1();
	}

	public void onAvatarImage14ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[13] > 0) {
				if (data.buck >= prices[13])
					AlertDialog5(14);
				else
					AlertDialog4();
			} else
				AlertDialog6(14);
		} else
			AlertDialog1();
	}

	public void onAvatarImage15ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[14] > 0) {
				if (data.buck >= prices[14])
					AlertDialog5(15);
				else
					AlertDialog4();
			} else
				AlertDialog6(15);
		} else
			AlertDialog1();
	}

	public void onAvatarImage16ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[15] > 0) {
				if (data.buck >= prices[15])
					AlertDialog5(16);
				else
					AlertDialog4();
			} else
				AlertDialog6(16);
		} else
			AlertDialog1();
	}

	public void onAvatarImage17ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[16] > 0) {
				if (data.buck >= prices[16])
					AlertDialog5(17);
				else
					AlertDialog4();
			} else
				AlertDialog6(17);
		} else
			AlertDialog1();
	}

	public void onAvatarImage18ButtonClick(View view) {
		if (takenCount >= 10) {
			if (prices[17] > 0) {
				if (data.buck >= prices[17])
					AlertDialog5(18);
				else
					AlertDialog4();
			} else
				AlertDialog6(18);
		} else
			AlertDialog1();
	}

	public void onAvatarImage19ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[18] > 0) {
				if (data.buck >= prices[18])
					AlertDialog5(19);
				else
					AlertDialog4();
			} else
				AlertDialog6(19);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage20ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[19] > 0) {
				if (data.buck >= prices[19])
					AlertDialog5(20);
				else
					AlertDialog4();
			} else
				AlertDialog6(20);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage21ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[20] > 0) {
				if (data.buck >= prices[20])
					AlertDialog5(21);
				else
					AlertDialog4();
			} else
				AlertDialog6(21);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage22ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[21] > 0) {
				if (data.buck >= prices[21])
					AlertDialog5(22);
				else
					AlertDialog4();
			} else
				AlertDialog6(22);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage23ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[22] > 0) {
				if (data.buck >= prices[22])
					AlertDialog5(23);
				else
					AlertDialog4();
			} else
				AlertDialog6(23);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage24ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[23] > 0) {
				if (data.buck >= prices[23])
					AlertDialog5(24);
				else
					AlertDialog4();
			} else
				AlertDialog6(24);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage25ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[24] > 0) {
				if (data.buck >= prices[24])
					AlertDialog5(25);
				else
					AlertDialog4();
			} else
				AlertDialog6(25);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage26ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[25] > 0) {
				if (data.buck >= prices[25])
					AlertDialog5(26);
				else
					AlertDialog4();
			} else
				AlertDialog6(26);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
			AlertDialog3();
	}

	public void onAvatarImage27ButtonClick(View view) {
		if (takenCount >= 20) {
			if (prices[26] > 0) {
				if (data.buck >= prices[26])
					AlertDialog5(27);
				else
					AlertDialog4();
			} else
				AlertDialog6(27);
		} else if (takenCount >= 10)
			AlertDialog2();
		else
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
			NextActivity();
		}
	}
	
	public void onAvatarBackButtonClick(View view) {
		NextActivity();
	}

	public void NextActivity() {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", avatar.userName);
		userLog.put("from", "AvatarImageEdit");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(AvatarImageEdit.this, MainActivity.class);
		startActivity(intent);
	}

}