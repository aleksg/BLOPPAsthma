package com.blopp.bloppasthma.adapters;

import java.util.ArrayList;
import java.util.List;

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
	private static final String TAG = AirQualityAdapter.class.getSimpleName();

	private Context context;
	
	private List<AirQualityState> airQualityStateArray;
	
	public AirQualityAdapter(Context context, 
			ArrayList<AirQualityState> airQualityStates)
	{
		this.context = context;
		this.airQualityStateArray = airQualityStates;
		
	}
	
	public int getCount() 
	{
		return airQualityStateArray.size();
	}
	
	public Object getItem(int position)
	{
		return airQualityStateArray.get(position);
	}
	
	public long getItemId(int position)
	{
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater airQualityInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View listItem;
		Log.d(TAG, "Iam in getView at "+TAG);
		if (convertView == null)
		{
			listItem = new View(context);
			listItem = airQualityInflater.inflate(R.layout.air_quality_list_item, parent, 
					false);
			listItem.setPadding(0, 0, 0, 0);
			
			AirQualityState state = (AirQualityState)getItem(position);
			
			TextView airQualityView = (TextView) listItem
					.findViewById(R.id.air_quality_comment_textView);
			airQualityView.setText(state.getShortDescription());
			airQualityView.setTextColor(Color.BLACK);
			airQualityView.setPadding(0, 0, 0, 0);

			ImageView airQualityImageView = (ImageView) listItem.findViewById(R.id.air_quality_imageView);
			airQualityImageView
				.setImageResource(getImageResourceForAirQualityState(state
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
