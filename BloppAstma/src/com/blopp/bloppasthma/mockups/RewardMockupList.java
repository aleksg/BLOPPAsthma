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
				.setDescription("Fotballkamp").setReceived(false));
		this.rewards.add(new Reward().setStars(100)
				.setDescription("Tivoli").setReceived(false));
		this.rewards.add(new Reward().setStars(25)
				.setDescription("Pirbadet").setReceived(false));
		this.rewards.add(new Reward().setStars(10)
				.setDescription("Iskrem").setReceived(false));
		this.rewards.add(new Reward().setStars(5)
				.setDescription("50 kroner i ukepenger").setReceived(false));
		this.rewards.add(new Reward().setStars(1)
				.setDescription("Gudstjeneste").setReceived(false));
	}

	public List<Reward> getRewards()
	{
		return this.rewards;
	}
}
