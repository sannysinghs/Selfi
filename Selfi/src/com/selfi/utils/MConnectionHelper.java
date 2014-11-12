package com.selfi.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.selfi.adapters.PhotoAdapter;
import com.selfi.models.Photo;

public class MConnectionHelper {
	
	private MJSONHandaler mJSONHandler;
	private JSONObject jPhotoObj;
	private List<Photo> photos;
	private Context ctx;
	public MConnectionHelper(Context ctx) {
		// TODO Auto-generated constructor stub
		mJSONHandler = new MJSONHandaler();
		this.ctx = ctx;
	}
	
	public void fetchPhotos(String text, int per_page , int page_no, final ListView mPhotoListView) {
		// TODO Auto-generated method stub
		photos = new ArrayList<Photo>();
		String url = IConstants.URL+"/?method="+IConstants.METHOD_SEARCH+"&api_key="+IConstants.KEY+"&text="+text+"&sort="+IConstants.SORT+"&page="+page_no+"&per_page="+ per_page +"&format="+IConstants.FORMAT;
		
		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, null, 
				new Response.Listener<JSONObject>() {
		
					@Override
					public void onResponse(JSONObject res) {
						// TODO Auto-generated method stub
						fetchPhoto(res);
						mPhotoListView.setAdapter(new PhotoAdapter(ctx, photos));
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError e) {
						// TODO Auto-generated method stub
						Log.d("Response Fail ", "Error: " + e.getMessage());
					}
				});
		
		VolleyController.getInstance().addToRequestQueue(req);
	}

	protected void fetchPhoto(JSONObject res) {
		JSONObject jsonObject = null;
		
		try {
			jsonObject = new JSONObject(res.getString("photos"));
			
			JSONArray jPhotoArray = jsonObject.getJSONArray("photo");
			
			for (int i = 0; i < jPhotoArray.length(); i++) {
			  Photo p =	mJSONHandler.getPhtoObjFromJObj(jPhotoArray.getJSONObject(i));
			  photos.add(p);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e("JObj", jsonObject.toString());
		}
		
		
	}
	
	public List<Photo> getPhotos(){
		return this.photos;
	}
	
	

	
}
