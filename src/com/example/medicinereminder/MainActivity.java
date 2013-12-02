package com.example.medicinereminder;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends Activity {

	public static MainActivity instance = null;

	private final Database data = Database.getInstance();

	private ViewPager mTabPager;
	private ImageView mTabImg;
	private ImageView mTab1, mTab2, mTab3;
	private final int zero = 0;
	private int currIndex = 0;
	private int one;
	private int two;
	private Uri imageUri;

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		instance = this;

		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

		mTab1 = (ImageView) findViewById(R.id.img_home);
		mTab2 = (ImageView) findViewById(R.id.img_medication);
		mTab3 = (ImageView) findViewById(R.id.img_settings);
		mTabImg = (ImageView) findViewById(R.id.img_tab_now);
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		Display currDisplay = getWindowManager().getDefaultDisplay();
		int displayWidth = currDisplay.getWidth();
		one = displayWidth / 3 + 5;
		two = one * 2 - 2;
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.main_tab_home, null);
		View view2 = mLi.inflate(R.layout.main_tab_medication, null);
		View view3 = mLi.inflate(R.layout.main_tab_setting, null);

		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mTabPager.setAdapter(mPagerAdapter);
	}

	//
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

	}

	public void EditMedication(View v) {
		Intent intent = new Intent(MainActivity.this, MedicationEdit.class);
		startActivity(intent);
	}

	public void EditAppointment(View v) {
		Intent intent = new Intent(MainActivity.this, AppointmentEdit.class);
		startActivity(intent);
	}

	public void EditRefill(View v) {
		Intent intent = new Intent(MainActivity.this, RefillEdit.class);
		startActivity(intent);
	}

	public void EditPersonal(View v) {
		Intent intent = new Intent(MainActivity.this, UserEdit.class);
		startActivity(intent);
	}

	public void EditAvatarImage(View v) {
		Intent intent = new Intent(MainActivity.this, AvatarImageEdit.class);
		startActivity(intent);
	}

	public void EditAvatarInformation(View v) {
		Intent intent = new Intent(MainActivity.this,
				AvatarInformationEdit.class);
		startActivity(intent);
	}

	public void AvatarAlreadyBought(View v) {
		Intent intent = new Intent(MainActivity.this,
				Avatar_alreadybought.class);
		startActivity(intent);
	}

	public void MyAvatarClick(View v) {
		Intent intent = new Intent(MainActivity.this,
				AvatarInformationDisplay.class);
		startActivity(intent);
	}

	public void onCalendarButton(View v) {
		Intent intent = new Intent(MainActivity.this,
				CaldroidSampleActivity.class);
		startActivity(intent);
	}

	public void onTookButtonClick(View view) {
		data.notTakenCount = 0;
		data.takenCount += 1;
		data.buck += 1;
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					// object.put("notTakenCount", 0);
					object.put("takenCount", data.takenCount);
					object.put("buck", data.buck);
					object.saveInBackground();
				} else {

				}
			}
		});

		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Users");
		query2.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
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
		
		Date newDate = new Date();
		ParseObject medsDate = new ParseObject("MedsDate");
		medsDate.put("username", data.userName);
		medsDate.put("date", newDate);
		medsDate.put("type", "Took");
		medsDate.saveInBackground();

	}

	public void onNotTakenButtonClick(View view) {
		data.takenCount = 0;
		data.notTakenCount += 1;
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
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
						@Override
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
						@Override
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
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "MainActivity");
		userLog.put("to", "SnoozeActivity");
		userLog.saveInBackground();

		Intent intent = new Intent();
		intent.setClass(MainActivity.this, SnoozeActivity.class);
		startActivity(intent);
	}

	public void onPillCamButtonClick(View view) {
		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "MainActivity");
		userLog.put("to", "TakePillPictureActivity");
		userLog.saveInBackground();

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory(),
				"test.jpg");
		imageUri = Uri.fromFile(file);

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					int buck = data.buck + 2;
					object.put("buck", buck);
					object.saveInBackground();
				} else {

				}
			}
		});
		
		Date newDate = new Date();
		ParseObject medsDate = new ParseObject("MedsDate");
		medsDate.put("username", data.userName);
		medsDate.put("date", newDate);
		medsDate.put("type", "PillCam");
		medsDate.saveInBackground();

		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

	}

	@Override
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
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
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
				.setMessage("Please set refill for next time!")
				.setNeutralButton("close", null).show();

	}

	public void onOfficeButtonClick(View view) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.getInBackground(data.objectId, new GetCallback<ParseObject>() {
			@Override
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
				.setMessage("Please set next office hour for next time!")
				.setNeutralButton("close", null).show();

	}
}
