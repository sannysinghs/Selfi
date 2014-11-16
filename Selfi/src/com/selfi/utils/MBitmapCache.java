package com.selfi.utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class MBitmapCache extends LruCache<String, Bitmap> implements ImageCache {


	public MBitmapCache(){
		this(getDefaultCacheSize());
		Log.d("","Cahce : " + this.getDefaultCacheSize());
	}
	
	public MBitmapCache(int maxSize) {
		super(maxSize);
		// TODO Auto-generated constructor stub
	}
	
	public static int getDefaultCacheSize(){
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        return cacheSize;
	}
	
	@Override
	protected int sizeOf(String key, Bitmap value) {
		// TODO Auto-generated method stub
		return value.getRowBytes() * value.getHeight() / 1024;
	}
	
	
	
	@Override
	public Bitmap getBitmap(String key) {
		// TODO Auto-generated method stub
		Log.d("Cache", key + " was returned");
		return get(key);
	}

	@Override
	public void putBitmap(String key, Bitmap value) {
		// TODO Auto-generated method stub
		Log.d("Cache", key + " was added");
		put(key, value);
	}
	
}
