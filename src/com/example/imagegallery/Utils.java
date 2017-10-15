package com.example.imagegallery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Utils {

	Context context;
	public ArrayList<File> fileList = new ArrayList<File>();
	
	public Utils(Context context){
		this.context = context;
	}
	
	public ArrayList<File> getfile(File dir) {
		File listFile[] = dir.listFiles();
		if (listFile != null && listFile.length > 0) {
			for (int i = 0; i < listFile.length; i++) {

				if (listFile[i].isDirectory()) {
					fileList.add(listFile[i]);
					getfile(listFile[i]);

				} else {
					if (listFile[i].getName().endsWith(".png")
							|| listFile[i].getName().endsWith(".jpg")
							|| listFile[i].getName().endsWith(".jpeg")
							|| listFile[i].getName().endsWith(".gif"))

					{
						fileList.add(listFile[i]);
					}
				}

			}
		}
		return fileList;
	}
	public ArrayList<String> getFilePaths(){
		ArrayList<String> filePaths = new ArrayList<>();
		
		File directory = Environment.getExternalStorageDirectory();
		
		
		
		ArrayList<File> fList = getfile(directory);
			
			for(int i=0;i<fList.size();i++){
				String path = fList.get(i).getAbsolutePath();
				
				if(isSupported(path)){
					
					filePaths.add(path);
					
				}
				
			}
		
		return filePaths;
		
	}

	public boolean isSupported(String path) {
		// TODO Auto-generated method stub
		
		String extn = path.substring(path.lastIndexOf('.')+1, path.length());
		
		if(Constants.EXTNS.contains(extn.toLowerCase(Locale.getDefault()))){
			return true;
		}
		else
		return false;
	}
	
	public int getScreenWidth(){
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		/*DisplayMetrics dmDisplayMetrics;
		
		Point p = new Point();
		
		try{
			d.getSize(p);
		}catch(java.lang.NoSuchMethodError e){
			p.x = d.getWidth();
			p.y = d.getHeight();
		}
		columnWidth = p.x;
		
		return columnWidth;*/
		Display d = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		d.getMetrics(metrics);
		// since SDK_INT = 1;
		int widthPixels = metrics.widthPixels;
		//heightPixels = metrics.heightPixels;
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
		try {
		    widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
		    //heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
		} catch (Exception ignored) {
		}
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 17)
		try {
		    Point realSize = new Point();
		    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
		    widthPixels = realSize.x;
		    //heightPixels = realSize.y;
		} catch (Exception ignored) {
		}
		return widthPixels;
	}
	
}
