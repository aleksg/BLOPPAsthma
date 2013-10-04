package com.blopp.bloppasthma.activities;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.Reward;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddRewardActivity extends Activity
{
	
	private static final String TAG = AddRewardActivity.class.getSimpleName();
	private EditText descriptionText, starsText;
	private Button findImageButton, saveRewardButton;
	private CheckBox repeatRewardCheckbox;
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_reward);
		
		preferences = getPreferences(MODE_PRIVATE);
		
		descriptionText = (EditText)findViewById(R.id.descriptionText);
		findImageButton = (Button)findViewById(R.id.findImageButton);
		saveRewardButton = (Button)findViewById(R.id.saveRewardButton);
		repeatRewardCheckbox = (CheckBox) findViewById(R.id.repeatRewardCheckbox);
		starsText = (EditText)findViewById(R.id.rewardCostText);
		saveRewardButton.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						save();		
					}
				});
		findImageButton.setOnClickListener(new FindImageClickListener());
		
	}
	
	private void save()
	{
		String desc = descriptionText.getText().toString();
		int stars = Integer.parseInt(starsText.getText().toString());
		boolean repeat = repeatRewardCheckbox.isSelected();
		Reward mock = new Reward()
							.setDescription(desc)
							.setStars(stars)
							.setReceived(false);
		Editor prefsEditor = preferences.edit();
		Gson gson = new Gson();
		String json = gson.toJson("Reward");
		prefsEditor.putString("Reward", json);
		prefsEditor.commit();
		Log.d(TAG, "Saving reward");
	}
	
	private class FindImageClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			Log.d(TAG, "Should find images now.");
		}
		
	}
}
