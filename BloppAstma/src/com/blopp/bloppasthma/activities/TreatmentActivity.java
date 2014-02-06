package com.blopp.bloppasthma.activities;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
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
	
	
	private AvailableMedicines availableMedicines;
	private Button submitButton;
	private ListView medicineListView; //Listview containing a checkbox, the medicinename and the icon of the medicine. 
	
	private DatePicker datePicker;
	
	private MyDate today, selected;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treatment);
		
		childIdService = new ChildIdService(getApplicationContext());
		
		submitButton = (Button) findViewById(R.id.register_treatment_button);
		datePicker = (DatePicker)findViewById(R.id.datePicker);
		
		today = new MyDate(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
		
		availableMedicines = new AvailableMedicines();
		medicineListView = (ListView) findViewById(R.id.treatment_medication_listView);
		medicineListView.setDivider(null);
		
		medicineListView.setAdapter(new MedicineRadioAdapter(
				getApplicationContext()));
	
		
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
		
		post(formatDate(), medicineId);
	}
	
	/**
	 * 
	 * @return false if the medicine is taken on a date later than today 
	 */
	private boolean inputDateIsLegal()
	{	
		selected = new MyDate(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
		if(today.compareTo(selected) == -1)
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
	
	/**
	 * 
	 * @author aarseth_90
	 * Helper class, used to keep track of today's date, and the date picked in the DatePicker. 
	 */
	public class MyDate implements Comparable<MyDate>{
		private int year; 
		private int month;
		private int day_of_month;
		
		public MyDate(int year, int month, int day_of_month)
		{
			super();
			this.year = year;
			this.month = month;
			this.day_of_month = day_of_month;
		}
		public int getYear()
		{
			return year;
		}
		public void setYear(int year)
		{
			this.year = year;
		}
		public int getMonth()
		{
			return month;
		}
		public void setMonth(int month)
		{
			this.month = month;
		}
		public int getDay_of_month()
		{
			return day_of_month;
		}
		public void setDay_of_month(int day_of_month)
		{
			this.day_of_month = day_of_month;
		}
		@Override
		public int compareTo(MyDate another)
		{
			System.out.println("Comparing Mydates");
			if(this.year > another.getYear())
			{
				return 1;
			}else if(this.year == another.getYear()){
				System.out.println("Same year");
				if(this.month == another.getMonth()){
					System.out.println("Same month");
					if(this.day_of_month == another.getDay_of_month())
					{
						System.out.println("Same day");
						return 0;
					}else if(this.day_of_month > another.getDay_of_month())
					{
						System.out.println("Day is greater than picked date");
						return 1;
					}else if(this.day_of_month < another.getDay_of_month())
					{
						
						System.out.println("Day is earlier than picked date");
						return -1;
					}
					
				}else if(this.month > another.getMonth())
				{
					System.out.println("Todays month is greater than another dates month");
					return 1;
				}else if(this.month < another.getMonth())
				{
					return -1;
				}
			}
			else if(this.year < another.getYear())
			{
				return -1;
			}
			System.out.println("Returning 0 for some reason");
			return 0;
		}
		
		
	}
	
}
