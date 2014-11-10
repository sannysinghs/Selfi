package com.selfi.fragments;

import java.util.ArrayList;
import java.util.List;

import com.selfi.MainActivity;
import com.selfi.R;
import com.selfi.adapters.PhotoAdapter;
import com.selfi.models.Photo;
import com.selfi.utils.MConnectionHelper;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FPhoto extends Fragment implements OnItemClickListener, OnScrollListener {

	List<Photo> mPhotoList ;
	View v;
	ListView mPhotoListView;
	PhotoAdapter adapter;
	int per_page = 15;
	int page_no = 1 ;
	boolean fetching = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new SearchPhotosAsync().execute("Yangon");
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		v = inflater.inflate(R.layout.fragment_photo_layout, null);
		mPhotoListView = (ListView) v.findViewById(R.id.listView_photo);
		mPhotoListView.setOnItemClickListener(this);
		mPhotoListView.setOnScrollListener(this);
		return v;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), position+" clicked", Toast.LENGTH_SHORT).show();
	}
	
	
	
	
	private class SearchPhotosAsync extends AsyncTask<String, Void, List<Photo>> {

		@Override
		protected List<Photo> doInBackground(String... params) {
			// TODO Auto-generated method stub
			fetching = true;
			return MConnectionHelper.fetchPhotos(params[0],per_page,page_no);
			
		}
		
		@Override
		protected void onPostExecute(List<Photo> result) {
			// TODO Auto-generated method stub
			if (mPhotoList == null) {
				mPhotoList = new ArrayList<Photo>();
			}
			
			if (mPhotoList.size() > 0) {
				int size = mPhotoList.size();
				for (int i = 0; i < result.size(); i++) {
					mPhotoList.add(i+size, result.get(i));
				}
				adapter.notifyDataSetChanged();
			}else{
				mPhotoList.addAll(result);
			}
			
			if (adapter == null) {
				adapter = new PhotoAdapter(getActivity() , mPhotoList);
				mPhotoListView.setAdapter(adapter);	
			}
			
			fetching = false;
		}
		
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
				if (!fetching) {
						page_no++;
						new SearchPhotosAsync().execute("Yangon");
				}
			}
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		menu.findItem(R.id.action_add_album).setVisible(true);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
	
		MenuInflater inflater = new MenuInflater(getActivity());
		inflater.inflate(R.menu.album_context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		return super.onContextItemSelected(item);
	}
}
