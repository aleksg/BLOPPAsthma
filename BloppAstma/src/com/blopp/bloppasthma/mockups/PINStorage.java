package com.blopp.bloppasthma.mockups;

import android.content.Context;

public class PINStorage
{
	private static final String TAG = PINStorage.class.getSimpleName();
	private static String sharedPreferenceName = "PIN";
	private Context context;
	
	public PINStorage(Context context)
	{
		this.context = context;
	}
	
	public void savePin(int number) throws IllegalArgumentException{
		if(number < 1000 && number > 9999){
			throw new IllegalArgumentException("PIN must be 4 digits");
		}
		else{
			context.getSharedPreferences(sharedPreferenceName, 0).
				edit().putInt("PIN", number).commit();
		}
	}
	public boolean hasSavedPin(){
		if(context.getSharedPreferences(sharedPreferenceName, 0).contains("PIN")){
			return true;
		}
		return false;
	}
	public boolean comparePin(int inputPIN){
		int storedValue = context.getSharedPreferences(sharedPreferenceName, 0).
								getInt("PIN", -1);
		if(storedValue == inputPIN){
			return true;
		}
		return false;
	}
	public int getPin(){
		return -1;
	}
	public Context getContext(){
		return this.context;
	}
}
