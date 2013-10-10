package com.blopp.bloppasthma.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ByteImage
	{
		private byte[] bytes;
		
		public ByteImage(){}
		
		public ByteImage setBytes(byte[] bytes){
			this.bytes = bytes;
			return this;
		}
		public byte[] getBytes()
		{
			return this.bytes;
		}
		public ByteImage setBytes(Bitmap bitmap)
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			this.bytes = out.toByteArray();
			return this;
		}
		public Bitmap getImageAsBitmap()
		{
			return BitmapFactory.decodeByteArray(bytes, 0, this.bytes.length);
		}
		
	}