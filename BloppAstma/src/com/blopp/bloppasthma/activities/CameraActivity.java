package com.blopp.bloppasthma.activities;

import java.io.ByteArrayOutputStream;

import com.blopp.bloppasthma.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CameraActivity extends Activity implements OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
	private static final String TAG = CameraActivity.class.getSimpleName(); 
    private ImageView imageView;
    private LinearLayout mainLayout;  
    
    // this is an array that holds the IDs of the drawables ...  
    private int[] images = {R.drawable.toalett, R.drawable.toalett, R.drawable.toalett,
                 R.drawable.toalett, R.drawable.toalett, R.drawable.toalett, R.drawable.toalett};  
      
    private View cell; 
    
    private Button photoButton, savereturnButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camerapreview);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        
        mainLayout = (LinearLayout) findViewById(R.id._linearLayout); 


        setUpListeners();
        insertPicturesinList();
    }

    private void insertPicturesinList() {
    	for (int i = 0; i < images.length; i++) {  
            
    		Log.d(TAG, "Funker");
            cell = getLayoutInflater().inflate(R.layout.imagecell, null);  
              
            final ImageView imageView = (ImageView) cell.findViewById(R.id.chooserewardimage);  
            imageView.setOnClickListener(new OnClickListener() {  
                         
                       @Override  
                       public void onClick(View v) {  
                            Toast.makeText(CameraActivity.this, 
                            (CharSequence) imageView.getTag(), Toast.LENGTH_SHORT).show();  
                       }  
                  });  
              
            imageView.setTag("Image#"+(i+1));  
            imageView.setImageResource(images[i]);  
              
            mainLayout.addView(cell);  
        } 
		
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
            imageView.setOnClickListener(new OnClickListener() {  
                
                @Override  
                public void onClick(View v) {  
                     //Do something 
                }  
           });  
        }  
    } 
    
    
    public void onClick(View v) {
    	int vid = v.getId();
    	Log.d("CamerActivity", "pressed button: " + v.getId());
    	if	(vid == R.id.photobutton){
    		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
    		startActivityForResult(cameraIntent, CAMERA_REQUEST);
    	} else if (vid == R.id.savereturnbutton) {
    		Intent intent = new Intent();
    		Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		image.compress(Bitmap.CompressFormat.JPEG, 100, out);
    		byte[] imageInByte = out.toByteArray();
    		intent.putExtra("image", imageInByte);
    		finish();
    	}
    	
    }
}