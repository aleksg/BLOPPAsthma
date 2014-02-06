package com.blopp.bloppasthma.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.TextView;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.models.Medicine;

public class MedicineListAdapter extends GenericListAdapter<Medicine>
{
	public MedicineListAdapter(Context context, Medicine[] medicines){
		super(context, convertToList(medicines));
	}
	private static List<Medicine> convertToList(Medicine[] medicines)
	{
		List<Medicine> meds = new ArrayList<Medicine>();
		for (Medicine medicine : medicines)
		{
			meds.add(medicine);
		}
		return meds;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View medicineListView;

		if (convertView == null)
		{

			medicineListView = new View(context);

			medicineListView = inflater.inflate(R.layout.medicine_list_item, parent,
					false);

			ImageView medicineIconView = (ImageView) medicineListView
					.findViewById(R.id.medicine_imageView);
			Medicine medicine = (Medicine)getItem(position);
			medicineIconView.setImageBitmap(getLargeMedicineImage(medicine.getId()));
			
			medicineIconView.setPadding(10, 5, 0, 0);
			TextView medicineNameView = (TextView) medicineListView
					.findViewById(R.id.medicine_name_textView);
			medicineNameView.setText(medicine.getName());
			medicineNameView.setTextSize(36f);
			medicineNameView.setTextColor(Color.BLACK);
			medicineNameView.setPadding(10,15,0,0);
			
			TextView medicineDescriptionView = (TextView) medicineListView
					.findViewById(R.id.medicine_short_desc_textView);
			medicineDescriptionView.setText("");
			medicineDescriptionView.setTextColor(Color.BLACK);
			medicineDescriptionView.setPadding(10,5,0,0);

		} else
		{
			medicineListView = convertView;
		}
		return medicineListView;
	}
	
	private Bitmap getLargeMedicineImage(int id) {
		
		switch (id) {
		case 1:
			return BitmapFactory.decodeResource(context.getResources(), R.drawable.orange_medicine_big);
		case 2:
			return BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_medicine_big);
		case 3:
			return BitmapFactory.decodeResource(context.getResources(), R.drawable.purple_medicine_big);
		default:
			break;
		}
		
		return null;
	}
}
