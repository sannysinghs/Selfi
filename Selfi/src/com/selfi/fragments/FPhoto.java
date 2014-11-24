package com.selfi.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.selfi.R;
import com.selfi.adapters.PhotoAdapter;
import com.selfi.models.Photo;
import com.selfi.utils.IConstants;
import com.selfi.utils.MBitmapCache;
import com.selfi.utils.MConnectionHelper;

public class FPhoto extends Fragment implements OnItemClickListener, OnScrollListener {

	List<Photo> mPhotoList ;
	View v;
	ListView mPhotoListView;
	PhotoAdapter adapter;
	MConnectionHelper helper;
	private int page_count;
	private String query;
	
	private static boolean fetching;
	
	public FPhoto() {
		fetching = true;
		page_count = 1;
	}
	
	public FPhoto(String query) {
		this();
		this.query = query;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		helper = new MConnectionHelper(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_photo_layout, null);
		mPhotoListView = (ListView) v.findViewById(R.id.listView_photo);
		mPhotoListView.setOnItemClickListener(this);
		mPhotoListView.setOnScrollListener(this);
		if (!TextUtils.isEmpty(this.query)) {
			helper.RetrieveRecentPhotos(IConstants.NO_OF_ITEMS_PER_PAGE, page_count , mPhotoListView , query );
		}else{
			helper.RetrieveRecentPhotos(IConstants.NO_OF_ITEMS_PER_PAGE, page_count , mPhotoListView);
		}
		changeFetchStatus();
		return v;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), position+" clicked", Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
		if (view.getId() == R.id.listView_photo) {
			int last = firstVisibleItem + visibleItemCount;
			if (last == totalItemCount && totalItemCount > 0) {	
				Log.d("Last Item and total count", last + " , "+ totalItemCount);
				if (!fetching) {
					if (page_count <= 5) {
						Log.d("Fetching", "Fetching again");
						page_count++;
						helper.RetrieveRecentPhotos(IConstants.NO_OF_ITEMS_PER_PAGE, page_count , mPhotoListView);
						changeFetchStatus();
					}else{
						Log.d("Fetching", "Reached end of scroll");
					}
				}
			}
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		
	}
	
	public static void changeFetchStatus() {
		fetching = !fetching;
	}
	

}
