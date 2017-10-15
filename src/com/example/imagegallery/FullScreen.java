package com.example.imagegallery;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class FullScreen extends Activity {
ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullscreen);
		img = (ImageView)findViewById(R.id.imageView1);
		
		Intent i = getIntent();
		
		int position = i.getIntExtra("position", 0);
		
		ArrayList<String> filePaths = i.getStringArrayListExtra("list");
		
		File imageFile = new File(filePaths.get(position));
		
		if(imageFile.exists()){
			Log.d("File exists", "True");
			Bitmap image = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			img.setImageBitmap(image);
			
		}
		else
			Log.d("File exists", "False");
		
	}

}
