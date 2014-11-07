/**
 * 
 */
package com.selfi.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfi.R;
import com.selfi.models.NaviDrawer;

/**
 * @author tagitdev
 *
 */
public class NaviDrawerAdapter extends BaseAdapter {

	private Context ctx;
	private List<NaviDrawer> naviList;
	public NaviDrawerAdapter(Context ctx , List<NaviDrawer> naviList) {
		// TODO Auto-generated constructor stub
		this.ctx = ctx;
		this.naviList = naviList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return naviList.size();
	}
	
	@Override
	public NaviDrawer getItem(int position) {
		// TODO Auto-generated method stub
		return naviList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = ((Activity)ctx).getLayoutInflater();
			convertView = inflater.inflate(R.layout.drawer_list_item , null);
		}
		ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		
		NaviDrawer naviDrawer = naviList.get(position);
		title.setText( naviDrawer.getTitle());
		icon.setImageResource(naviDrawer.getIcon());
		return convertView;
	}

}
