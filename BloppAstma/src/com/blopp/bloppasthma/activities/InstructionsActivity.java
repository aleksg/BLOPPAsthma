package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.TabsAdapter;
//TODO: Duplicate evil twin
public class InstructionsActivity extends SyncActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.pager);
	    TabsAdapter adapter = new TabsAdapter();
	    ViewPager myPager = (ViewPager) findViewById(R.id.instructionspager);
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
