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
import com.selfi.daos.AlbumDAO;
import com.selfi.models.Album;

public class AddNewAlbumDialog extends DialogFragment {

	AlbumDAO albumDao;
	AlbumAdapter albumAdapter;
	public AddNewAlbumDialog(AlbumAdapter a) {
		// TODO Auto-generated constructor stub
		this.albumAdapter = a;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		albumDao = new AlbumDAO(getActivity());
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View v = inflater.inflate(R.layout.insert_new_album, null);
		final EditText a_title = (EditText) v.findViewById(R.id.txt_album_new_title);
		final EditText a_desc = (EditText) v.findViewById(R.id.txt_album_new_desc);
         builder.setView(v)
        		.setTitle("Add Album")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       albumAdapter.addNewAlbum(new Album(a_title.getText().toString() , a_desc.getText().toString()));
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
