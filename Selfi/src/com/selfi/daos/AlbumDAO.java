package com.selfi.daos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.selfi.daos.DAOHelper.DBHelper;
import com.selfi.models.Album;

public class AlbumDAO {
	
	private Context context;
	private DBHelper dbHelper;
	
	public static abstract class AlbumEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_UPDATED_AT = "updated_at";
       
       
    }
	
	public AlbumDAO(Context ctx) {
		dbHelper = new DAOHelper(ctx).getDbHelper();
		
	}
	
	public void addNewAlbum(Album a){
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cn = new ContentValues();
		cn.put(AlbumEntry.COLUMN_NAME_TITLE, a.getTitle());
		cn.put(AlbumEntry.COLUMN_NAME_DESCRIPTION, a.getDesc());
		db.insert(AlbumEntry.TABLE_NAME, null, cn);
		db.close();
		
		
	}
	
	public ArrayList<Album> getAllAlbums(){
		String sql = "SELECT * FROM "+AlbumEntry.TABLE_NAME;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		
		ArrayList<Album> albums = new ArrayList<Album>();
		
		if(cursor.moveToFirst()){
			do{
				Album album = new Album();
				album.setId( Integer.parseInt(cursor.getString(0) ));
				album.setTitle(cursor.getString(1));
				album.setDesc(cursor.getString(2));
				albums.add(album);
			}while(cursor.moveToNext());
		}
		
		db.close();
		return albums;
	}

	public int delete(Album album) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String whereClause = AlbumEntry._ID + " = ?";
		String[] whereArgs = new String[] { String.valueOf(album.getId()) } ;
		int delete = db.delete(AlbumEntry.TABLE_NAME, whereClause, whereArgs);
		db.close();
		return delete;
	}

	public void updateAlbum(Album a) {
		// TODO Auto-generated method stub
		//get Writable database
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		//create key,value pair 
		ContentValues cn = new ContentValues();
		cn.put(AlbumEntry.COLUMN_NAME_TITLE, a.getTitle());
		cn.put(AlbumEntry.COLUMN_NAME_DESCRIPTION, a.getDesc());
		
		//update to db
		db.update(AlbumEntry.TABLE_NAME, cn, AlbumEntry._ID+"= ?", new String[] {String.valueOf(a.getId())});
		
		//close db
		db.close();
	}
	
}
