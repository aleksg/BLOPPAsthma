package com.blopp.bloppasthma.activities;

import com.blopp.bloppasthma.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity implements OnClickListener {
    private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;
    
    private Button photoButton, savereturnButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camerapreview);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);


        setUpListeners();
    }

    private void setUpListeners() {
    	setUpListenerForButton(savereturnButton, R.id.savereturnbutton);
    	setUpListenerForButton(photoButton, R.id.photobutton);
		
	}

	private void setUpListenerForButton(Button b, int id) {
		b = (Button) findViewById(id);
		if(b == null){
			return;
		}
		b.setOnClickListener(this);
		Log.d("MainMenu", "Set up listener for id:" + id);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            imageView.setImageBitmap(photo);
        }  
    } 
    
    
    public void onClick(View v) {
    	int vid = v.getId();
    	Log.d("CamerActivity", "pressed button: " + v.getId());
    	if	(vid == R.id.photobutton){
    		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
    		startActivityForResult(cameraIntent, CAMERA_REQUEST);
    	} else if (vid == R.id.savereturnbutton) {
    		//TODO: Send image object back to activity
    	}
    	
    }
}