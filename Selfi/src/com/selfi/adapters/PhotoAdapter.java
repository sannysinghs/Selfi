package com.selfi.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.selfi.R;
import com.selfi.fragments.FPhoto;
import com.selfi.models.Photo;
import com.selfi.models.PhotoDetail;
import com.selfi.utils.MStrUtils;
import com.selfi.utils.VolleyController;

public class PhotoAdapter extends ArrayAdapter<Photo> {

	List<Photo> photos ;
	Context context;
	View convertView;
	ImageLoader mImageLoader = VolleyController.getInstance().getImageLoader(); 
	private int lastPostion = -1;
	
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
		NetworkImageView profile;
		TextView owner_name;
		TextView comment_count;
		TextView views_count;
		
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
			holder.profile = (NetworkImageView) convertView.findViewById(R.id.img_profile);
			holder.owner_name = (TextView) convertView.findViewById(R.id.txt_profile_name);
			holder.comment_count = (TextView) convertView.findViewById(R.id.txt_action_comment);
			holder.views_count = (TextView) convertView.findViewById(R.id.txt_action_views);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Photo photo = this.getItem(position);
		holder.title.setText(photo.getPhoto_title());		
		if (photo.getPhoto_detail() != null) {
			PhotoDetail detail = photo.getPhoto_detail();
			holder.desc.setText(  MStrUtils.HTMLEncode(detail.getPhoto_desc()));
			holder.profile.setImageUrl(detail.getPhoto_owner().getOwner_thumbnail(), mImageLoader);
			holder.owner_name.setText(detail.getPhoto_owner().getOwner_name());
			holder.comment_count.setText(detail.getPhoto_comment_count());
			holder.views_count.setText(detail.getPhoto_views());
		}
		
		if (position > lastPostion) {
			convertView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.list_anim_up_from_bottom ));
			lastPostion = position;
		}
		
		holder.thumbnail.setImageUrl(photo.getPhoto_url(), mImageLoader);
		
		return convertView;
	}


}
