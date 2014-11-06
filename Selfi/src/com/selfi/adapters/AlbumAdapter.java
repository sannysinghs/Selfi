package com.selfi.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.selfi.R;
import com.selfi.daos.AlbumDAO;
import com.selfi.models.Album;

public class AlbumAdapter extends ArrayAdapter<Album>{

	private ArrayList<Album> albums;
	private Context context;
	private HashMap<Integer,Boolean> selValMap = new HashMap<Integer, Boolean>();
	private AlbumDAO albumDAO;
	private int editKey;
	
	TextView txt_album_id;
	TextView txt_album_title;
	TextView txt_album_desc;
	
	public AlbumAdapter(Context c ,ArrayList<Album> albums) {
		super(c,R.layout.album_list_item,albums);
		this.context = c;
		this.albums = albums;
		albumDAO = new AlbumDAO(c);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return albums.size();
	}
	
	@Override
	public Album getItem(int position) {
		// TODO Auto-generated method stub
		return albums.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Populate the custom view for the individual list of album 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View v = inflater.inflate(R.layout.album_list_item, null);
		
		txt_album_id = (TextView) v.findViewById(R.id.txt_album_id);
		txt_album_title = (TextView) v.findViewById(R.id.txt_album_title);
		txt_album_desc = (TextView) v.findViewById(R.id.txt_album_desc);
		
		Album album = albums.get(position);
		
		txt_album_id.setText(String.valueOf(album.getId()));
		txt_album_title.setText(album.getTitle());
		txt_album_desc.setText(album.getDesc());
		return v;
	}
	
	//add selected album position from album listView to map 
	public void selectItem(int position, boolean checked) {
		// TODO Auto-generated method stub
		if (checked) {
			selValMap.put(position, checked);
		}else{
			selValMap.remove(position);
		}
		notifyDataSetChanged();
	}

	public void removeSelectedItemFromList() {
		// TODO Auto-generated method stub
		List<Album> selectedAlbum = new ArrayList<Album>();
		
		//Get all selected index of albums
		for (int pos : selValMap.keySet()) {
			selectedAlbum.add(albums.get(pos));
			//remove from database
			albumDAO.delete(getItem(pos));
		}
		
		//remove from album listView
		albums.removeAll(selectedAlbum);
		
		
		//flush the map
		this.clearSelectonMap();
		notifyDataSetChanged();
	}

	public void addNewAlbum(Album a) {
		// TODO Auto-generated method stub
		albums.add(a);
		albumDAO.addNewAlbum(a);
		notifyDataSetChanged();
	}

	public Album getItemForEdit() {
		Album a = new Album();
		
		for (int key : selValMap.keySet()) {
			editKey = key;
			a = albums.get(key);
		}
		return a;
		
	}

	public void updateAlbum(Album a) {
		// TODO Auto-generated method stub
		if (editKey > -1) {
			albums.set(editKey, a);
			albumDAO.updateAlbum(a);
		}
		editKey = -1;
		this.clearSelectonMap();
	}

	public void clearSelectonMap() {
		// TODO Auto-generated method stub
		selValMap.clear();
	}

	public void removeAlbum( int position) {
		// TODO Auto-generated method stub
		albumDAO.delete(albums.get(position));
		albums.remove(position);
		notifyDataSetChanged();
	}
	
}
