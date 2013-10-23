package com.blopp.bloppasthma.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.blopp.bloppasthma.R;

public class StarsAdapter extends BaseAdapter
{
	private Context context;
	private int size;
	private int reward;
	public StarsAdapter(Context context, int columnWidth, int reward)
	{
		this.context = context;
		this.size = columnWidth;
		this.reward = reward;
	}

	public int getCount()
	{
		return reward;
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
		// the first square should be a text view with the number of stars
		// while the rest are images of stars
		ImageView imageView;
		if (convertView == null)
		{
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(size,size));
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