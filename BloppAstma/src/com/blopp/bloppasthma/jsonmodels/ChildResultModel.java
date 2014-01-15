package com.blopp.bloppasthma.jsonmodels;

public class ChildResultModel
{
	private int id;
	private String name;
	private int persNum;
	private int medicalPlanId;
	private int avatarId;
	private int credits;
	private double locationLatitude;
	private double locationAltitude;

	public ChildResultModel() {}

	public int getId() {
		return id;
	}

	public ChildResultModel setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public ChildResultModel setName(String name) {
		this.name = name;
		return this;
	}

	public int getPersNum() {
		return persNum;
	}

	public ChildResultModel setPersNum(int persNum) {
		this.persNum = persNum;
		return this;
	}

	public int getMedicalPlanId() {
		return medicalPlanId;
	}

	public ChildResultModel setMedicalPlanId(int medicalPlanId) {
		this.medicalPlanId = medicalPlanId;
		return this;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public ChildResultModel setAvatarId(int avatarId) {
		this.avatarId = avatarId;
		return this;
	}

	public int getCredits() {
		return credits;
	}

	public ChildResultModel setCredits(int credits) {
		this.credits = credits;
		return this;
	}

	public double getLocationLatitude() {
		return locationLatitude;
	}

	public ChildResultModel setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
		return this;
	}

	public double getLocationAltitude() {
		return locationAltitude;
	}

	public ChildResultModel setLocationAltitude(double locationAltitude) {
		this.locationAltitude = locationAltitude;
		return this;
	}
}
