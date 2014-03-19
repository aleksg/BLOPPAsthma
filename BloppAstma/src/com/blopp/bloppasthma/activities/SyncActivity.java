package com.blopp.bloppasthma.activities;


import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.jsonposters.NewUserPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;

public class SyncActivity extends Activity
{
	
	private String TAG = SyncActivity.class.getSimpleName();

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
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

	private void synchronizeWithAsthmaBuddy(String ip)
	{
		NewUserPoster newPoster = new NewUserPoster(ip, new ChildIdService(
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
	
	@SuppressLint("ValidFragment")
	protected class SyncDialog extends DialogFragment
	{
		private int _selected;
		public SyncDialog()
		{
			
		}
		public void setSelected(int which){
			this._selected = which;
		}
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    
		    builder.setTitle(R.string.sync_menu_title).setSingleChoiceItems(R.array.sync_options, 0, 
		                      new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						setSelected(which);
					}
		           })
		    // Set the action buttons
		           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	   String selectedIp = getResources().getStringArray(R.array.sync_options)[_selected];
		            	
		            	 
		            	   synchronizeWithAsthmaBuddy(selectedIp); 
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
