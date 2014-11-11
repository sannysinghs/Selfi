package com.selfi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.selfi.R;
import com.selfi.adapters.AlbumAdapter;
import com.selfi.daos.AlbumDAO;
import com.selfi.dialogs.AddNewAlbumDialog;
import com.selfi.dialogs.EditAlbumDialog;
import com.selfi.models.Album;
import com.selfi.utils.SwipeToRemoveUtils;


public class FAlbum extends Fragment implements OnItemClickListener {
	
	ListView albumListView;
	AlbumAdapter albumAdapter;
	AlbumDAO albumDao;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//create album adapter
		albumDao =  new AlbumDAO(getActivity());
		albumAdapter = new AlbumAdapter(getActivity(),albumDao.getAllAlbums());
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		menu.findItem(R.id.action_add_album).setVisible(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_album_layout, container , false);
		albumListView = (ListView) v.findViewById(R.id.album_list_view);
		albumListView.setAdapter(albumAdapter);
		albumListView.setOnItemClickListener(this);
		albumListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		
		new SwipeToRemoveUtils(albumListView, new SwipeToRemoveUtils.DismissCallbacks() {
			@Override
			public void OnDismiss(int position) {
				// TODO Auto-generated method stub
				albumAdapter.removeAlbum(position);
				Log.d("", "I am going to remove ");
			}
		});
		
		albumListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
			int counter = 0;	
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}
			
			//ok button is clicked 
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				//reset counter
				counter = 0;
				albumAdapter.clearSelectonMap();
			}
			
			//Long clicked
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.album_context_menu, menu);
				return true;
			}
			
			//an menu item is selected
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub 
				
   				switch (item.getItemId()) {
				case R.id.action_album_delete:
						//reset counter
						counter = 0;
						//remove items
						albumAdapter.removeSelectedItemFromList();
						mode.finish();
					break;
				
				case R.id.action_album_edit:
						Album a = albumAdapter.getItemForEdit();
						new EditAlbumDialog(albumAdapter,a).show(getFragmentManager(), "Edit Album");
						mode.finish();
					break;
			
				default:
					break;
				}
				return true;
			}
			
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position,
					long id, boolean checked) {
				// TODO Auto-generated method stub
				
				//increase or decrease the selected items
				counter = (checked)? ++counter : --counter;
				MenuItem ic_edit = mode.getMenu().findItem(R.id.action_album_edit);
				if (counter == 1) {
					ic_edit.setVisible(true);
				}else{
					ic_edit.setVisible(false);
				}
				albumAdapter.selectItem(position,checked);
				mode.setTitle(counter + " selected");
			}
		});
		return v;
	}
	
	//Main menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 int id = item.getItemId();
	        switch (id) {
				case R.id.action_add_album:
					new AddNewAlbumDialog(albumAdapter).show(getFragmentManager(), "New Album");
					break;
				default:
					
					break;
			}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = new MenuInflater(getActivity());
		inflater.inflate(R.menu.album_context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		return super.onContextItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}
	
}
