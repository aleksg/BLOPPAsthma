package com.blopp.bloppasthma.mockups;

import java.util.ArrayList;
import java.util.List;

public class RewardMockupList
{
	private List<Reward> rewards;

	public RewardMockupList()
	{
		this.rewards = new ArrayList<Reward>();
		seed();
	}
	
	public void seed()
	{
		this.rewards.add(new Reward().setStars(1000)
				.setDescription("Fotballkamp").setOrdered(false));
		this.rewards.add(new Reward().setStars(100)
				.setDescription("Tivoli").setOrdered(false));
		this.rewards.add(new Reward().setStars(25)
				.setDescription("Pirbadet").setOrdered(false));
		this.rewards.add(new Reward().setStars(10)
				.setDescription("Iskrem").setOrdered(false));
		this.rewards.add(new Reward().setStars(5)
				.setDescription("50 kroner i ukepenger").setOrdered(false));
		this.rewards.add(new Reward().setStars(1)
				.setDescription("Gudstjeneste").setOrdered(false));
	}

	public List<Reward> getRewards()
	{
		return this.rewards;
	}
}
