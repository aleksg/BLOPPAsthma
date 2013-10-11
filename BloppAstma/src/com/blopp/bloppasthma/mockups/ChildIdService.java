package com.blopp.bloppasthma.mockups;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ChildIdService
{
	private static final String TAG = ChildIdService.class.getSimpleName();
	private static final String sharedPreferenceName = "ChildId";

	private Context context;

	public ChildIdService(Context context)
	{
		this.context = context;
	}

	public void saveChildId(int childId)
	{
		getMySharedPreferences().edit().putInt("ChildId", childId).commit();
	}
	public int getChildId()
	{
		return getMySharedPreferences().getInt("ChildId", -1);
	}
	public boolean hasChildId()
	{
		return getMySharedPreferences().contains("ChildId");
	}
	public SharedPreferences getMySharedPreferences()
	{
		return context.getSharedPreferences(sharedPreferenceName, 0);
	}
}
