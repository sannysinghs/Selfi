package com.selfi.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.selfi.models.Photo;

public class MJSONHandaler {

	public static Photo getPhtoObjFromJObj(JSONObject object) throws JSONException {
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
		p.setPhoto_desc("no_content");
			
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				p.setPhoto_thumbnail(getBitMapImageFromURL(p.getPhoto_url()));
			}
		}).start();
		
		return p;
	}
	
	
	
	
	public static Bitmap getBitMapImageFromURL(String url){
		return JSONParser.readBitmap(url);
	}

	private static String makeUrl(String farm, String server, String id, String secret) {
		// TODO Auto-generated method stub
		return "http://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
	}
	
}
