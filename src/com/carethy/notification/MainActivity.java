package com.carethy.notification;

import com.sattone.myfirstapp.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.sattone.myfirstapp.MESSAGE";
	public final static String NOTIFICATION_TITLE = "com.sattone.myfirstapp.NOTIFICATION_TITLE";
	public final static String NOTIFICATION_MESSAGE = "com.sattone.myfirstapp.NOTIFICATION_MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button dialogButton = (Button) findViewById(R.id.dialog);
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						MainActivity.this);
				alertDialogBuilder.setTitle("Warning");
				alertDialogBuilder
						.setMessage("Your blood pressure is high, please take hypotensor!!!")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
										// finish();
									}
								});
				// .setNegativeButton("Cancel",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int id) {
				// dialog.cancel();
				// }
				// });
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		String notificationTitle = "Carethy";
		String notificationMessage = "You need to get more sleep.";
		intent.putExtra(NOTIFICATION_TITLE, notificationTitle);
		intent.putExtra(NOTIFICATION_MESSAGE, notificationMessage);

		startActivity(intent);
	}

	public void showWarningDialog(View view) {
		sendMessage(view);
	}

	public void sendNotification(View view) {
		String notificationTitle = "Carethy";
		String notificationMessage = "You need to get more sleep.";

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		intent.putExtra(NOTIFICATION_TITLE, notificationTitle);
		intent.putExtra(NOTIFICATION_MESSAGE, notificationMessage);

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);

		Notification notif = new Notification.Builder(this)
				.setContentTitle(notificationTitle)
				.setContentText(notificationMessage)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(pendingIntent).setTicker(notificationMessage)
				.build();

		notif.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(0, notif);
	}
}
