package com.blopp.bloppasthma.jsonmodels;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class InstructionsResult
{
	private String instructions, effect, imageUrl;
	private int id;
	private ArrayList<Bitmap> instructionImages;
	

	public InstructionsResult()
	{
		instructionImages = new ArrayList<Bitmap>();
	}
	
	public ArrayList<Bitmap> getInstructionImages()
	{
		if(instructionImages == null)
		{
			instructionImages = new ArrayList<Bitmap>();
		}
		return instructionImages;
	}

	public InstructionsResult setInstructionImages(ArrayList<Bitmap> instructionImages)
	{
		this.instructionImages = instructionImages;
		return this;
	}
	public String getInstructions()
	{
		return instructions;
	}

	public InstructionsResult setInstructions(String instructions)
	{
		this.instructions = instructions;
		return this;
	}

	public String getEffect()
	{
		return effect;
	}

	public InstructionsResult setEffect(String effect)
	{
		this.effect = effect;
		return this;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public InstructionsResult setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
		return this;
	}

	public int getId()
	{
		return id;
	}

	public InstructionsResult setId(int id)
	{
		this.id = id;
		return this;
	}
}
