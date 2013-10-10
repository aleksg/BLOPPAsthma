package com.blopp.bloppasthma.adapters;

import java.util.ArrayList;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.models.AirQualityState;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AirQualityAdapter extends BaseAdapter
{
	private static final String TAG = "AirQualityAdapter";

	private Context context;
	
	private AirQualityState[] airQualityStateArray;
	
	public AirQualityAdapter(Context context, 
			ArrayList<AirQualityState> airQualitStates)
	{
		this.context = context;
		this.airQualityStateArray = new AirQualityState[airQualitStates.size()];
		airQualitStates.toArray(airQualityStateArray);
	}
	
	public int getCount() 
	{
		return airQualityStateArray.length;
	}
	
	public Object getItem(int arg0)
	{
		return airQualityStateArray[arg0];
	}
	
	public long getItemId(int position)
	{
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View listItem;
		
		if (convertView == null)
		{
			listItem = new View(context);
			listItem = inflater.inflate(R.layout.air_quality_list_item, parent, 
					false);
			listItem.setPadding(0, 10, 0, 10);
			TextView airQualityView = (TextView) listItem
					.findViewById(R.id.air_quality_comment_textView);
			airQualityView.setText(airQualityStateArray[position].getDescription());
			airQualityView.setTextColor(Color.BLACK);
			airQualityView.setPadding(0, 0, 0, 5);
			
			Log.d(TAG, "Dette blir kjørt");
			ImageView airQualityImageView = (ImageView) listItem.findViewById(R.id.air_quality_imageView);
			airQualityImageView
				.setImageResource(getImageResourceForAirQualityState(airQualityStateArray[position]
						.getAQI()));
		} else
		{
			listItem = convertView;
		}
		return listItem;
	}
	
	public int getImageResourceForAirQualityState(int airQuality) 
	{
		switch(airQuality) {
		case 0:
			return R.drawable.air_quality_no_data;
		case 1:
			return R.drawable.air_quality_fine;
		case 2:
			return R.drawable.air_quality_moderat;
		case 3:
			return R.drawable.air_quality_kraftig;
		case 4: 
			return R.drawable.air_quality_extreme;
		default:
			return R.drawable.air_quality_no_data;
		}
		
	}
	
}
