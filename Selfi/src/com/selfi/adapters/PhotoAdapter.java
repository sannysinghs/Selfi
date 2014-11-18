package com.selfi.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.selfi.R;
import com.selfi.models.Photo;
import com.selfi.utils.MStrUtils;
import com.selfi.utils.VolleyController;

public class PhotoAdapter extends ArrayAdapter<Photo> {

	List<Photo> photos ;
	Context context;
	View convertView;
	ImageLoader mImageLoader = VolleyController.getInstance().getImageLoader(); 
	
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
	
	public class ViewHolder{
		TextView title;
		TextView desc;
		NetworkImageView thumbnail;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.photo_list_item, null);
			holder = new ViewHolder();
			
			
			holder.title = (TextView) convertView.findViewById(R.id.txt_photo_title);
			holder.desc = (TextView) convertView.findViewById(R.id.txt_photo_description);
			holder.thumbnail = (NetworkImageView) convertView.findViewById(R.id.img_photo_thumbnail);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		Photo photo = this.getItem(position);
		
		holder.title.setText(photo.getPhoto_title());
		holder.desc.setText(  MStrUtils.HTMLEncode(photo.getPhoto_detail().getPhoto_desc()));
		holder.thumbnail.setImageUrl(photo.getPhoto_url(), mImageLoader);		
		return convertView;
	}
	
//private class BitMapWorkerAsyncTask extends AsyncTask<String, Void, Bitmap> {
//		
//		private String url;
//		private WeakReference<ImageView> wRefImageView;
//		
//		public BitMapWorkerAsyncTask(ImageView imageView) {
//			// TODO Auto-generated constructor stub
//			wRefImageView = new WeakReference<ImageView>(imageView);
//		}
//		
//		@Override
//		protected Bitmap doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			return 	MJSONHandaler.getBitMapImageFromURL(params[0]);
//	
//		}
//		
//		@Override
//		protected void onPostExecute(Bitmap result) {
//			// TODO Auto-generated method stub
//			if (wRefImageView != null) {
//	            ImageView imageView = wRefImageView.get();
//	            if (imageView != null) {
//	                imageView.setImageBitmap(result);
//	            }
//	        }
//		}
//	}
//
}
