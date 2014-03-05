package com.blopp.bloppasthma.activities;

import com.blopp.bloppasthma.R;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;


public class SyncActivity extends Activity
{

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.sync:
	            //sync(); TODO: Make connection with method for syncing
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
