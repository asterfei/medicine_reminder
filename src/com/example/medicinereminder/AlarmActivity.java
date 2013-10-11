package com.example.medicinereminder;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Menu;

public class AlarmActivity extends Activity {
	//AlarmSet alarmSet;
	String time;
	public static final int TakeOption_ID = 1;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 Database database = Database.getInstance();
		 
		 setContentView(R.layout.alarm_page);
		 TextView out = (TextView)findViewById(R.id.alarmMessage);
		 out.append(database.message);
	 }


	 public class SpinnerActivity extends Activity implements OnItemSelectedListener {


		 public void onItemSelected(AdapterView<?> parent, View view, 
				 int pos, long id) {
			 	// An item was selected. You can retrieve the selected item using
			 	time =(String)parent.getItemAtPosition(pos);
		 }

		    public void onNothingSelected(AdapterView<?> parent) {
		        time = "5";
		    }
		}

	 public void onIgnoreButtonClick(View view){
		 finish();
	 }
	 
	 public void onOpenButtonClick(View view){
			Intent i = new Intent(this, TakeOption.class);
			startActivityForResult(i, TakeOption_ID);
			
	}


}