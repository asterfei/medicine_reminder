package com.example.medicinereminder;

import com.parse.ParseObject;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.widget.Toast;

public class Mote extends BroadcastReceiver {
	
	private Database data = Database.getInstance();
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
		int icon = R.drawable.avatar1;
		// CharSequence tickerText = "Time to take your medication!";
		CharSequence tickerText = intent.getExtras().getString("Message",
				"Time to take your medication!");
		long when = System.currentTimeMillis();

		CharSequence contentTitle = "My notification";
		CharSequence contentText = "Don't forget to take your medication!";

		final int NOTIF_ID = 1234;
		NotificationManager notofManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		ParseObject userLog = new ParseObject("Logs");
		userLog.put("userName", data.userName);
		userLog.put("from", "Mote");
		userLog.put("to", "MainActivity");
		userLog.saveInBackground();
		
		Intent notificationIntent = new Intent(context, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		
		Notification notification = new Notification(icon, tickerText, when);

		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		notification.flags = Notification.FLAG_INSISTENT;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.priority = Notification.PRIORITY_MAX;

		notofManager.notify(NOTIF_ID, notification);

	}

}
