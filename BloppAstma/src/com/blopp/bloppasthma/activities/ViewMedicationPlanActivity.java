	package com.blopp.bloppasthma.activities;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.JsonModels.DeleteMedicineModel;
import com.blopp.bloppasthma.JsonModels.MedicationPlanResult;
import com.blopp.bloppasthma.jsonparsers.MedicationPlanParser;
import com.blopp.bloppasthma.jsonposters.DeleteMedicineFromPlanPoster;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.models.HealthState;
import com.blopp.bloppasthma.models.HealthZone;
import com.blopp.bloppasthma.models.MedicinePlanModel;
import com.blopp.bloppasthma.utils.AvailableMedicines;

/**
 * An activity class that contains a listview over the different medicines in a
 * specific medicationplan.
 */
public class ViewMedicationPlanActivity extends Activity implements
		OnItemClickListener
{
	private ChildIdService childIdService;
	private static final String TAG = ViewMedicationPlanActivity.class
			.getSimpleName();
	private HealthZone healthZone;
	private ListView listView;
	private MedicationPlanParser parser;
	private Button addMedicineButton;
	private HashMap<String, String> timeMap;
	private int pressedMedicine;
	private ImageView medicinePlanImageView;
	private TextView medicineNameTextView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medication_plan_view);
		
		childIdService = new ChildIdService(getApplicationContext());
		
		
		initHealthZone();
		listView = (ListView) findViewById(R.id.medicine_listview);
		
		intializeList();
		initializeHeader(HealthState.getIdByHealthZone(healthZone));
		
		listView.setAdapter(new PlanMedicineListAdapter(timeMap));
		listView.setOnItemClickListener(this);		
		addMedicineButton = (Button) findViewById(R.id.add_medicine_button);
		addMedicineButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(ViewMedicationPlanActivity.this,
						AddMedicineToPlanActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("healthState", HealthState.getIdByHealthZone(healthZone));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initHealthZone()
	{
		if (getIntent().getExtras().containsKey("healthZone"))
			try
			{
				healthZone = (HealthZone) getIntent().getExtras().getSerializable(
						"healthZone");
			} catch (Exception e)
			{
				healthZone = HealthZone.GREEN_ZONE;
			}
		else
		{
			//TODO: Should throw some kind of exception.
			healthZone = HealthZone.GREEN_ZONE;
		}
	}
	
	private void initializeHeader(int healthZone)
	{
		PlanViewHolder holder = new PlanViewHolder(healthZone);
		medicinePlanImageView = (ImageView) findViewById(R.id.medicine_plan_smiley);
		medicinePlanImageView.setImageBitmap(holder.getImage());
		
		medicineNameTextView = (TextView) findViewById(R.id.medicine_plan_name);
		medicineNameTextView.setText(holder.getPlanName());
		Log.d(TAG, "name is " + holder.getPlanName());
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Activity.RESULT_OK)
		{
			intializeList();
		}
	}

	/**
	 * Initializes the list model behind the listview with the medicines lying
	 * in the repository.
	 */
	public void intializeList()
	{

		timeMap = new HashMap<String, String>();
		parser = new MedicationPlanParser(childIdService.getChildId());
		parser.execute();
		try
		{
			parser.get();
		} catch (InterruptedException e)
		{
			Toast.makeText(getApplicationContext(), R.string.download_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return;
		} catch (ExecutionException e)
		{
			Toast.makeText(getApplicationContext(), R.string.download_error, Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return;
		}

		MedicationPlanResult result = parser.medicationPlanResult();
		
		for (MedicinePlanModel m : result.getPlans())
		{
			if(m.getHealthStateId()==HealthState.getIdByHealthZone(healthZone))
			{
				timeMap.put(m.getTime(), m.getMedicineName());
			}
		}
	}
	
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3)
	{
		//TODO: I think this is for the old ListView, with header included...
//		if (position == 0) 
//		{
//			return;
//		}
		
		pressedMedicine = position;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		PlanMedicineListAdapter a = (PlanMedicineListAdapter)listView.getAdapter();
		String displayedText = a.getItem(pressedMedicine).split("!")[0] + " " + a.getItem(pressedMedicine).split("!")[1];
		
		builder.setMessage(displayedText)
		       .setCancelable(false)
		       .setPositiveButton("Angre", new DialogInterface.OnClickListener() {

		    	   public void onClick(DialogInterface dialog, int which) {
					
				}
			})
		       .setNegativeButton("Slett", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
					PlanMedicineListAdapter a = (PlanMedicineListAdapter)listView.getAdapter();
					
					String combinedString = a.getItem(pressedMedicine);
					String time = getTime(combinedString);
					String medicineName = getMedicineName(combinedString);
					AvailableMedicines am = new AvailableMedicines();
					int medicine_id = am.getMedicineByName(medicineName);
					DeleteMedicineModel model = new DeleteMedicineModel(childIdService.getChildId(), medicine_id, time, HealthState.getIdByHealthZone(healthZone));
					Log.d("Deleting medicine", model.toString());
					
					DeleteMedicineFromPlanPoster poster = new DeleteMedicineFromPlanPoster(model.toString());
					poster.execute();
					try {
						poster.get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}

					redrawList();
				}
			});
		AlertDialog alert = builder.create();
		alert.show();
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		redrawList();	
	}
	private void redrawList()
	{
		listView = (ListView) findViewById(R.id.medicine_listview);
		intializeList();
		listView.setAdapter(new PlanMedicineListAdapter(timeMap));
	}
	private String getMedicineName(String combined)
	{
		return combined.split("!")[1];
	}
	private String getTime(String combined)
	{
		return combined.split("!")[0];
	}
	
	private class PlanMedicineListAdapter extends BaseAdapter
	{
		
		private HashMap<String, String> medicines;
		private String[] times;
		private String[] medicineNames;
		
		
		
		public PlanMedicineListAdapter(HashMap<String, String> medicines)
		{
			this.medicines = medicines;
			initArrays();
		}

		private void initArrays()
		{
			times = new String[medicines.size()];
			medicineNames = new String[medicines.size()];
			int i = 0;
			for (String key : medicines.keySet())
			{
				times[i] = key;
				medicineNames[i] = medicines.get(key);
				i++;
			}
		}
		
		public int getCount()
		{
			return medicines.size();
		}

		public String getItem(int position)
		{
			
			return times[position] + "!" + medicineNames[position];
		}
		public long getItemId(int position)
		{
			
			return 0;
		}
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (view == null)
			{
				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.plan_medicine_list_item, null);
				TextView nameTextView = (TextView) view
						.findViewById(R.id.medicine_name_textview);
				TextView timeTextView = (TextView) view
						.findViewById(R.id.time_to_take_textview);
				nameTextView.setText(medicineNames[position]);
				timeTextView.setText(times[position]);


			} else
			{
				view = convertView;
			}

			return view;
		}
		/**
		 * A list-item for medicationsplans holds an icon and a name (Syk, Litt syk, Frisk). This is a helper for the adapter. 
		 */
	}
	private class PlanViewHolder
	{
		private Bitmap image;
		private String planName;
		public PlanViewHolder(int healthState)
		{
			switch (healthState) {
				case 1:
					this.image = BitmapFactory.decodeResource(getResources(),
							R.drawable.smiley);
					this.planName = "Frisk";
					break;
				case 2:
					this.image = BitmapFactory.decodeResource(getResources(),
							R.drawable.mellowie);
					this.planName = "Litt syk";
					break;
				case 3:
					this.image = BitmapFactory.decodeResource(getResources(),
							R.drawable.sadie);
					this.planName = "Syk";
					break;
				default:
					this.image = BitmapFactory.decodeResource(getResources(),
							R.drawable.sadie);
					this.planName = "Frisk";
					Log.e(TAG,"Default health state was chosen. Healthstate = " + healthState);
					
					break;	
			}
			
		}
		public Bitmap getImage()
		{
			return this.image;
		}
		public String getPlanName(){
			return this.planName;
		}	
	}	
}