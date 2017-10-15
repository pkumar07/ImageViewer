package com.example.imagegallery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

	Context context;
	Activity activity;
	
	private ArrayList<String> filePaths;
	private int imageWidth;
	
	public GridViewAdapter(Activity activity, ArrayList<String> filePaths, int imageWidth){
		//this.context = context;
		this.filePaths = filePaths;
		this.imageWidth = imageWidth;
		this.activity=activity;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.filePaths.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.filePaths.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if(convertView==null){
			imageView = new ImageView(activity);
		}
		else
			imageView = (ImageView)convertView;
		
		 Bitmap image = decodeFile(filePaths.get(position), imageWidth,
	                imageWidth);
	 
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        imageView.setLayoutParams(new GridView.LayoutParams(imageWidth,
	                imageWidth));
	        imageView.setImageBitmap(image);
	 
	        // image view click listener
	        imageView.setOnClickListener(new OnImageClickListener(position));
	 
	        return imageView;
	}

	class OnImageClickListener implements OnClickListener{
		int position;
		public OnImageClickListener(int position){
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent(activity,FullScreen.class);
			i.putExtra("position", position);
			i.putStringArrayListExtra("list", filePaths);
			activity.startActivity(i);
		}
		
		
	}
	
	private Bitmap decodeFile(String filePath, int imageWidth2, int imageWidth3) {
		// TODO Auto-generated method stub
		try {
			 
            File f = new File(filePath);
 
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
 
            final int REQUIRED_WIDTH = imageWidth2;
            final int REQUIRED_HIGHT = imageWidth3;
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
                    && o.outHeight / scale / 2 >= REQUIRED_HIGHT)
                scale *= 2;
 
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
		
		
	}

}
