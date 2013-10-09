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
		rewardList.add(reward);
		commitRewards();
	}
	
	public RewardList getSavedRewards()
	{
		Gson gson = new Gson(); 
		String json = context.getSharedPreferences(sharedPreferenceName, 0).getString("Rewards", "");
		if(json.isEmpty())
		{
			return rewardList;
		}
		rewardList = gson.fromJson(json, RewardList.class);
		return rewardList;
	}
	
	public void orderReward(Reward r)
	{
		Log.d(TAG, "Child ordered reward with id: " + r.getId());
		rewardList.storeOrderedReward(r);
		commitRewards();

	}
	public void deleteReward(Reward r)
	{
		Log.d(TAG, "Parent deleted reward with id: " + r.getId());
		rewardList.remove(r);	
		commitRewards();
	}
	public void commitRewards()
	{
		Gson gson = new Gson();
		String json = gson.toJson(rewardList);
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Rewards", json).commit();
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
