package com.blopp.bloppasthma.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.GenericListAdapter;
import com.blopp.bloppasthma.jsonmodels.AddMedicineToPlanModel;
import com.blopp.bloppasthma.jsonposters.AddMedicineToPlanPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.utils.AvailableMedicines;


public class AddMedicineToPlanActivity extends Activity
{
	private static final String TAG = AddMedicineToPlanActivity.class
			.getSimpleName();
	private Spinner spinnerMedicine;
	private TimePicker timepickerMedicine;
	private LinearLayout mLayout;
	private AvailableMedicines availableMeds;
	private Button btnAddMedicine;
	private int healthStateId;
	private ChildIdService childIdService;
	private MedicineSpinnerAdapter spinnerAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medication_to_plan);

		initializeSpinner();
		initializeHealthState();
	
		btnAddMedicine = (Button)findViewById(R.id.add_medicine_button);
		btnAddMedicine.setOnClickListener(new AddMedicineClickListener());
		
		mLayout = (LinearLayout) findViewById(R.id.add_medicine_linearlayout);
		timepickerMedicine = (TimePicker) findViewById(R.id.timePicker_medicine);
		timepickerMedicine.setIs24HourView(true);
		
		childIdService = new ChildIdService(getApplicationContext());
	}
	/**
	 * Initialize the medicine spinner to the medicines existing in the database. 
	 */
	public void initializeSpinner()
	{
		this.spinnerMedicine = (Spinner) findViewById(R.id.medicine_spinner);		
		spinnerAdapter = new MedicineSpinnerAdapter(getApplicationContext(), getMedicines());
		this.spinnerMedicine.setAdapter(spinnerAdapter);
	}
	private List<String> getMedicines()
	{
		availableMeds = new AvailableMedicines();
		List<String> medicines = new ArrayList<String>();
		Set<String> keys = availableMeds.medicinesMap.keySet();
		String[] medicationNames = new String[keys.size()];
		medicationNames = keys.toArray(medicationNames);
		for (String string : medicationNames)
		{
			medicines.add(string);
		}
		return medicines;
	}
	/**
	 * Get the health state given the input from the last activity
	 */
	private void initializeHealthState()
	{
		Bundle bundle = getIntent().getExtras();
		healthStateId = bundle.getInt("healthState");
	}

	private boolean executePost(AddMedicineToPlanModel model)
	{
		AddMedicineToPlanPoster poster = new AddMedicineToPlanPoster(
				model.toString());
		poster.execute();
		try
		{
			poster.get();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			return false;
		} catch (ExecutionException e)
		{
			e.printStackTrace();
			return false;
		}

		return poster.getPlanSuccessfullyPosted();
	}
	public class MedicineSpinnerAdapter extends GenericListAdapter<String>
	{
		public MedicineSpinnerAdapter(Context context, List<String> medicines)
		{
			super(context, medicines);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{

			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View spinnerItem;
			if(convertView == null)
			{
				spinnerItem = new View(context);
				spinnerItem = inflater.inflate(R.layout.single_string_list_adapter, parent, false);
				TextView textView = (TextView)spinnerItem.findViewById(R.id.string_textView);
				
				textView.setText((String)getItem(position));
				textView.setTextColor(Color.BLACK);
				textView.setTextSize(31);
				
			}else{
				spinnerItem = convertView;
			}
			return spinnerItem;
		}
		
	}

	private class AddMedicineClickListener implements OnClickListener{

		@Override
		public void onClick(View v)
		{
			int medicineId = availableMeds.getMedicineByName(spinnerMedicine
					.getSelectedItem().toString());
			String time = getTime();
			
			AddMedicineToPlanModel model = new AddMedicineToPlanModel(childIdService.getChildId(),
					healthStateId, time, medicineId);
			boolean wasSuccess = executePost(model);
			if (!(wasSuccess))
			{
				showToast();
				return;
			}
			Intent resultIntent = new Intent();
			
			setResult(Activity.RESULT_OK, resultIntent);
			finish();	
		}
		private String getTime(){
			int hour = timepickerMedicine.getCurrentHour();
			int minute = timepickerMedicine.getCurrentMinute();

			String time = hour + ":" + minute + ":" + "00";
			return time;
		}
		private void showToast(){
			Toast t = Toast.makeText(getApplicationContext(), R.string.post_error, Toast.LENGTH_LONG);
			t.show();
			return;
		}
	}

}
