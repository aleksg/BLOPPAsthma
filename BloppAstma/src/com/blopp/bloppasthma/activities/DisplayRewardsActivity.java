package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.ChildIdService;
import com.blopp.bloppasthma.models.ChildRewards;

public class DisplayRewardsActivity extends Activity
{
	GridView gridView;
	TextView countView;
	
	private ChildIdService childIdService;
	private ChildRewards childRewards;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.rewardview);
		
		gridView = (GridView) findViewById(R.id.rewardsView);
		countView = (TextView) findViewById(R.id.displayRewardsTextView);
		
		childIdService = new ChildIdService(getApplicationContext());
		childRewards = new ChildRewards(childIdService.getChildId());
		childRewards.initChildModelParser();
		
		gridView.setAdapter(new StarAdapter(this));
		countView.setText(Integer.toString(childRewards.getCredits()));
	}
	
	public class StarAdapter extends BaseAdapter
	{
		private Context context;
		
		public StarAdapter(Context context)
		{
			this.context = context;
		}

		public int getCount()
		{
			return childRewards.getCredits() + 1;
		}

		public Object getItem(int position)
		{
			return position;
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView imageView;
			if (convertView == null)
			{
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(64,64));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(0,0,0,0);
			}
			else
			{
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(R.drawable.star);
			return imageView;
		}
	}
}
