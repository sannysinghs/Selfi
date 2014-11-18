package com.selfi.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

import com.selfi.fragments.FPhoto;
import com.selfi.models.Photo;
import com.selfi.models.PhotoDetail;

public class MJSONHandaler {
	
	private MBitmapCache mBitmapCache;
	
	public MJSONHandaler() {
		// TODO Auto-generated constructor stub
		if (mBitmapCache == null) {
//			mBitmapCache = new MBitmapCache();
		}
		
	}
	
	public ArrayList<Photo> FetchPhotos(JSONObject res) {
		// TODO Auto-generated method stub
		
		JSONObject jsonObject = null;
		ArrayList<Photo> photos = new ArrayList<>();
				
		try {
			jsonObject = new JSONObject(res.getString("photos"));
			JSONArray jPhotoArray = jsonObject.getJSONArray("photo");
			for (int i = 0; i < jPhotoArray.length(); i++) {
			  JSONObject jPhoto = jPhotoArray.getJSONObject(i);
			  Photo p =	this.getPhtoObjFromJObj(jPhoto);
			  MConnectionHelper.RetrievePhotoDetail(p);
			  photos.add(p);
			  
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e("JObj", jsonObject.toString());
		}
		FPhoto.changeFetchStatus();
		return photos;
	}	
	
	
	public Photo getPhtoObjFromJObj(JSONObject object) throws JSONException {
		// TODO Auto-generated method stub
		Photo p = new Photo();
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
	
//	private  Bitmap getBitMapImageFromURL(String url){
//		return JSONParser.readBitmap(url);
//	}

	private String makeUrl(String farm, String server, String id, String secret) {
		// TODO Auto-generated method stub
		return "http://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
	}

	public static PhotoDetail getPhotoDetailFromJSONObj(JSONObject object) throws JSONException {
		// TODO Auto-generated method stub
		PhotoDetail pDetail = new PhotoDetail();
		
		pDetail.setPhoto_desc( object.getJSONObject("description").getString("_content"));
		pDetail.setPhoto_date(object.getString("dateuploaded"));
		
//		pDetail.setPhoto_owner() object.getJSONObject("owner");
		return pDetail;

	}	
	
}
