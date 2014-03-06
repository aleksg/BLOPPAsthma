package com.blopp.bloppasthma.activities;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.jsonposters.NewUserPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class SyncActivity extends Activity
{
	
	private String TAG = SyncActivity.class.getSimpleName();

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.sync:
	        	SyncDialog dialog = new SyncDialog();
	        	dialog.show(getFragmentManager(), TAG);
	        	Log.d(TAG, "Trying to open dialog fragment");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void syncronizeData() {
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
	}
	
	@SuppressLint("ValidFragment")
	protected class SyncDialog extends DialogFragment
	{
		
		public SyncDialog()
		{
			
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    builder.setTitle(R.string.sync_menu_title).setSingleChoiceItems(R.array.sync_options, 0, 
		                      new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0)
						{
							//Use this IP
						} 
					}
		           })
		    // Set the action buttons
		           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	   syncronizeData(); //TODO: 
		                   Log.d(TAG, "User clicked save");
		               }
		           })
		           .setNeutralButton(R.string.add_ip, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//TODO: Add functionality for changing the IP adress
					}
				})
		           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	   Log.d(TAG, "User clicked cancel");
		               }
		           });

		    return builder.create();
		}
	}

}
