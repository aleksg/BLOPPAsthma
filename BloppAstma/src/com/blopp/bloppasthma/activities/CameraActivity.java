package com.blopp.bloppasthma.activities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.blopp.bloppasthma.R;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CameraActivity extends Activity {
	
		private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
		private Uri fileUri;
		private static final int MEDIA_TYPE_IMAGE = 1;
		
	  	

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.camerapreview);
	        
	        
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        
	        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	        
	        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	        
	    }
	    
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	            if (resultCode == RESULT_OK) {
	                // Image captured and saved to fileUri specified in the Intent
	                Toast.makeText(this, "Image saved to:\n" +
	                         data.getData(), Toast.LENGTH_LONG).show();
	            } else if (resultCode == RESULT_CANCELED) {
	                // User cancelled the image capture
	            } else {
	                // Image capture failed, advise user
	            }
	        }
	    }
	    
	    private static Uri getOutputMediaFileUri(int type){
	        return Uri.fromFile(getOutputMediaFile(type));
	    }
	    
	    private static File getOutputMediaFile(int type){
	        // To be safe, you should check that the SDCard is mounted
	        // using Environment.getExternalStorageState() before doing this.

	        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	                  Environment.DIRECTORY_PICTURES), "MyCameraApp");
	        // This location works best if you want the created images to be shared
	        // between applications and persist after your app has been uninstalled.

	        // Create the storage directory if it does not exist
	        if (! mediaStorageDir.exists()){
	            if (! mediaStorageDir.mkdirs()){
	                Log.d("MyCameraApp", "failed to create directory");
	                return null;
	            }
	        }

	        // Create a media file name
	        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        File mediaFile;
	        if (type == MEDIA_TYPE_IMAGE){
	            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	            "IMG_"+ timeStamp + ".jpg");
	        }  else {
	            return null;
	        }

	        return mediaFile;
	    }
}
