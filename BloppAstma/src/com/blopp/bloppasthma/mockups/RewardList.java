package com.blopp.bloppasthma.mockups;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class RewardList
{
	private List<Reward> rewards;
	
	public RewardList()
	{
		this.rewards = new ArrayList<Reward>();
	}
	public List<Reward> getRewards(){
		return this.rewards;
	}
	public RewardList setRewards(List<Reward> rewards)
	{
		this.rewards = rewards;
		return this;
	}
	public void add(Reward reward)
	{
		this.rewards.add(reward);
	}
	
	public Reward storeOrderedReward(Reward alteredReward)
	{
		this.rewards.remove(findById(alteredReward.getId()));
		alteredReward.setOrdered(true);
		this.rewards.add(alteredReward);
		return alteredReward;
	}
	public void replace(Reward oldReward, Reward newReward){
		this.rewards.remove(oldReward);
		this.rewards.add(newReward);
	}
	public Reward findById(int id) throws IllegalArgumentException 
	{
		List<Reward> stored = getRewards();
		
		for (Reward reward : stored)
		{
			if(reward.getId() == id)
			{
				return reward;
			}
		}
		throw new IllegalArgumentException("Id for reward does not exist");
	}
	public int getMaximumIdentifier()
	{	
		int max = -1;
		for (Reward reward : rewards)
		{
			if(reward.getId() > max)
			{
				max = reward.getId();
			}
		}
		return max;
	}
}
