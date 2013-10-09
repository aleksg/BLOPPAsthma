package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.Reward;
import com.blopp.bloppasthma.mockups.SavedRewards;

public class AddRewardActivity extends Activity
{
	private static String sharedPreferenceName = "RewardList";
	
	private static final String TAG = AddRewardActivity.class.getSimpleName();
	private EditText descriptionText, starsText;
	private Button findImageButton, saveRewardButton;
	private CheckBox repeatRewardCheckbox;
	private SavedRewards savedRewards;
	private Bitmap selectedImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_reward);
		
		savedRewards = new SavedRewards(getApplicationContext());
		
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
						setResult(Activity.RESULT_OK, new Intent());
						finish();
					}
				});
		findImageButton.setOnClickListener(new FindImageClickListener());
		
	}
	
	private void save()
	{
		savedRewards.saveReward(createReward());
	}
	
	private Reward createReward()
	{
		String desc = descriptionText.getText().toString();
		int stars = Integer.parseInt(starsText.getText().toString());
		boolean repeat = repeatRewardCheckbox.isSelected();
		int currentMax = savedRewards.getMaximumIdentifier(getApplicationContext());
		
		Reward r = new Reward(currentMax)
						.setDescription(desc)
						.setStars(stars)
						.setOrdered(false)
						.setRepeat(repeat);
		if(selectedImage == null){
			selectedImage = BitmapFactory.decodeResource(getResources(), R.drawable.book_small);
			
		}
		return r.setBitmap(selectedImage);
	}
	private class FindImageClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			activityStarter(CameraActivity.class);
			Log.d(TAG, "Should find images now.");
		}
		
	}
	
	private void activityStarter(Class<?> c)
	{
		Log.d(c.getSimpleName(), "activityStarter");
		Intent intent = new Intent(AddRewardActivity.this, c);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(getIntent().hasExtra("image")){
			byte[] image = getIntent().getExtras().getByteArray("image");
			selectedImage = BitmapFactory.decodeByteArray(image, 0, image.length);
		}	
	}
}