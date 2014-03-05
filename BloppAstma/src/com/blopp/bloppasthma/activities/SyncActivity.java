package com.blopp.bloppasthma.activities;

import java.util.concurrent.ExecutionException;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.jsonposters.NewUserPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;

import android.app.Activity;
import android.util.Log;
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
	            NewUserPoster newPoster = new NewUserPoster(new ChildIdService(getApplicationContext()).getChildId() + "");
	            newPoster.execute();
			try {
				String response = newPoster.get();
				Log.d("SyncActivity", response);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} catch (ExecutionException e) {
				
				e.printStackTrace();
			}
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
