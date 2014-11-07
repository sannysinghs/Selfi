package com.selfi.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.selfi.models.Photo;

public class MJSONHandaler {

	public static Photo getPhtoObjFromJObj(JSONObject object) throws JSONException {
		// TODO Auto-generated method stub
		Photo p = new Photo();
		String id = object.getString("id");
		String farm = object.getString("farm");
		String title = object.getString("title");
		String secret = object.getString("secret");
		String server = object.getString("server");
		
		
		return null;
	}
	

}
