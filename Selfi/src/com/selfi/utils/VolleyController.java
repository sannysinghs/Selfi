package com.selfi.utils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.text.TextUtils;

public class VolleyController extends Application{

	private static final String TAG = VolleyController.class.getSimpleName();
	private static VolleyController mClassInstace;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mClassInstace = this;
	}
	
	public static synchronized VolleyController getInstance(){
		return mClassInstace;
	}
	
	public RequestQueue getRequestQueue(){
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}
	
	public ImageLoader getImageLoader(){
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader =  new ImageLoader(mRequestQueue, new MBitmapCache());
		}
		return mImageLoader;
	}
	
	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// TODO Auto-generated method stub
		req.setTag( TextUtils.isEmpty(tag)? TAG : tag );
		getRequestQueue().add(req);
	}
	
	public <T> void addToRequestQueue(Request<T> req){
		req.setTag(TAG);
		getRequestQueue().add(req);
	}
	
	public void CancelPendingRequests(String tag){
		if (!TextUtils.isEmpty(tag)) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
