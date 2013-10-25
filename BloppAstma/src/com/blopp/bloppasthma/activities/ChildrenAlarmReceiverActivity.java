package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
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
 * <code>AlarmUpdateReciever</code> class, and it updates its view based on which 
 * <code>MedicinePlanModel</code> is in the extras from the intent.
 */
public class ChildrenAlarmReceiverActivity extends Activity{
	private static final String TAG = ChildrenAlarmReceiverActivity.class.getSimpleName();
    private MedicinePlanModel medicinePlanModel;
    private Ringtone ringtone;
    private AudioManager audioManager;
    private int ringerMode;
    @SuppressWarnings("static-access")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate is called in ChildrenAlarmReceiverActivity");
        //update medicinePlanModel with the medicine sent in with the intent
		medicinePlanModel = (MedicinePlanModel) getIntent().getExtras().getSerializable("medicinePlanModel");
       
		
        // set window related stuff, view etc.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.alarm);
        
        //update the view based on the medicinePlanModel
        updateViewWithMedicine();
        
        //Find the phones assigned alarmsound, and play it.
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringtone.play();
        
        //Set the phones ringer mode to be normal
		audioManager = (AudioManager)getSystemService(this.AUDIO_SERVICE);
        ringerMode = audioManager.getRingerMode();
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        
        
        //Button for stopping the alarm
        ImageView stopAlarm = (ImageView) findViewById(R.id.stop_alarm_imageview);
        stopAlarm.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	ringtone.stop();
            	audioManager.setRingerMode(ringerMode);
                finish();
            }
        });
        
        //button for starting the distraction sequence.
        ImageView startTreatment = (ImageView) findViewById(R.id.alarm_medicine_imageview);
        startTreatment.setOnTouchListener(new MedicineTouchListener());
        ImageView maskImage = (ImageView) findViewById(R.id.alarm_mask_imageview);
        maskImage.setOnDragListener(new MaskDragListener());
        
        
        
//        startTreatment.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				Log.d(DistractionActivity.class.getSimpleName(), "activityStarter");
//				Intent intent = new Intent(ChildrenAlarmReceiverActivity.this,DistractionActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("medicinePlanModel", medicinePlanModel);
//				intent.putExtras(bundle);
//				startActivity(intent);
//				ringtone.stop();
//            	audioManager.setRingerMode(ringerMode);
//				finish();
//			}
//		});
        
    }
    private class MedicineTouchListener implements OnTouchListener
    {

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder builder = new DragShadowBuilder(v);
			v.startDrag(data, builder, v, 0);
			
			Log.d(TAG, "Touch recognized");
			return true;
		}
    	
    }
    private class MaskDragListener implements OnDragListener
    {
    	
		@Override
		public boolean onDrag(View v, DragEvent event)
		{
			ImageView medicineView = getMedicineImageView();
			switch (event.getAction())
			{
				case DragEvent.ACTION_DRAG_ENDED:
					Log.d(TAG, "Drag ended");
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					Log.d(TAG, "Drag exited");
					break;
				case DragEvent.ACTION_DROP:
					Log.d(TAG, "Drag dropped");
					break;
				default:
					break;
			}
			Log.d(TAG, "Dragged something");
			return false;
		}
    	
    }
    private ImageView getMedicineImageView(){
    	return (ImageView) findViewById(R.id.alarm_medicine_imageview);
    }
    /**
     * Updates the view with the medicine that was found in the intents extras.
     * this means updating the image and text according to medicinePlanModel.
     */
    private void updateViewWithMedicine() {
    	ImageView imgv = (ImageView) findViewById(R.id.alarm_medicine_imageview);
//    	TextView txtv = (TextView) findViewById(R.id.alarm_medicine_textview);
//    	imgv.setImageResource(ColorMeds.medicineImage(medicinePlanModel.getMedicineColor()));
//		txtv.setText(medicinePlanModel.getMedicineName());
	}
}