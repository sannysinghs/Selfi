package com.selfi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
 
public class JSONParser {
 
    public static InputStream getStream(String url) {
        InputStream is = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return is;
    }
        
    public static String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            is.close();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return(sb.toString());
    }
    
    public static Bitmap readBitmap(String url){
    	
//    	BitmapFactory.Options options = new BitmapFactory.Options();
//    	options.inJustDecodeBounds = true;
//    	BitmapFactory.decodeStream(getStream(url) , null, options);
//    	
//    	int outHeight = options.outHeight;
//    	int outWidth = options.outWidth;
//    	int inSampleSize = 1 ;
//   
//    	if (outWidth > 400 || outHeight > 100) {
//			final int halfOutWidth =  outWidth / 2;
//			final int halfOutHeight = outHeight / 2;
//			while ((halfOutHeight / inSampleSize) > 100
//	                && (halfOutWidth / inSampleSize) > 200) {
//				inSampleSize *= 2;
//				
//			}
//		}
//    	options.inSampleSize = inSampleSize;
//    	options.inJustDecodeBounds = false;
//     	Log.d("","Width : "+ outWidth + " Height : "+ outHeight + " In sample size : "+inSampleSize);
    	return BitmapFactory.decodeStream(getStream(url));
    }
 
    public static String postStream(String url, String data) {
        InputStream is = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(data));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return(readStream(is));
    }
    
    
    public static JSONObject getJSONFromUrl(String url) {
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(readStream(getStream(url)));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }
    
    public static JSONArray getJSONArrayFromUrl(String url) {
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(readStream(getStream(url)));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing array " + e.toString());
        }
        return jArray;
    }
    
}