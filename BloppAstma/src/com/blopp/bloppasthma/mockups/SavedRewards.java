package com.blopp.bloppasthma.mockups;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

public class SavedRewards
{
	private static final String TAG = SavedRewards.class.getSimpleName();
	private static RewardList rewards;
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
			rewards = new RewardList();
		}else
		{
			rewards = getSavedRewards();
		}
	}
	
	public void saveReward(Reward reward)
	{
		Gson gson = new Gson();
		rewards.add(reward);
		String json = gson.toJson(rewards);
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
			return rewards;
		}
		rewards = gson.fromJson(json, RewardList.class);
		return rewards;
	}
	
	public void orderReward(Reward r)
	{
		Gson gson = new Gson();
		Reward old = getSavedRewards().findById(r.getId());
		Log.d(TAG, "User ordered reward with id: " + r.getId());
		rewards.storeOrderedReward(old);
		String json = gson.toJson(rewards);
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Rewards", json).commit();
		Log.d("Altered", context.getSharedPreferences(sharedPreferenceName, 0).getAll().toString());
	}
	
	/*
	 * TODO: Move to RewardList
	 */
	public int getMaximumIdentifier(Context context)
	{
		RewardList stored = getSavedRewards();
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

	
	public Context getContext()
	{
		return context;
	}
	
}
