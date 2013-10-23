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

public class AirQualityAdapter extends GenericListAdapter<AirQualityState>
{
	private static final String TAG = AirQualityAdapter.class.getSimpleName();
	
	public AirQualityAdapter(Context context, 
			ArrayList<AirQualityState> airQualityStates)
	{
		super(context, airQualityStates);
	}
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater airQualityInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View listItem;
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
			
			TextView airQualityLongCommentView = (TextView) listItem
					.findViewById(R.id.air_quality_long_comment_textView);
			airQualityLongCommentView.setText(state.getLongDescription());
			airQualityLongCommentView.setTextColor(Color.BLACK);
			airQualityLongCommentView.setPadding(0, 0, 0, 0);

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
