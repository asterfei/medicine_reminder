package com.example.medicinereminder;

import java.util.Date;
import java.util.List;

import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDisplayActivity extends Activity {
	private Database data = Database.getInstance();
	private ImageView image;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_display);

		Bundle bundle = this.getIntent().getExtras();
		Intent intent = this.getIntent();

		int month = intent.getIntExtra("month", 12);
		int day = intent.getIntExtra("day", 10);
		int year = intent.getIntExtra("year", 2013);

		Log.i("Info", month + " " + day + " " + year);
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ImageObj");
		
		boolean flag = false;
		
		try {
			List<ParseObject> results = query.find();
			for (ParseObject imageResult : results) {
				if (imageResult.getString("userName").equals(data.userName)) {
					Date createDate = imageResult.getCreatedAt();
					Log.i("Info", createDate.getMonth() + " " + createDate.getDate() + " " + createDate.getYear());
					if (createDate.getDate() == day
							&& createDate.getMonth() == month
							&& createDate.getYear() + 1900 == year) {
						flag = true;
						ParseFile imageFile = (ParseFile) imageResult
								.get("userFile");
						imageFile.getDataInBackground(new GetDataCallback() {
							public void done(byte[] data, ParseException e) {
								if (e == null) {
									Bitmap bmp = BitmapFactory.decodeByteArray(
											data, 0, data.length);
									image = (ImageView) findViewById(R.id.background_image);
									image.setImageBitmap(bmp);
								} else {
									image = (ImageView) findViewById(R.id.background_image);
								}
							}
						});
					} else {
						image = (ImageView) findViewById(R.id.background_image);
					}
				}
			}
		} catch (ParseException e) {
			Log.i("Info", "Error: " + e.getMessage());
		}
		if (!flag) {
			image = (ImageView) findViewById(R.id.background_image);
		}

		Log.i("CalenderData", "month:" + month + " day:" + day + " year:"
				+ year);
	}

	public void returnClick(View view) {
		Intent intent = new Intent();
		intent.setClass(ImageDisplayActivity.this, CaldroidSampleActivity.class);
		startActivity(intent);
	}

}
