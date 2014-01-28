package com.blopp.bloppasthma.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.JodaTimePermission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.MedicineListModel;
import com.blopp.bloppasthma.adapters.MedicineRadioAdapter;
import com.blopp.bloppasthma.jsonmodels.RegisterMedicinePostModel;
import com.blopp.bloppasthma.jsonparsers.HealthStateParser;
import com.blopp.bloppasthma.jsonposters.PostRegisterTreatment;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.utils.AvailableMedicines;
import com.blopp.bloppasthma.utils.DateAdapter;

public class TreatmentActivity extends Activity
{
	private static final String TAG = TreatmentActivity.class.getSimpleName();
	private ChildIdService childIdService;
	private EditText dateEditText;
	
	private AvailableMedicines availableMedicines;
	private Button submitButton;
	private String dateNow;
	private ListView medicineListView; //Listview containing a checkbox, the medicinename and the icon of the medicine. 
	private TextView dateTextField, chooseMedicineTextField;
	private DatePicker datePicker;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treatment);
		
		childIdService = new ChildIdService(getApplicationContext());
		
		submitButton = (Button) findViewById(R.id.register_treatment_button);
//		dateEditText = (EditText) findViewById(R.id.treatment_date_editText);
		datePicker = (DatePicker)findViewById(R.id.datePicker);
		
		availableMedicines = new AvailableMedicines();
		medicineListView = (ListView) findViewById(R.id.treatment_medication_listView);
		medicineListView.setDivider(null);
		
		medicineListView.setAdapter(new MedicineRadioAdapter(
				getApplicationContext()));
		dateTextField = (TextView)findViewById(R.id.treatment_date_textView);

		chooseMedicineTextField = (TextView) findViewById(R.id.treatment_choose_medicine);
		

		submitButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				submitForm();
			}
		});
	}
	

	/**
	 * When user presses "Register", validate the form and submit it. 
	 */
	private void submitForm()
	{
 
		
		MedicineRadioAdapter adapter = (MedicineRadioAdapter)medicineListView.getAdapter();
		MedicineListModel medicineChosen = adapter.getCheckedItem();
		if(medicineChosen==null)
		{
			Toast.makeText(getApplicationContext(), "Velg en medisin", Toast.LENGTH_SHORT).show();
			return;
		}
		int medicineId = availableMedicines
				.getMedicineByName(medicineChosen.getName());
		
		if (!inputDateIsLegal())
		{
			Toast.makeText(this, R.string.date_error,
					Toast.LENGTH_SHORT).show();
			return; //Return if date is invalid
		}
		
		//Should not occur unless there is some sort of injection in the view. 
		if (medicineId == -1)
		{
			Toast.makeText(getApplicationContext(),
					"Vennligst velg en medisin", Toast.LENGTH_SHORT).show();
			return; //Return if medicineId is -1. This happens when the medicine with given name is not in stored in the database. 
		}
		post(formatDate(), medicineId);
	}
	
	/**
	 * 
	 * @return false if the medicine is taken later than today 
	 */
	private boolean inputDateIsLegal()
	{
		Calendar current = Calendar.getInstance();
		
		Calendar picked = Calendar.getInstance();
		picked.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
		picked.set(Calendar.MONTH, datePicker.getMonth());
		picked.set(Calendar.YEAR, datePicker.getYear());
		
		if(current.compareTo(picked) == -1)
		{
			return false;
		}else{
			return true;
		}		
	}
	
	private void post(String date, int medicineId)
	{
		int healthStateId = getCurrentHealthState();
		if(healthStateId == -1)
		{
			return; //Return if we could not retrieve healthstate 
		}
		RegisterMedicinePostModel model = new RegisterMedicinePostModel(date,
				medicineId, childIdService.getChildId(), healthStateId);
		int reward = executeRegisterPost(model);
		if(reward == -1){
			showPostError();
			return;
		}
		Toast t2 = Toast.makeText(this, "Barnet ditt fikk " + reward
				+ " stjerner", Toast.LENGTH_LONG); //Notify the parent that the child got some stars
		t2.show();
		returnToMainMenu(); //Return when the post is completed
	}


	private int executeRegisterPost(RegisterMedicinePostModel model)
	{
		PostRegisterTreatment poster = new PostRegisterTreatment(
				model.toString());
		//Execute the post
		poster.execute();
		try
		{
			poster.get();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			return -1;
		} catch (ExecutionException e)
		{
			e.printStackTrace();
			return -1;
		}
		int reward = poster.getTreatmentResult().getReward();
		return reward;
	}
	
	private String formatDate()
	{
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth()+1;
		int year = datePicker.getYear();
		String date = String.format("%d-%d-%d", day, month, year); 
		
		return new DateAdapter(day, month, year).getSqlFormattedDate();
	}

	/**
	 * Called after the user has successfully submitted the form.
	 */
	private void returnToMainMenu()
	{
		finish();
	}
	private void showPostError()
	{
		Toast.makeText(getApplicationContext(), R.string.post_error, Toast.LENGTH_SHORT).show();
	}
	private int getCurrentHealthState()
	{
		HealthStateParser hParser = new HealthStateParser(childIdService.getChildId());
		int healthStateId = -1;
		hParser.execute();
		try
		{
			hParser.get();
		} catch (InterruptedException e)
		{
			Toast.makeText(getApplicationContext(), R.string.download_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return -1;
		} catch (ExecutionException e)
		{
			Toast.makeText(getApplicationContext(), R.string.download_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return -1;
		}
		healthStateId = hParser.getHealthStateId();
		return healthStateId;
		
	}

}
