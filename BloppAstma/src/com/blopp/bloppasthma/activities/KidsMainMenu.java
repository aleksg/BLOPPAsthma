package com.blopp.bloppasthma.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.services.AlarmUpdateReceiver;

public class KidsMainMenu extends Activity implements
		android.view.View.OnClickListener
{
	private Button DisplayRewardsButton, TreatmentButton, instructionsButton, shopButton;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.kids_main_menu);
		setUpListeners();
		//startAlarmUpdater();
	}

//	private void startAlarmUpdater() {
//		//Set up the Calendar used for the first AlarmUpdateReceiver, 10 seconds from current time.
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.SECOND,10);
//		Intent i = new Intent(this, AlarmUpdateReceiver.class);
//		int updateTime = 30*1000;
//		//Set up the pendingIntent, and set the alarm to repeat according to updateTime.
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
//		    	0, i, PendingIntent.FLAG_CANCEL_CURRENT);
//		AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
//		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), updateTime, pendingIntent);  	
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.select_user, menu);
		
		return true;
	}


	private void setUpListeners()
	{
		setUpListenerForButton(DisplayRewardsButton, R.id.rewards_button);
		setUpListenerForButton(TreatmentButton, R.id.treatment_button);
		setUpListenerForButton(instructionsButton, R.id.instructions_button);
		setUpListenerForButton(shopButton, R.id.shop_button);
	}

	private void setUpListenerForButton(Button b, int id)
	{
		b = (Button) findViewById(id);
		if(b == null){
			return;
		}
		b.setOnClickListener(this);
		Log.d("MainMenu", "Set up listener for id:" + id);
	}
	public void onClick(View v)
	{
		int vid = v.getId();
		Log.d("MainMenu", "got input on view: " + v.getId());
		if (vid == R.id.treatment_button)
		{
			startDistraction();
		} else if (vid == R.id.rewards_button)
		{
			startDisplayRewards();
		} else if (vid == R.id.instructions_button)
		{
			activityStarter(InstructionsActivity.class);
		} else if (vid == R.id.shop_button) 
		{
			activityStarter(ShopActivity.class);
		}
		Log.d("MainMenu", "v.id()=" + v.getId());
	}
	private void startDistraction(){
		if (isConnectedToInternet())
		{
			activityStarter(DistractionActivity.class);
		} else
		{
			makeConnectionToast();
		}
	}
	private void startDisplayRewards(){
		if (isConnectedToInternet())
		{
			activityStarter(DisplayRewardsActivity.class);
		}
		else
		{
			makeConnectionToast();
		}
	}
	private void activityStarter(Class<?> c)
	{
		Log.d(c.getSimpleName(), "activityStarter");
		Intent intent = new Intent(KidsMainMenu.this, c);
		startActivity(intent);
	}

	private boolean isConnectedToInternet()
	{
		ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectionManager
				.getActiveNetworkInfo();

		return activeNetworkInfo != null;
	}
	/**
	 * Give the user a notice that the device is not connected to internet
	 */
	private void makeConnectionToast()
	{
		Toast.makeText(getApplicationContext(), R.string.connect_message, Toast.LENGTH_LONG).show();
	}

}
