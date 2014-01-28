package com.blopp.bloppasthma.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blopp.bloppasthma.activities.ChildrenAlarmReceiverActivity;
import com.blopp.bloppasthma.jsonmodels.MedicationPlanResult;
import com.blopp.bloppasthma.jsonparsers.MedicationPlanParser;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.models.MedicinePlanModel;

/**
 * A broadcastReceiver in charge of updating the alarms used in this system. It deletes
 * all current alarms, and sets the new alarms, before it writes the new alarms to file, so it can
 * delete them regardless of database inconsistencies next time it updates.
 */
public class AlarmUpdateReceiver extends BroadcastReceiver {
	private static final String TAG = AlarmUpdateReceiver.class.getSimpleName();
	private Context context;
	private ChildIdService childIdService;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		Log.d(TAG, "onReceive is called");
    	updateAlarms();
    	
	}
		
	/**
     * Updates the alarms from the database. <code>MedicationPlanParser</code> is used to get the medication plans
     * from the database, before all the old alarms are deleted, and the new, based on the returns from the
     * database, is set.
     */
	public void updateAlarms(){
		Log.d(TAG, "Updating alarms");
		deleteOldAlarms();
    	Calendar calendar = Calendar.getInstance();
    	ArrayList<String> alarmIdList = new ArrayList<String>(); 
    	MedicationPlanResult result = getMedicationPlanResult();
		Log.d(TAG, "Looking through results");
		try{
			Log.d(TAG, "Found: " + result.getPlans().size() + " results in the set");
			for (MedicinePlanModel m : result.getPlans())
			{
				String time = m.getTime();
				
				Log.d(TAG, "time set for medicine is: " + time);
				/**/
				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
				calendar.set(Calendar.SECOND, Integer.parseInt(time.split(":")[2]));
				
				//check if we want to set the alarm tomorrow instead of today
				if (System.currentTimeMillis() > calendar.getTimeInMillis()){
					
					calendar.setTimeInMillis(calendar.getTimeInMillis()+ 24*60*60*1000);// Okay, then tomorrow ...
				}
				Log.d(TAG, "Alarm set at: " + calendar.toString());
				alarmIdList.add(Integer.toString((calendar.get(Calendar.MONTH)*1000000 + calendar.get(Calendar.DAY_OF_MONTH)*10000+ 
		        		calendar.get(Calendar.HOUR_OF_DAY)*100+calendar.get(Calendar.MINUTE))));
				
				createAlarm(calendar, m);
				calendar = Calendar.getInstance();
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		Log.d(TAG, "Creating alarm files");
		generateAlarmFile(alarmIdList);
    }

	private MedicationPlanResult getMedicationPlanResult()
	{
		childIdService = new ChildIdService(context);
		MedicationPlanParser parser = new MedicationPlanParser(childIdService.getChildId());
		parser.execute();
		try {
			parser.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		MedicationPlanResult result = parser.medicationPlanResult();
		return result;
	}

	private void deleteOldAlarms() {
		// Read alarm indexes from file
		StringBuffer buffer = new StringBuffer();
		String[] alarms = {};
		try {
			FileInputStream fis = context.openFileInput("alarms.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF8");
	        Reader in = new BufferedReader(isr);
	        int ch;
	        while ((ch = in.read()) > -1) {
	            buffer.append((char)ch);
	        }
	        in.close();
	        alarms = buffer.toString().split(",");
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		// Delete alarms
		
		for(int i = 0; i<alarms.length;i++){
			int index = Integer.parseInt(alarms[i]);
			Intent intent = new Intent(context, ChildrenAlarmReceiverActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context,
        			index, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        	AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        	alarmManager.cancel(pendingIntent);
		}
	}
	
	
	/**
	 * Creates a new alarm based on the <code>MedicationPlanModel</code>, at the time specified by the
	 * <code>Calendar</code>.
	 * @param calendar, The <code>Calendar</code> that specifies alarm time.
	 * @param medicinePlanModel, The <code>MedicationPlanModel</code> that triggers the alarm.
	 */
    public void createAlarm(Calendar calendar, MedicinePlanModel medicinePlanModel){
    	Log.d(TAG, "Create alarm is called");
        //Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(context, ChildrenAlarmReceiverActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Make a bundle to get the Medicine object across to the alarm, and add the relevant medicine
        Bundle bundle = new Bundle();
        bundle.putSerializable("medicinePlanModel", medicinePlanModel);
        intent.putExtras(bundle);
        int index = (calendar.get(Calendar.MONTH)*1000000 + calendar.get(Calendar.DAY_OF_MONTH)*10000+ 
        		calendar.get(Calendar.HOUR_OF_DAY)*100+calendar.get(Calendar.MINUTE));
        Log.d(TAG, "Alarm is being set at: " + calendar.toString());
        //Log.d("index",""+index);
        
        boolean alarmAlreadyUp = (PendingIntent.getBroadcast(context, index, intent, PendingIntent.FLAG_NO_CREATE) != null);
        if(!alarmAlreadyUp){
        	PendingIntent pendingIntent = PendingIntent.getActivity(context,
        			index, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        	AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        	alarmManager.cancel(pendingIntent);
        	alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);  	
        }
    
    }

    @SuppressWarnings("static-access")
	public void generateAlarmFile(ArrayList<String> list)
    {
    	if(list.size()==0) return;
    	
    	String FILENAME = "alarms.txt";
    	String alarms = "";
    	Log.d(TAG, "Size of alarm list in generate alarm file" + list.size());
    	for(int i = 0; i<list.size();i++){
    		if (i ==0)
    			alarms += list.get(i);
    		else
    			alarms += "," + list.get(i);
    	}
    	FileOutputStream fos;
		try {
			fos = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
			OutputStreamWriter out = new OutputStreamWriter(fos, "UTF8");
	        out.write(alarms);
	        out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
}
