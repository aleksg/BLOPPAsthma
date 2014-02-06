package com.blopp.bloppasthma.activities;

import java.sql.Date;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.MedicineListAdapter;
import com.blopp.bloppasthma.adapters.StarsAdapter;
import com.blopp.bloppasthma.div.Actions;
import com.blopp.bloppasthma.div.ColorMeds;
import com.blopp.bloppasthma.div.SoundStreamer;
import com.blopp.bloppasthma.jsonmodels.RegisterMedicinePostModel;
import com.blopp.bloppasthma.jsonparsers.MedicineListParser;
import com.blopp.bloppasthma.jsonposters.PostRegisterTreatment;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.models.Medicine;
import com.blopp.bloppasthma.models.MedicinePlanModel;

public class DistractionActivity extends Activity {
	private MedicinePlanModel medicinePlanModel;
	private Medicine medicine;
	private int healthStateId;
	private int reward;
	private ChildIdService childIdService;
	private static final String TAG = DistractionActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.distraction_screen);
        childIdService = new ChildIdService(getApplicationContext());
        Bundle b = getIntent().getExtras();
        if(b!=null){
        	if(getIntent().getExtras().containsKey("medicinePlanModel")) {
        		medicinePlanModel = (MedicinePlanModel) getIntent().getExtras().getSerializable("medicinePlanModel");
        		healthStateId = medicinePlanModel.getHealthStateId();
        		Log.d(TAG, "Health state is " + healthStateId);
        		action1s();
        	}
        }
        else{
        	//TODO: This is probably wrong health state id. Use parser!
        	healthStateId = 1;
			initPickMedicine();
		}
    }
	
	public String getMedicineColor()
	{
		if (medicinePlanModel != null)
		{
			Log.d("color", medicinePlanModel.getMedicineColor());
			return medicinePlanModel.getMedicineColor();
		}
		return medicine.getColor();	
	}
	
	public void action1s()
	{
		Log.d(TAG, "started action 1s");
		TextView textView = (TextView) findViewById(R.id.distraction_text);
		textView.setText(getString(R.string.medicationGreeting));
		final ImageView imageView = (ImageView) findViewById(R.id.distraction_image_view);
		imageView.setImageResource(R.drawable.karotz_normal);
		imageView.setOnClickListener(null);
		SoundStreamer.getInstance(Actions.action1s.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				imageView.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						action1c();
					}
				});
			}
		}).play();
	}
	
	//"fetch medicine, push head when ready"
	public void action1c()
	{
		String color = getMedicineColor();
		Actions action;
		if (color.equalsIgnoreCase("BLUE")) {
			action = Actions.action1b;
		} else if (color.equalsIgnoreCase("PURPLE")) {
			action = Actions.action1p;
		} else {
			action = Actions.action1o;
		}
		TextView textView = (TextView) findViewById(R.id.distraction_text);
		textView.setText(String.format(getString(R.string.medicationInstruction1), ColorMeds.getNorwegianWord(color)));
		final ImageView imageView = (ImageView) findViewById(R.id.distraction_image_view);
		imageView.setImageResource(ColorMeds.getNotificationImage(getMedicineColor()));
		imageView.setOnClickListener(null);
		SoundStreamer.getInstance(action.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				imageView.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						action2c();
					}
				});
			}
		}).play();
	}
	
	//"shake the medicine, push head when ready""
	public void action2c()
	{
		String color = getMedicineColor();
		Actions action;
		if (color.equalsIgnoreCase("BLUE")) {
			action = Actions.action2b;
		} else if (color.equalsIgnoreCase("PURPLE")) {
			action = Actions.action2p;
		} else {
			action = Actions.action2o;
		}
		TextView textView = (TextView) findViewById(R.id.distraction_text);
		textView.setText(String.format(getString(R.string.medicationInstruction2), ColorMeds.getNorwegianWord(color)));
		final ImageView imageView = (ImageView) findViewById(R.id.distraction_image_view);
		imageView.setImageResource(ColorMeds.shakeAnimation(color));
		((AnimationDrawable) imageView.getDrawable()).start();
		imageView.setOnClickListener(null);
		SoundStreamer.getInstance(action.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				imageView.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						action3c();
					}
				});
			}
		}).play();
	}
	
	public void action35s()
	{
		String color = getMedicineColor();
		TextView textView = (TextView) findViewById(R.id.distraction_text);
		textView.setText(R.string.Empty);
		ImageView imageView = (ImageView) findViewById(R.id.distraction_image_view);
		imageView.setImageResource(ColorMeds.maskInstructionImage(color));
		imageView.setOnClickListener(null);
		SoundStreamer.getInstance(Actions.action35s.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				action4s();
			}
		}).play();
	}
	
	// "put on mask, press me when ready"
	public void action3c()
	{
		String color = getMedicineColor();
		Actions action;
		if (color.equalsIgnoreCase("BLUE")) {
			action = Actions.action3b1;
		} else if (color.equalsIgnoreCase("PURPLE")) {
			action = Actions.action3p1;
		} else {
			action = Actions.action3o1;
		}
		TextView textView = (TextView) findViewById(R.id.distraction_text);
		textView.setText(getString(R.string.medicationInstruction3));
		final ImageView imageView = (ImageView) findViewById(R.id.distraction_image_view);
		imageView.setImageResource(ColorMeds.maskInstructionImage(color));
		imageView.setOnClickListener(null);
		SoundStreamer.getInstance(action.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				imageView.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						action35s();
					}
				});
			}
		}).play();
		
	}
	
	// countup to ten
	public void action4s()
	{
		TextView textView = (TextView) findViewById(R.id.distraction_text);
		textView.setText(getString(R.string.medicationInstructionCountup));
		ImageView imageView = (ImageView) findViewById(R.id.distraction_image_view);
		imageView.setImageResource(ColorMeds.breatheAnimation(getMedicineColor()));
		((AnimationDrawable) imageView.getDrawable()).start();
		imageView.setOnClickListener(null);
		SoundStreamer.getInstance(Actions.action41s.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				SoundStreamer.getInstance(Actions.action6s.getSoundFileUrl(), new OnCompletionListener()
				{
					public void onCompletion(MediaPlayer mp)
					{
						action7s();
					}
				}).play();
			}
		}).play();
	}
	
	//rewarding
	public void action7s()
	{
		setContentView(R.layout.distraction_screen_finished);
		TextView rewardsTextView = (TextView) findViewById(R.id.distraction_finished_reward_text);
		rewardsTextView.setText("");
		ImageView chestView = (ImageView) findViewById(R.id.distraction_finished_chest);
		chestView.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				visualizeReward();
			}
		});
		
		findReward();
	}
	
	public void initPickMedicine()
	{	
		setContentView(R.layout.medication_pick_medicine);
		Log.d(TAG, "set content view");
		final ListView listView = (ListView) findViewById(R.id.medicines_listView);
		Log.d(TAG, "found list view");
		MedicineListParser mlp = new MedicineListParser(getApplicationContext());
		Log.d(TAG, "inited med list parser");
		
		mlp.execute();
		try {
			mlp.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Medicine[] medicines = mlp.getMedicines().toArray(new Medicine[mlp.getMedicines().size()]);
		Log.d(TAG, "found meds");
		listView.setAdapter(new MedicineListAdapter(getApplicationContext(), medicines));
		Log.d(TAG, "set list adapter");
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				medicine = (Medicine)listView.getItemAtPosition(position);
				healthStateId = 1;
				setContentView(R.layout.distraction_screen);
				action1s();
			}
		});
	}
	
	
	
	public void findReward()
	{
		int medicineId;
		if (medicinePlanModel != null) {
			medicineId = medicinePlanModel.getMedicineId();
		} else {
			medicineId = medicine.getId();
		}
		Date sqlToday = new Date(new java.util.Date().getTime());
		
		RegisterMedicinePostModel postModel = new RegisterMedicinePostModel(sqlToday.toString(), medicineId, childIdService.getChildId(), healthStateId);
		PostRegisterTreatment poster = new PostRegisterTreatment(postModel.toString());
		
		poster.execute();
		try
		{
			poster.get();
		} catch (InterruptedException e)
		{
			Toast.makeText(getApplicationContext(), R.string.post_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return;
		} catch (ExecutionException e)
		{
			Toast.makeText(getApplicationContext(), R.string.post_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return;
		}
		reward = poster.getTreatmentResult().getReward();
	}
	
	public void visualizeReward()
	{
		GridView rewardStarsView = (GridView) findViewById(R.id.rewards_grid);
		int width = rewardStarsView.getWidth()/5;
		rewardStarsView.setColumnWidth(width);
		rewardStarsView.setAdapter(new StarsAdapter(this, Math.min(width, rewardStarsView.getHeight()), reward));
		TextView rewardsTextView = (TextView) findViewById(R.id.distraction_finished_reward_text);
		rewardsTextView.setText(String.format(getString(R.string.RewardsText), reward));
		final ImageView chestView = (ImageView) findViewById(R.id.distraction_finished_chest);
		chestView.setImageResource(R.drawable.chest_open);
		
		Actions file;
		switch (reward)
		{
			case 1: file = Actions.action7s1; break;
			case 2: file = Actions.action7s2; break;
			case 3: file = Actions.action7s3; break;
			case 4: file = Actions.action7s4; break;
			case 5: file = Actions.action7s5; break;
			case 7: file = Actions.action7s7; break;
			case 10: file = Actions.action7s10; break;
			default: file = Actions.action7s1;
		}
		chestView.setOnClickListener(null);
		SoundStreamer.getInstance(file.getSoundFileUrl(), new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				chestView.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						finish();
					}
				});
				
			}
		}).play();
	}
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
	}
}
