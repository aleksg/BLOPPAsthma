package com.blopp.bloppasthma.activities;

import com.blopp.bloppasthma.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SelectUserActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_user);
		
		ImageButton kids = (ImageButton)findViewById(R.id.kidImageButton);
		ImageButton parents = (ImageButton)findViewById(R.id.parentImageButton);
		
		parents.setOnClickListener(new ParentSelectedListener());
		kids.setOnClickListener(new KidSelectedListener());
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