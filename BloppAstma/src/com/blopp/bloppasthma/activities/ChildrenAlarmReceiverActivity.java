package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.div.ColorMeds;
import com.blopp.bloppasthma.models.MedicinePlanModel;

/**
 * The <code>Activity</code> used for the alarm. This is updated in the
 * <code>AlarmUpdateReciever</code> class, and it updates its view based on
 * which <code>MedicinePlanModel</code> is in the extras from the intent.
 */
public class ChildrenAlarmReceiverActivity extends Activity
{
	private static final String TAG = ChildrenAlarmReceiverActivity.class
			.getSimpleName();
	private MedicinePlanModel medicinePlanModel;
	private Ringtone ringtone;
	private AudioManager audioManager;
	private int ringerMode;

	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate is called in ChildrenAlarmReceiverActivity");
		// update medicinePlanModel with the medicine sent in with the intent
		medicinePlanModel = (MedicinePlanModel) getIntent().getExtras()
				.getSerializable("medicinePlanModel");

		// set window related stuff, view etc.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.alarm);

		// update the view based on the medicinePlanModel
		updateViewWithMedicine();

		// Find the phones assigned alarmsound, and play it.
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_ALARM);
		ringtone = RingtoneManager.getRingtone(getApplicationContext(),
				notification);
		ringtone.play();

		// Set the phones ringer mode to be normal
		audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
		ringerMode = audioManager.getRingerMode();
		audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

		AlarmDragListener dragListener = new AlarmDragListener();
		ImageView cancelAlarmImage = (ImageView) findViewById(R.id.stop_alarm_imageview);
		cancelAlarmImage.setOnDragListener(dragListener);
		cancelAlarmImage.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				stopAlarm();
			}
		});

		((ImageView) findViewById(R.id.alarm_medicine_imageview))
				.setOnTouchListener(new MedicineTouchListener());

		((ImageView) findViewById(R.id.alarm_mask_imageview))
				.setOnDragListener(dragListener);
		((ImageView) findViewById(R.id.alarm_mask_imageview))
		.setOnTouchListener(new OnTouchListener()
		{
			
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				startTreatmentActivity();
				return true;
			}
		});
	}

	private void stopAlarm()
	{
		ringtone.stop();
		audioManager.setRingerMode(ringerMode);
		finish();
	}

	private void startTreatmentActivity()
	{
		Intent intent = new Intent(ChildrenAlarmReceiverActivity.this,
				DistractionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("medicinePlanModel", medicinePlanModel);
		intent.putExtras(bundle);
		startActivity(intent);
		ringtone.stop();
		audioManager.setRingerMode(ringerMode);
		finish();
	}

	/**
	 * Updates the view with the medicine that was found in the intents extras.
	 * this means updating the image and text according to medicinePlanModel.
	 */
	private void updateViewWithMedicine()
	{
		ImageView imgv = (ImageView) findViewById(R.id.alarm_medicine_imageview);
	}

	private ImageView getStopAlarmImageView()
	{
		return (ImageView) findViewById(R.id.stop_alarm_imageview);
	}

	private ImageView getMaskImageView()
	{
		return (ImageView) findViewById(R.id.alarm_mask_imageview);
	}

	private class MedicineTouchListener implements OnTouchListener
	{
		@Override
		public boolean onTouch(View medicineImageView, MotionEvent event)
		{
			Log.d(TAG, "Touch received");
			float imageSizeX = medicineImageView.getWidth();
			float imageSizeY = medicineImageView.getHeight();

			switch (event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
					ClipData clipData = ClipData.newPlainText("", "");
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
							medicineImageView);
					medicineImageView.startDrag(clipData, shadowBuilder,
							medicineImageView, 0);
					
					medicineImageView.setVisibility(View.INVISIBLE);
					return true;
				
						
			}
			return false;
		}

	}

	private class AlarmDragListener implements OnDragListener
	{

		@Override
		public boolean onDrag(View v, DragEvent event)
		{
			View dragview = (View) event.getLocalState();
			switch (event.getAction())
			{
				case DragEvent.ACTION_DROP:
					if(v == findViewById(R.id.alarm_mask_imageview)){
						Log.d(TAG, "ACTION_DROP on alarm_mask_imageview");
						startTreatmentActivity();
						return true;
					}else if(v == findViewById(R.id.stop_alarm_imageview)){
						Log.d(TAG, "ACTION_DROP on stop_alarm_imageview");
						stopAlarm();
						return true;
					}
				default:
					break;
			}
			if(dragEventNotHandled(event)){
				dragview.setVisibility(View.VISIBLE);
			}
			return true;
		}

	}

	private boolean dragEventNotHandled(DragEvent event)
	{
		return !event.getResult();
	}
	// private class CancelMedicationDragListener implements OnDragListener{
	//
	// @Override
	// public boolean onDrag(View v, DragEvent event)
	// {
	// View dragView = (View)event.getLocalState();
	// if(v == findViewById(R.id.stop_alarm_imageview)){
	// switch(event.getAction())
	// {
	// case DragEvent.ACTION_DROP:
	// Log.d(TAG, "ACTION_DROP registered in CancelMedicationDragListener");
	// stopAlarm();
	// return true;
	// default:
	// break;
	// }
	//
	// }
	// return false;
	// }
	//
	// }

}