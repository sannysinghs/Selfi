package com.selfi.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.selfi.R;
import com.selfi.adapters.PhotoAdapter;
import com.selfi.models.Photo;
import com.selfi.utils.IConstants;
import com.selfi.utils.MConnectionHelper;
import com.selfi.utils.VolleyController;

public class FPhoto extends Fragment implements OnItemClickListener, OnScrollListener {

	List<Photo> mPhotoList ;
	View v;
	ListView mPhotoListView;
	PhotoAdapter adapter;
	MConnectionHelper helper;
	int per_page = 15;
	int page_no = 1 ;
	boolean fetching = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
//		String text = "Face";
//		String url = IConstants.URL+"/?method="+IConstants.METHOD_SEARCH+"&api_key="+IConstants.KEY+"&text="+text+"&sort="+IConstants.SORT+"&page="+page_no+"&per_page="+ per_page +"&format="+IConstants.FORMAT;
//		new SearchPhotosAsync().execute("Yangon");
//		long totalMemory = Runtime.getRuntime().totalMemory();
//		long freeMemory = Runtime.getRuntime().freeMemory();
//		Log.d("Total memory", totalMemory +"");
//		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		v = inflater.inflate(R.layout.fragment_photo_layout, null);
		mPhotoListView = (ListView) v.findViewById(R.id.listView_photo);
		mPhotoListView.setOnItemClickListener(this);
		mPhotoListView.setOnScrollListener(this);
		
		helper = new MConnectionHelper(getActivity());
		helper.fetchPhotos("Face", per_page, page_no , mPhotoListView);
		
		mPhotoList = helper.getPhotos();
		
		mPhotoListView.setAdapter(new PhotoAdapter(getActivity() , mPhotoList));	
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
				if (!fetching) {
						page_no++;
//						helper.fetchPhotos("Face", per_page, page_no, mPhotoListView);
				}
			}
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		
	}
	
	/* --------------------Async Tasks------------------------*/
//	private class SearchPhotosAsync extends AsyncTask<String, Void, List<Photo>> {
//		
//		@Override
//		protected List<Photo> doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			fetching = true;
//			MConnectionHelper helper = new MConnectionHelper();
////			return helper.fetchPhotos(params[0],per_page,page_no);
//			
//		}
//		
//		@Override
//		protected void onPostExecute(List<Photo> result) {
//			// TODO Auto-generated method stub
//			
//			Log.d("","Download ");
//			if (mPhotoList == null) {
//				mPhotoList = new ArrayList<Photo>();
//			}
//			
//			if (mPhotoList.size() > 0) {
//				int size = mPhotoList.size();
//				for (int i = 0; i < result.size(); i++) {
//					mPhotoList.add(i+size, result.get(i));
//				}
//				adapter.notifyDataSetChanged();
//			}else{
//				mPhotoList.addAll(result);
//			}
//			
//			if (adapter == null) {
//				adapter = new PhotoAdapter(getActivity() , mPhotoList);
//				mPhotoListView.setAdapter(adapter);	
//			}
//			fetching = !fetching;
//		}
//		
//	}

}
