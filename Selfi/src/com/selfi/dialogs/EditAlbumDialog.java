package com.selfi.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.selfi.R;
import com.selfi.adapters.AlbumAdapter;
import com.selfi.models.Album;

public class EditAlbumDialog extends DialogFragment {
	
	AlbumAdapter albumAdapter;
	Album album;
	
	public EditAlbumDialog(AlbumAdapter adapter, Album album) {
		// TODO Auto-generated constructor stub
		this.albumAdapter = adapter;
		this.album = album;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View v = inflater.inflate(R.layout.insert_new_album, null);
		
		final EditText a_title = (EditText) v.findViewById(R.id.txt_album_new_title);
		final EditText a_desc = (EditText) v.findViewById(R.id.txt_album_new_desc);
		
		a_title.setText(album.getTitle());
		a_desc.setText(album.getDesc());
		
         builder.setView(v)
        		.setTitle("Edit Album")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   album.setTitle(a_title.getText().toString());
                	   album.setDesc(a_desc.getText().toString());
                       albumAdapter.updateAlbum(album);
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
	}
}
