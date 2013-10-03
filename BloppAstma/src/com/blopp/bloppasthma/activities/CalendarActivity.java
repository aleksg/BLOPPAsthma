

package com.blopp.bloppasthma.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.joda.time.DateTime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.PollenDistributionAdapter;
import com.blopp.bloppasthma.adapters.TakenMedicinesAdapter;
import com.blopp.bloppasthma.models.LogModel;
import com.blopp.bloppasthma.models.PollenState;
import com.blopp.bloppasthma.utils.DateAdapter;
import com.blopp.bloppasthma.views.CalendarView;
import com.blopp.bloppasthma.views.CalendarView.OnCellTouchListener;
import com.blopp.bloppasthma.views.Cell;
import com.blopp.bloppasthma.xmlfeed.PollenCast;


public class CalendarActivity extends Activity implements
		android.view.View.OnClickListener, OnCellTouchListener
{
	private static final int CHILD_ID = 6;
	private static final String TAG = CalendarActivity.class.getSimpleName();
	CalendarView calendarView;
	TextView monthTextView;
	Button nextMonthButton, previousMonthButton;
	
	private ListView medicineTakenListView;
	private ListView pollenListView;
	private LogModel logModel;
	
	private PollenCast pollenCast;
	private DateAdapter dateAdapter;
	private int day, month, year;
	
	private DateTime dateTime = new DateTime();
	private TakenMedicinesAdapter medicineGridAdapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		
		logModel = new LogModel(CHILD_ID, dateTime.getMonthOfYear(), dateTime.getYear());
		
		calendarView = (CalendarView) findViewById(R.id.calendarview);
		calendarView.setOnCellTouchListener(this);
		
		initializeDaysShownInMedicineList();
		
		nextMonthButton = (Button) findViewById(R.id.next_month_button);
		nextMonthButton.setOnClickListener(this);
		
		previousMonthButton = (Button) findViewById(R.id.prev_month_button);
		previousMonthButton.setOnClickListener(this);
		
		monthTextView = (TextView) findViewById(R.id.month_of_year_textview);
		updateMonthTextField();
		
		medicineTakenListView = (ListView) findViewById(R.id.medicine_taken_listView);
		medicineGridAdapter = new TakenMedicinesAdapter(getApplicationContext(), getAmountOfMedicinesTaken());
		medicineTakenListView.setAdapter(medicineGridAdapter);
		
		pollenListView = (ListView)findViewById(R.id.pollen_listView);
		pollenListView.setAdapter(new PollenDistributionAdapter(getApplicationContext(), getPollenStates()));
		pollenListView.setDivider(null);
	}
	

	private void initializeDaysShownInMedicineList()
	{
		
		day = dateTime.getDayOfMonth();
		month = dateTime.getMonthOfYear();
		year = dateTime.getYear();
		dateAdapter = new DateAdapter(day, month, year);
		makeToast(day + "-" + month + "-" + year, Toast.LENGTH_SHORT);
	}
	
	private void updateMonthTextField()
	{
		DateTime.Property month = dateTime.monthOfYear();
		DateTime.Property year = dateTime.year();
		monthTextView.setText(month.getAsText() + "-" + year.getAsText());
	}
	
	/**
	 * When a user clicks "Next" or "Previous", update the days such that the JSON-call becomes correct.
	 */
	
	private void updateDates()
	{
		day = dateTime.getDayOfMonth();
		month = dateTime.getMonthOfYear();
		year = dateTime.getYear();
	}
	
	/**
	 * Updates medicineTakenListView according to day selected.
	 */
	public void onTouch(Cell cell)
	{
		day = cell.getDayOfMonth();
		month = calendarView.getMonth()+1;
		year = calendarView.getYear();
		
		dateAdapter = new DateAdapter(day, month, year);
		medicineTakenListView.setAdapter(new TakenMedicinesAdapter(getApplicationContext(), getAmountOfMedicinesTaken()));
		
		makeToast(day+"-"+month+"-"+year, Toast.LENGTH_SHORT);
	}
	/**
	 * Used if the user presses "next" or "previous"
	 */
	public void onClick(View v)
	{
		if (v.getId() == nextMonthButton.getId())
		{
			calendarView.nextMonth();
			dateTime = dateTime.plusMonths(1);
			logModel = new LogModel(CHILD_ID, dateTime.getMonthOfYear(), dateTime.getYear());
		} else if (v.getId() == previousMonthButton.getId())
		{
			calendarView.previousMonth();
			dateTime = dateTime.minusMonths(1);
			logModel = new LogModel(CHILD_ID, dateTime.getMonthOfYear(), dateTime.getYear());
		}
		updateMonthTextField();	
		updateDates();
		refreshMedicinesTaken(day, month, year);
	}
	
	private void refreshMedicinesTaken(int day, int month, int year)
	{
		dateAdapter = new DateAdapter(day, month, year);
		medicineTakenListView.setAdapter(new TakenMedicinesAdapter(getApplicationContext(), getAmountOfMedicinesTaken()));		
		makeToast(day+"-"+month+"-"+year, Toast.LENGTH_SHORT);
	}

	private HashMap<Integer, Integer> getAmountOfMedicinesTaken()
	{
		return logModel.getAmountOfMedicineAtDate(dateAdapter.getSqlFormattedDate());
	}
	
	/**
	 * WARNING: NOT COMPLETED YET.
	 * @return ArrayList<PollenState> which contains pollenstates for the selected day
	 */
	private ArrayList<PollenState> getPollenStates()
	{
		this.pollenCast = new PollenCast();
		pollenCast.execute();
		try
		{
			pollenCast.get();
		} catch (InterruptedException e)
		{
			makeToast(R.string.download_error, Toast.LENGTH_SHORT);
			//Error handling
			e.printStackTrace();
		} catch (ExecutionException e)
		{
			makeToast(R.string.download_error, Toast.LENGTH_SHORT);
			//Error handling
			e.printStackTrace();
		}
		return pollenCast.getPollenStateAtDayModel().getPollenStatesAtDay();
		
	}
	/*
	 * Used to infrom the user about which date is set.
	 * @param toastMessage, date to be displayed or the error message. 
	 * @param length
	 */
	private void makeToast(int toastMessage, int length)
	{
		Toast.makeText(this, toastMessage, length).show();
	}
	
	
	private void makeToast(String toastMessage, int length)
	{
		Toast.makeText(this, toastMessage, length).show();
	}
}