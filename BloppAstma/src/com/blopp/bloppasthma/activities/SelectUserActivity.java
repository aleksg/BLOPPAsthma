package com.blopp.bloppasthma.activities;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.jsonmodels.AddChildPostModel;
import com.blopp.bloppasthma.jsonposters.AddChildPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.mockups.PINStorage;
import com.blopp.bloppasthma.services.AlarmUpdateReceiver;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SelectUserActivity extends Activity
{

	private static final String TAG = SelectUserActivity.class.getSimpleName();
	private ChildIdService childIdService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_user);
		
		ImageButton kids = (ImageButton)findViewById(R.id.kidImageButton);
		ImageButton parents = (ImageButton)findViewById(R.id.parentImageButton);
		
		parents.setOnClickListener(new ParentSelectedListener());
		kids.setOnClickListener(new KidSelectedListener());
		
		checkChildId();
		startAlarmUpdater();
	}
	private void startAlarmUpdater()
	{
		Log.d(TAG, "Starting to update alarms");
		//Set up the Calendar used for the first AlarmUpdateReceiver, 10 seconds from current time.
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND,10);
		Intent i = new Intent(this, AlarmUpdateReceiver.class);
		int updateTime = 30*1000;
		//Set up the pendingIntent, and set the alarm to repeat according to updateTime.
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
		    	0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), updateTime, pendingIntent);
	 
	}
	
	
	private void checkChildId()
	{
		
		childIdService = new ChildIdService(getApplicationContext());
		if(!(childIdService.hasChildId()))
		{
			AddChildPostModel model = new AddChildPostModel();
			AddChildPoster poster = new AddChildPoster(model.toString());
			poster.execute();
			try
			{
				poster.get();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			} catch (ExecutionException e)
			{
				e.printStackTrace();
			}
			childIdService.saveChildId(poster.getReceivedChildId());
			Log.d(TAG, "Received child id: " + childIdService.getChildId());
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_user, menu);
		return true;
	}
	
	private class ParentSelectedListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			Intent intent = new Intent(SelectUserActivity.this, PINActivity.class);
			startActivity(intent);
		}	
	}
	private class KidSelectedListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			Intent intent = new Intent(SelectUserActivity.this, KidsMainMenu.class);
			startActivity(intent);
		}
	}
	@Override
	public void onBackPressed()
	{
		/*
		 * Effectively disables the Back button. We do not want to go back to the PIN-page 
		 */
		
	}
}
