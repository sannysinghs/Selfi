package com.selfi.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

import com.selfi.models.Photo;

public class MJSONHandaler {
	
	private MBitmapCache mBitmapCache;
	
	public MJSONHandaler() {
		// TODO Auto-generated constructor stub
		if (mBitmapCache == null) {
//			mBitmapCache = new MBitmapCache();
		}
		
	}
	
	public Photo getPhtoObjFromJObj(JSONObject object) throws JSONException {
		// TODO Auto-generated method stub
		final Photo p = new Photo();
		String id = object.getString("id");
		String farm = object.getString("farm");
		String title = object.getString("title");
		String secret = object.getString("secret");
		String server = object.getString("server");
		
		p.setPhoto_id(id);
		p.setPhoto_title(title);
		p.setPhoto_url(makeUrl(farm,server,id,secret));
		return p;
	}
	
	
	
	
	private  Bitmap getBitMapImageFromURL(String url){
		return JSONParser.readBitmap(url);
	}

	private String makeUrl(String farm, String server, String id, String secret) {
		// TODO Auto-generated method stub
		return "http://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
	}
	
}
