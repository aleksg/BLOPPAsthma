package com.blopp.bloppasthma.activities;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.jsonposters.NewUserPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;

public class SyncActivity extends Activity
{

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.sync:
				synchronizeWithAsthmaBuddy();
				return true;	
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void synchronizeWithAsthmaBuddy()
	{
		NewUserPoster newPoster = new NewUserPoster(new ChildIdService(
				getApplicationContext()).getChildId() + "");
		newPoster.execute();
		try
		{
			Boolean success = newPoster.get();
			if(!success){	
				handleServerError("IOException"); 
			}else{
				handleSyncComplete();
			}
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			handleServerError("InterruptedException");
			
		} catch (ExecutionException e)
		{
			e.printStackTrace();
			handleServerError("ExecutionException");
		}
	}
	private void handleSyncComplete()
	{
		Toast.makeText(getApplicationContext(), "Successfully synced with server", Toast.LENGTH_LONG).show();
	}

	private void handleServerError(String exception){
		Toast.makeText(getApplicationContext(), "Could not reach server.\n. Is the node up and running?. \n Exception thrown: " + exception, Toast.LENGTH_LONG).show();
	}
}
