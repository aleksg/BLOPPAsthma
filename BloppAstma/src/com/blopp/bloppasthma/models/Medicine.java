package com.blopp.bloppasthma.models;

import android.graphics.Bitmap;

//Initial testing purposes for the log.
public class Medicine implements java.io.Serializable
{
	private int id;
	private String name;
	private int instructionsId;
	private int imgId;
	private String color;
	private Bitmap bitmap;

	public Medicine(String name, int instructionsId, int imgId)
	{
		this.instructionsId = instructionsId;
		this.name = name;
		this.setImgId(imgId);
	}

	public Medicine(String name, int instructionsId, int imgId, String color)
	{
		super();
		this.name = name;
		this.instructionsId = instructionsId;
		this.imgId = imgId;
		this.color = color;
	}

	public Medicine(String name, int instructionsId)
	{
		this.instructionsId = instructionsId;
		this.name = name;
	}

	public Medicine(String name)
	{
		this.name = name;
	}

	public Medicine(int id, String medicine, String color,
			Bitmap medColorToBitmap)
	{
		this.id = id;
		this.name = medicine;
		this.color = color;
		this.bitmap = medColorToBitmap;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		if (name.isEmpty())
		{
			throw new IllegalArgumentException("The name cannot be null");

		}
		this.name = name;
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
	}

	public int getId()
	{
		return this.id;
	}

	public int getInstructionsId()
	{
		return instructionsId;
	}

	public void setInstructionsId(int instructionsId)
	{
		if (instructionsId < 0)
			throw new IllegalArgumentException(
					"instructionsId must be bigger than null");

		this.instructionsId = instructionsId;
	}

	public int getImgId()
	{
		return imgId;
	}

	public void setImgId(int imgId)
	{
		this.imgId = imgId;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

}
