package com.blopp.bloppasthma.utils;

import java.io.ByteArrayOutputStream;

import com.google.gson.Gson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class TemporarilyImageStore
{
	private static final String sharedPreferenceName = "Image";
	private static final String TAG = TemporarilyImageStore.class.getSimpleName();
	private Context context;
	public TemporarilyImageStore(Context context)
	{
		this.context = context;
	}
	
	public void storeTemporarily(ByteImage image)
	{
		Gson gson = new Gson();
		String json = gson.toJson(image);
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Image", json).commit();
	}
	
	public void removeTemporarilyImage()
	{
		context.getSharedPreferences(sharedPreferenceName, 0).edit().putString("Image", "").commit();
	}
	
	public ByteImage getByteImage()
	{
		Gson gson = new Gson();
		String json = context.getSharedPreferences(sharedPreferenceName, 0).getString("Image", "");
		if(json.isEmpty())
		{
			Log.d(TAG, "Json was empty, returning empty image");
			return null;
		}
		return gson.fromJson(json, ByteImage.class);
	}
	
	public boolean hasStoredAnImage()
	{
		Gson gson = new Gson();
		String json = context.getSharedPreferences(sharedPreferenceName, 0).getString("Image", "");
		if(json.isEmpty())
		{
			Log.d(TAG, "Json was empty, returning empty image");
			return false;
		}
		return true;
	}
	
}
