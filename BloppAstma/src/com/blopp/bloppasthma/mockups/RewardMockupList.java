package com.blopp.bloppasthma.mockups;

import java.util.ArrayList;
import java.util.List;

public class RewardMockupList
{
	private List<RewardMockup> rewards;

	public RewardMockupList()
	{
		this.rewards = new ArrayList<RewardMockup>();
		seed();
	}

	public void seed()
	{
		this.rewards.add(new RewardMockup().setStars(1000)
				.setDescription("Fotballkamp").setReceived(false));
		this.rewards.add(new RewardMockup().setStars(100)
				.setDescription("Tivoli").setReceived(false));
		this.rewards.add(new RewardMockup().setStars(25)
				.setDescription("Pirbadet").setReceived(false));
		this.rewards.add(new RewardMockup().setStars(10)
				.setDescription("Iskrem").setReceived(false));
		this.rewards.add(new RewardMockup().setStars(5)
				.setDescription("50 kroner i ukepenger").setReceived(false));
		this.rewards.add(new RewardMockup().setStars(1)
				.setDescription("Gudstjeneste").setReceived(false));
	}

	public List<RewardMockup> getRewards()
	{
		return this.rewards;
	}
}
