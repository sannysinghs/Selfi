package com.selfi.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.selfi.models.Photo;

public class MConnectionHelper {

	public static List<Photo> fetchPhotos(String text) {
		// TODO Auto-generated method stub
		List<Photo> photos = new ArrayList<Photo>();
		String url = IConstants.URL+"/?method="+IConstants.METHOD_SEARCH+"&api_key="+IConstants.KEY+"&text="+text+"&sort="+IConstants.SORT+"&format="+IConstants.FORMAT;
		JSONObject jPhotoObj = JSONParser.getJSONFromUrl(url);
		try {
			jPhotoObj = new JSONObject(jPhotoObj.getString("photos"));
			JSONArray jPhotoArray = jPhotoObj.getJSONArray("photo");
			
			for (int i = 0; i < jPhotoArray.length(); i++) {
			  Photo p =	MJSONHandaler.getPhtoObjFromJObj(jPhotoArray.getJSONObject(i));
			  photos.add(p);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return photos;
	}

	
}
