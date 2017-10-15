package com.example.imagegallery;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.GridView;

public class GridActivity extends Activity {

	private static int columnWidth;
	Utils utils;
	ArrayList<String> imagePaths;
	GridView gv;
	GridViewAdapter gvadapter;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);
		
		gv = (GridView)findViewById(R.id.gridview);
		utils = new Utils(this);
		 
        imagePaths = new ArrayList<>();
		// Initilizing Grid View
       InitializeGridLayout();
 
        
        // loading all image paths from SD card
        imagePaths = utils.getFilePaths();
 
        // Gridview adapter
        
       // new Initialize().execute();
        gvadapter = new GridViewAdapter(GridActivity.this, imagePaths,
                columnWidth);
 
        // setting grid view adapter
        gv.setAdapter(gvadapter);
       
    }
 
	
	
    private void InitializeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                Constants.PADDING, r.getDisplayMetrics());
 
        columnWidth = (int) ((utils.getScreenWidth() - ((Constants.NUM_COLUMNS + 1) * padding)) / Constants.NUM_COLUMNS);
 
        gv.setNumColumns(Constants.NUM_COLUMNS);
        gv.setColumnWidth(columnWidth);
        gv.setStretchMode(GridView.NO_STRETCH);
        gv.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gv.setHorizontalSpacing((int) padding);
        gv.setVerticalSpacing((int) padding);
    }
		
	
   /* class Initialize extends AsyncTask<Void, Void, Void>{

    	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog = new ProgressDialog(GridActivity.this);
	        progressDialog.setCancelable(true);
	        progressDialog.setMessage("Initializing...");
	        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        progressDialog.show();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if(progressDialog!=null)
				{progressDialog=null;
				progressDialog.dismiss();
				}
			 gvadapter = new GridViewAdapter(GridActivity.this, imagePaths,
		                columnWidth);
		 
		        // setting grid view adapter
		        gv.setAdapter(gvadapter);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			InitializeGridLayout();
			return null;
		}
    	
    }*/
    
	}

	

