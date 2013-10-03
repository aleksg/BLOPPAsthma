package com.blopp.bloppasthma.activities;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.MainMenuAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ShopActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);

	}

	private void activityStarter(Class<?> c)
	{
		Intent intent = new Intent(ShopActivity.this, c);
		startActivity(intent);
	}
	

}
