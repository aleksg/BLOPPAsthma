package com.blopp.bloppasthma.mockups;

import java.util.ArrayList;
import java.util.List;

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
}
