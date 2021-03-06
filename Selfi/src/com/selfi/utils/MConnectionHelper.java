package com.selfi.utils;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.selfi.adapters.PhotoAdapter;
import com.selfi.fragments.FPhoto;
import com.selfi.models.Photo;

public class MConnectionHelper {
	
	private MJSONHandaler mJSONHandler;
	private Context ctx;
	private PhotoAdapter photoAdapter;
	ArrayList<Photo> photos;
	
	public MConnectionHelper(Context ctx) {
		mJSONHandler = new MJSONHandaler();
		photos = new ArrayList<Photo>();
		this.ctx = ctx;
	}
	
	//Volley JSONObject Request
	public void RetrievePhotos(int per_page , int page_no, final ListView mPhotoListView , String ... query) {
		String url;
		
		if ( query.length > 0  ) {
			url = IConstants.URL+"/?method="+IConstants.METHOD_SEARCH+"&api_key="+IConstants.KEY+"&text="+query[0]+"&sort="+IConstants.SORT+"&page="+page_no+"&per_page="+ per_page +"&format="+IConstants.FORMAT;
		}else{
			url = IConstants.URL+"/?method="+IConstants.METHOD_RECENT+"&api_key="+IConstants.KEY+"&sort="+IConstants.SORT+"&page="+page_no+"&per_page="+ per_page +"&format="+IConstants.FORMAT;
		}
		
		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, null, 
				new Response.Listener<JSONObject>() {
		
					@Override
					public void onResponse(JSONObject res) {
						// fetch photos and set it to adapter
						
						if (photoAdapter != null ) {
							photos.addAll(photos.size(), mJSONHandler.FetchPhotos(res));
							
						}else{
							photos = mJSONHandler.FetchPhotos(res);
							photoAdapter = new PhotoAdapter(ctx,photos );
							mPhotoListView.setAdapter(photoAdapter);
							
						}
						//Change Status
						FPhoto.changeFetchStatus();
						photoAdapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError e) {
						// TODO Auto-generated method stub
						Log.e("Response Fail ", "Error: " + e.getMessage());
					}
				});
		
		VolleyController.getInstance().addToRequestQueue(req);
	}
	
	
	public static void RetrievePhotoDetail(final Photo p) {
		
		String url = IConstants.URL+"/?method="+IConstants.METHOD_GETINFO+"&api_key="+IConstants.KEY+"&photo_id="+p.getPhoto_id()+"&format="+IConstants.FORMAT;
		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, null, 
				new Response.Listener<JSONObject>() {
		
					@Override
					public void onResponse(JSONObject res) {
						// fetch photos and set it to adapter
						try {
							p.setPhoto_detail(MJSONHandaler.getPhotoDetailFromJSONObj(res.getJSONObject("photo")));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError e) {
						// TODO Auto-generated method stub
						
					}
				});
		
		VolleyController.getInstance().addToRequestQueue(req);
	}
}
