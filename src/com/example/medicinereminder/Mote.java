package com.example.medicinereminder;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager.LayoutParams;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class Mote extends BroadcastReceiver {
	
	@SuppressLint("NewApi")
	public void onReceive(Context context, Intent intent) {
		Database db = Database.getInstance();
		Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
		int icon = R.drawable.avatar1;
		//CharSequence tickerText = "Time to take your medication!";
		CharSequence tickerText = intent.getExtras().getString("Message", "Time to take your medication!");
		long when = System.currentTimeMillis();
		
		CharSequence contentTitle = "My notification";
		CharSequence contentText = "Don't forget to take your medication!";
		
		final int NOTIF_ID = 1234;
		NotificationManager notofManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent notificationIntent = new Intent(context, TakeOption.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon, tickerText, when);
		
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		notification.flags = Notification.FLAG_INSISTENT;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.priority = Notification.PRIORITY_MAX;
		
		notofManager.notify(NOTIF_ID, notification);
		
		
	}

}
