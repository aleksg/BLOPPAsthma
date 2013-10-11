package com.blopp.bloppasthma.activities;

import java.util.concurrent.ExecutionException;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.JsonModels.AddChildPostModel;
import com.blopp.bloppasthma.jsonposters.AddChildPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;

import android.os.Bundle;
import android.app.Activity;
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
			Intent intent = new Intent(SelectUserActivity.this, ParentsMainMenu.class);
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
}
