package com.blopp.bloppasthma.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.models.PollenState;

public class PollenDistributionAdapter extends GenericListAdapter<PollenState>
{

	public PollenDistributionAdapter(Context context, List<PollenState> items)
	{
		super(context, items);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pollenDistribution
	 * @return Resource Id of a pollen state icon
	 */
	public int getImageResourceIdForPollenState(int pollenDistribution)
	{
		switch (pollenDistribution) {
		case 0:
			return R.drawable.pollen_none;
		case 1:
			return R.drawable.pollen_beskjeden;
		case 2:
			return R.drawable.pollen_moderat;
		case 3:
			return R.drawable.pollen_kraftig;
		case 4:
			return R.drawable.pollen_extreme;
		default:
			return R.drawable.pollen_beskjeden;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
	
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
			View listItem;
	
			if (convertView == null)
			{
	
				listItem = new View(context);
				listItem= inflater.inflate(R.layout.pollen_list_item, parent,
						false);
				
				listItem.setPadding(0, 5, 0, 0);
				PollenState state = (PollenState)getItem(position);
				TextView pollenTypeView = (TextView) listItem
						.findViewById(R.id.pollen_type_textView);
				pollenTypeView.setText(state.getPollenName());
				pollenTypeView.setTextColor(Color.BLACK);
				pollenTypeView.setPadding(5, 0, 0, 0);
				// Uses an icon matching pollenvarslingen.no's traditional icons for
				// pollen spread
				ImageView pollenDistributionView = (ImageView) listItem
						.findViewById(R.id.pollen_spread_imageView);
				pollenDistributionView
						.setImageResource(getImageResourceIdForPollenState(state
								.getDistribution()));
	
			} else
			{
				listItem = convertView;
			}
			return listItem;
	}

}
