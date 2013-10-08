package com.blopp.bloppasthma.mockups;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

public class SavedRewards
{
	private static final String TAG = SavedRewards.class.getSimpleName();
	private static RewardList rewards;
	private static String sharedPreferenceName = "RewardList";
	
	public SavedRewards(Context context)
	{
		createRewardList(context);
	}
	private void createRewardList(Context context)
	{
		if(getSavedRewards(context) == null)
		{
			rewards = new RewardList();
		}else
		{
			rewards = getSavedRewards(context);
		}
	}
	
	public void saveReward(Context context, Reward reward)
	{
		Gson gson = new Gson();
		rewards.add(reward);
		String json = gson.toJson(rewards);
		
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Rewards", json)
					.commit();
		Log.d(TAG, context.getSharedPreferences(sharedPreferenceName, 0).getAll().toString());
	}
	
	public RewardList getSavedRewards(Context context)
	{
		Gson gson = new Gson(); 
		String json = context.getSharedPreferences(sharedPreferenceName, 0).getString("Rewards", "");
		Log.d(TAG, json);
		if(json.isEmpty())
		{
			return rewards;
		}
		rewards = gson.fromJson(json, RewardList.class);
		return rewards;
	}
	
	
	public int getMaximumIdentifier(Context context)
	{
		RewardList stored = getSavedRewards(context);
		int max = -1;
		for (Reward reward : stored.getRewards())
		{
			if(reward.getId() > max)
			{
				max = reward.getId();
			}
		}
		return max;
	}
}
