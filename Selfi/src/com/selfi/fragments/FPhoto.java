package com.selfi.fragments;

import java.util.List;

import com.selfi.R;
import com.selfi.adapters.PhotoAdapter;
import com.selfi.models.Photo;
import com.selfi.utils.MConnectionHelper;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FPhoto extends Fragment implements OnItemClickListener {

	List<Photo> PhotoList ;
	PhotoAdapter mPhotoAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new SearchPhotosAsync().execute("Search");
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
//		View v = inflater.inflate(R.layout.fragment_photo_layout, null);
//		ListView mPhotoListView = (ListView) v.findViewById(R.id.listView_photo);
//		mPhotoListView.setAdapter(null);
//		mPhotoListView.setOnItemClickListener(this);
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	private class SearchPhotosAsync extends AsyncTask<String, Void, List<Photo>> {

		@Override
		protected List<Photo> doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			return MConnectionHelper.fetchPhotos(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<Photo> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
	}
}
