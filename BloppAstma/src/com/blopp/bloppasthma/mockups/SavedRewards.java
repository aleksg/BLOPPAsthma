package com.blopp.bloppasthma.mockups;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

public class SavedRewards
{
	private static final String TAG = SavedRewards.class.getSimpleName();
	private static RewardList rewardList;
	private static String sharedPreferenceName = "RewardList";
	
	private Context context;
	
	public SavedRewards(Context context)
	{
		this.context = context;
		createRewardList();
	}
	private void createRewardList()
	{
		if(getSavedRewards() == null)
		{
			rewardList = new RewardList();
		}else
		{
			rewardList = getSavedRewards();
		}
	}
	
	public void saveReward(Reward reward)
	{
		Gson gson = new Gson();
		rewardList.add(reward);
		String json = gson.toJson(rewardList);
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Rewards", json)
					.commit();
		Log.d(TAG, context.getSharedPreferences(sharedPreferenceName, 0).getAll().toString());
	}
	
	public RewardList getSavedRewards()
	{
		Gson gson = new Gson(); 
		String json = context.getSharedPreferences(sharedPreferenceName, 0).getString("Rewards", "");
		Log.d(TAG, json);
		if(json.isEmpty())
		{
			return rewardList;
		}
		rewardList = gson.fromJson(json, RewardList.class);
		return rewardList;
	}
	
	public void orderReward(Reward r)
	{
		Gson gson = new Gson();
		Reward old = getSavedRewards().findById(r.getId());
		Log.d(TAG, "User ordered reward with id: " + r.getId());
		rewardList.storeOrderedReward(old);
		String json = gson.toJson(rewardList);
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Rewards", json).commit();
		Log.d("Altered", context.getSharedPreferences(sharedPreferenceName, 0).getAll().toString());
	}
	
	public int getMaximumIdentifier()
	{
		return rewardList.getMaximumIdentifier();
	}
	
	
	public Context getContext()
	{
		return context;
	}
	
}
