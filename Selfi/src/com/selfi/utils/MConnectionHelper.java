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
	private Context ctx;
	private PhotoAdapter photoAdapter;
	List<Photo> photos;
	public MConnectionHelper(Context ctx) {
		// TODO Auto-generated constructor stub
		mJSONHandler = new MJSONHandaler();
		photos = new ArrayList<Photo>();
		this.ctx = ctx;
	}
	
	//Volley JSONObject Request
	public void RetrievPhotos(String text, int per_page , int page_no, final ListView mPhotoListView) {
		String url = IConstants.URL+"/?method="+IConstants.METHOD_SEARCH+"&api_key="+IConstants.KEY+"&text="+text+"&sort="+IConstants.SORT+"&page="+page_no+"&per_page="+ per_page +"&format="+IConstants.FORMAT;
		
		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, null, 
				new Response.Listener<JSONObject>() {
		
					@Override
					public void onResponse(JSONObject res) {
						// fetch photos and set it to adapter
						if (photoAdapter == null) {
							photoAdapter = new PhotoAdapter(ctx, FetchPhoto(res));
							mPhotoListView.setAdapter(photoAdapter);
						}
						
						photoAdapter.notifyDataSetChanged();
						
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

	//Create a list of photo from given json object and return 
	private List<Photo> FetchPhoto(JSONObject res) {
		JSONObject jsonObject = null;
		
		try {
			jsonObject = new JSONObject(res.getString("photos"));
			JSONArray jPhotoArray = jsonObject.getJSONArray("photo");
			for (int i = 0; i < jPhotoArray.length(); i++) {
			  Photo p =	mJSONHandler.getPhtoObjFromJObj(jPhotoArray.getJSONObject(i));
			  if (photos.size() > 0) {
				photos.add(photos.size(), p);
			  }else{
				  photos.add(p);  
			  }
			  
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
}
