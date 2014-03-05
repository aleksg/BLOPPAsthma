package com.blopp.bloppasthma.activities;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.Reward;
import com.blopp.bloppasthma.mockups.SavedRewards;
import com.blopp.bloppasthma.utils.TemporarilyImageStore;

public class AddRewardActivity extends SyncActivity
{
	private static String sharedPreferenceName = "RewardList";
	private static final int CAMERA_REQUEST = 1888;
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

		descriptionText = (EditText) findViewById(R.id.descriptionText);
		findImageButton = (Button) findViewById(R.id.findImageButton);
		saveRewardButton = (Button) findViewById(R.id.saveRewardButton);
		repeatRewardCheckbox = (CheckBox) findViewById(R.id.repeatRewardCheckbox);
		starsText = (EditText) findViewById(R.id.rewardCostText);
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	private void save()
	{
		savedRewards.saveReward(createReward());
		TemporarilyImageStore store = new TemporarilyImageStore(
				getApplicationContext());
		store.removeTemporarilyImage();
	}

	private Reward createReward()
	{
		if (descriptionText.getText().toString() == null
				|| starsText.getText().toString() == null)
		{
			Toast.makeText(getApplicationContext(),
					"Fyll inn beskrivelse og antall stjerner",
					Toast.LENGTH_SHORT).show();
		}
		String desc = descriptionText.getText().toString();
		int stars = Integer.parseInt(starsText.getText().toString());
		boolean repeat = repeatRewardCheckbox.isSelected();
		int currentMax = savedRewards.getMaximumIdentifier();

		Reward r = new Reward(currentMax).setDescription(desc).setStars(stars)
				.setOrdered(false).setRepeat(repeat);

		if (selectedImage == null)
		{

			Log.d(TAG, "Selected image is null");

			selectedImage = BitmapFactory.decodeResource(getResources(),
					R.drawable.medal);
			r.setBitmap(selectedImage);
		} else
		{
			Log.d(TAG, "Selected image was not null");
			r.setBitmap(selectedImage);
		}

		return r;
	}

	private class FindImageClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			DialogFragment dialog = new FindImageDialog();
			dialog.show(getFragmentManager(), TAG);
		}

	}

	@Override
	protected void onResume()
	{
		Log.d(TAG, "Resuming activity");

		super.onResume();

		TemporarilyImageStore store = new TemporarilyImageStore(
				getApplicationContext());
		if (store.hasStoredAnImage())
		{
			Log.d(TAG, "Found an image. WOHO!");
			selectedImage = store.getByteImage().getImageAsBitmap();
		}

	}

	@SuppressLint("ValidFragment")
	public class FindImageDialog extends DialogFragment
	{

		public FindImageDialog()
		{

		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Velg methode").setItems(
					R.array.reward_image_options,
					new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							if (which == 0)
							{
								startCamera();
							} else if (which == 1)
							{
								startImageGallery();
							}

						}
					});
			return builder.create();
		}
	}

	public void startCamera()
	{
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);
	}

	public void startImageGallery()
	{
		Intent imageIntent = new Intent(AddRewardActivity.this,
				SelectDefaultRewardImageActivity.class);
		startActivityForResult(imageIntent, 1337);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
		{
			selectedImage = (Bitmap) data.getExtras().get("data");
			Log.d(TAG, "Got activity result");
		} else if (requestCode == 1337 && resultCode == RESULT_OK)
		{
			int resource = (Integer) data.getExtras().get("ResourceId");
			selectedImage = BitmapFactory.decodeResource(getResources(),
					resource);
			Log.d(TAG, "Successfully got a resource");
		} else
		{
			Log.d(TAG, "Did not get a good result");
		}

	}

}
