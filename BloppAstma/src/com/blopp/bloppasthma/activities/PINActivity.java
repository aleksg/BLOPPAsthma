package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.PINStorage;
/*TODO: 
 * Aktiviteten starter opp igjen ved bruk av back-knappen. Kan dette disables?
 */
public class PINActivity extends Activity
{
	private static final String TAG = PINActivity.class.getSimpleName();
	private PINStorage storage;
	private TextView infoTextView;
	private EditText pinField;
	private Button submitPinButton;
	
	private int[] newPinTable;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pin_activity);
		storage = new PINStorage(getApplicationContext());
		infoTextView = (TextView)findViewById(R.id.pinInfoTextView);
		pinField = (EditText) findViewById(R.id.pinEditText);
		submitPinButton = (Button)findViewById(R.id.pinSubmitButton);
		submitPinButton.setOnClickListener(new SubmitPinClickListener());
		if(hasStoredPin()){
			infoTextView.setText(R.string.answer_pin_challenge);
			
		}else{
			newPinTable = new int[2];
		}
		
	}
	private class SubmitPinClickListener implements OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			if(hasStoredPin())
			{
				controlPin();
			}	
			else{
				firstTimeUsage();	
			}
		}

		private void controlPin()
		{
			if(pinIsCorrect(Integer.parseInt(pinField.getText().toString()))){
				startSelectUserActivity();
			}else{
				infoTextView.setText(R.string.pin_challenge_failed);
				infoTextView.setTextColor(Color.RED);
				pinField.setText("");
			}
		}
		private void firstTimeUsage()
		{
			
			if(pinField.getText().toString() == null)
			{
				//Should not work, restart process and try again
				clearTable();
				showLengthErrorMessage();
				pinField.setText("");
				return;
			}
			int pinCode = Integer.parseInt(pinField.getText().toString());
			if(!isValidLength(pinCode))
			{
				//Should give error message
				clearTable();
				showLengthErrorMessage();
				pinField.setText("");
				return;
			}
			
			//Clear field and repeat. Store the value afterwards
			if(hasMadeFirstAttempt())
			{
				checkIfPinsAreCorrect(pinCode);
			}else{
				
				storeTemproraryPin(pinCode);
				pinField.setText("");
				//Store firstAttempt and clear field
			}
		}
	}
	
	private boolean isValidLength(int pin){
		return (pin >= 1000 && pin <= 99999999);
	}
	
	//Step 1 of creating new PIN
	private void storeTemproraryPin(int firstAttempt)
	{
		newPinTable[0] = firstAttempt;
		infoTextView.setText(R.string.pin_repeat_message);
		Log.d(TAG, "First attempt is: " + firstAttempt);
	}
	//Step 2 of creating PIN
	private void checkIfPinsAreCorrect(int secondAttempt)
	{
		newPinTable[1] = secondAttempt;
		if(newPinTable[0] == newPinTable[1])
		{
			storage.savePin(secondAttempt);
			startSelectUserActivity();
		}else{
			Log.d(TAG, "first attempt was: " + newPinTable[0] + ". Second attempt was: " + newPinTable[1]);
			clearTable();
			showPINsNotEqualErrorMessage();
			pinField.setText("");
			//Delete both values, clear fields and start over.
		}
	}
	private void startSelectUserActivity(){
		
		Intent intent = new Intent(PINActivity.this, ParentsMainMenu.class);
		startActivity(intent);
	}
	private void showPINsNotEqualErrorMessage(){
		infoTextView.setText(R.string.pin_error_message);
		infoTextView.setTextColor(Color.RED);
	}
	private void showLengthErrorMessage(){
		infoTextView.setText(R.string.pin_length_error_message);
		infoTextView.setTextColor(Color.RED);
	}
	
	private void clearTable(){
		newPinTable[0] = 0;
		newPinTable[1] = 0;
	}
	private boolean hasMadeFirstAttempt(){
		return newPinTable[0] != 0;	
	}
	private boolean pinIsCorrect(int inputPin){
		return storage.comparePin(inputPin);
	}
	private boolean hasStoredPin(){
		return storage.hasSavedPin();
	}
	
}
