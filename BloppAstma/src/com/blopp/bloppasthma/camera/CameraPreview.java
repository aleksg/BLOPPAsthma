package com.blopp.bloppasthma.camera;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback 
{
	private SurfaceHolder mHolder;
	private Camera mCamera;
	
	public CameraPreview(Context context, Camera camera){
		super(context);
		mCamera = camera;
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	public void surfaceCreated(SurfaceHolder holder){
		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch (IOException e) {
			Log.d("TAG","Error setting camera preview: " + e.getMessage());
		}
	}
	
	public void surfaceDestroyed(SurfaceHolder holder){
		//Should be handled in corresponding activity
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
		
		if(mHolder.getSurface() == null){
			return;
		}
		
		
		try {
			mCamera.stopPreview();
		} catch (Exception e){
			Log.d("tag", e.getMessage());
		}
		
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
			
		} catch (Exception e) {
			Log.d("TAG", "Error starting camera preview" + e.getMessage());
		}
	}
	
	
	
	
}
