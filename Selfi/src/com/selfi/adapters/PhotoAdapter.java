package com.selfi.adapters;

import java.util.List;

import com.selfi.R;
import com.selfi.models.Photo;
import com.selfi.utils.MConnectionHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoAdapter extends ArrayAdapter<Photo> {

	List<Photo> photos ;
	Context context;
	View v;
	
	
	public PhotoAdapter(Context context, List<Photo> photos) {
		super(context, R.layout.photo_list_item,photos);
		this.context = context;
		this.photos = photos;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photos.size();
	}
	
	@Override
	public Photo getItem(int position) {
		// TODO Auto-generated method stub
		return photos.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		
		View v = inflater.inflate(R.layout.photo_list_item, null);
		
		TextView title = (TextView) v.findViewById(R.id.txt_photo_title);
		TextView desc = (TextView) v.findViewById(R.id.txt_photo_description);
		ImageView thumbnail = (ImageView) v.findViewById(R.id.img_photo_thumbnail);
		Photo photo = this.getItem(position);
		
		title.setText(photo.getPhoto_title());
		desc.setText(photo.getPhoto_desc());
		thumbnail.setImageBitmap(photo.getPhoto_thumbnail());
		
		return v;
	}

}
