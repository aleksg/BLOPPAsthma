package com.blopp.bloppasthma.activities;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.MainMenuAdapter;
import com.blopp.bloppasthma.utils.MainMenuItem;
import com.blopp.bloppasthma.utils.MainMenuItem.MenuOptions;

public class ParentsMainMenu extends SyncActivity implements OnItemClickListener
{

	private static final String TAG = ParentsMainMenu.class.getSimpleName();
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		listView = (ListView) findViewById(R.id.main_menu_listView);
		listView.setAdapter(new MainMenuAdapter(getApplicationContext()));
		listView.setOnItemClickListener(this);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed()
	{
		//TODO: Hoppe over steget som tar en til pin koden. How.
		Intent intent = new Intent(ParentsMainMenu.this, SelectUserActivity.class);
		startActivity(intent);
	}
	private void activityStarter(Class<?> c)
	{
		Intent intent = new Intent(ParentsMainMenu.this, c);
		startActivity(intent);
	}

	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id)
	{
		MainMenuAdapter adapter = (MainMenuAdapter) listView.getAdapter();
		List<MainMenuItem> items = adapter.getMainMenuItems();
		MainMenuItem itemClicked = items.get(position);
		reactToInput(itemClicked.getShortDesc());
	}

	/*
	 * Blocks input to activities that needs internet connection.
	 */
	public void reactToInput(MenuOptions option)
	{

		if (option.equals(MenuOptions.TREATMENT))
		{
			startTreatment();
		} else if (option.equals(MenuOptions.LOG))
		{
			startCalendar();
		} else if (option.equals(MenuOptions.MANUAL))
		{
			activityStarter(InstructionsActivity.class);
		} else if (option.equals(MenuOptions.PLAN))
		{
			startPlan();
		}else if(option.equals(MenuOptions.REWARD)){
			activityStarter(ParentShowRewardsActivity.class);
		}
	}

	private void startCalendar()
	{
		if (isConnectedToInternet())
		{
			activityStarter(CalendarActivity.class);
		} else
		{
			makeConnectionToast();
			return;
		}
	}

	private void startPlan()
	{
		if (isConnectedToInternet())
		{
			activityStarter(MedicationPlanActivity.class);
		} else
		{
			makeConnectionToast();
			return;
		}
		
	}

	private void startTreatment()
	{
		if (isConnectedToInternet())
		{
			activityStarter(TreatmentActivity.class);
		} else
		{
			makeConnectionToast();
		}
	}

	/**
	 * 
	 * @return a boolean, true if the device is connected to internet.
	 * 
	 */
	private boolean isConnectedToInternet()
	{
		ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectionManager
				.getActiveNetworkInfo();

		return activeNetworkInfo != null;
	}

	/**
	 * Give the user a notice that the device is not connected to internet.
	 */
	private void makeConnectionToast()
	{
		Toast.makeText(getApplicationContext(), R.string.connect_message,
				Toast.LENGTH_SHORT).show();
	}
}
