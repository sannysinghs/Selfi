package com.selfi.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.toolbox.NetworkImageView;
import com.selfi.fragments.FPhoto;
import com.selfi.models.Photo;
import com.selfi.models.PhotoDetail;
import com.selfi.models.PhotoOwner;

public class MJSONHandaler {
	
	private MBitmapCache mBitmapCache;
	
	public MJSONHandaler() {
		if (mBitmapCache == null) {
//			mBitmapCache = new MBitmapCache();
		}
		
	}
	
	public ArrayList<Photo> FetchPhotos(JSONObject res) {
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
		p.setPhoto_url(makePhotoUrl(farm,server,id,secret));
		return p;
	}
	
	
	private  Bitmap getBitMapImageFromURL(String url){
		return JSONParser.readBitmap(url);
	}

	private String makePhotoUrl(String farm, String server, String id, String secret) {
		return "http://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
	}

	private static String makeOwnerUrl(String server, String farm ,String owner_id) {
		return"http://farm"+farm+".staticflickr.com/"+server+"/buddyicons/"+owner_id+"_q.jpg";
	}	
	
	public static PhotoDetail getPhotoDetailFromJSONObj(JSONObject object) throws JSONException {
		PhotoDetail pDetail = new PhotoDetail();
		pDetail.setPhoto_desc( object.getJSONObject("description").getString("_content"));
		pDetail.setPhoto_comment_count(object.getJSONObject("comments").getString("_content"));
		pDetail.setPhoto_views(object.getString("views"));
		pDetail.setPhoto_date(object.getString("dateuploaded"));
		
		pDetail.setPhoto_owner( getPhotoOwnerFromJSONObj(object.getJSONObject("owner")) ) ;
		return pDetail;

	}

	private static PhotoOwner getPhotoOwnerFromJSONObj(JSONObject object) throws JSONException {
		PhotoOwner pOwner = new PhotoOwner();
		pOwner.setOwner_id(object.getString("nsid"));
		pOwner.setOwner_name(object.getString("username"));
		pOwner.setOwner_thumbnail(makeOwnerUrl(object.getString("iconserver"), object.getString("iconfarm") , pOwner.getOwner_id() ));
		return pOwner;
	}

	
	
}
