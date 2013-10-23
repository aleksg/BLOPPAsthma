package com.blopp.bloppasthma.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class GenericListAdapter<T> extends BaseAdapter
{
	protected List<T> items;
	protected Context context;
	public GenericListAdapter(Context context, List<T> items){
		this.context = context;
		this.items = items;
	}
	
	@Override
	public int getCount()
	{
		return items.size();
	}

	@Override
	public Object getItem(int position)
	{	
		return items.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	public abstract View getView(int position, View convertView, ViewGroup parent);
	

}
