package com.selfi.daos;

import java.util.ArrayList;

import com.selfi.models.Album;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlbumDAO extends SQLiteOpenHelper{
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "Selfithumbs";
	private static final String TABLE_ALBUM = "albums";
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_DESC = "description";
	
	public AlbumDAO(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		 String CREATE_ALBUM_TABLE = "CREATE TABLE `albums` ("+
											"`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
											"`title`	TEXT,"+
											"`description`	TEXT,"+
											"`created_at`	TEXT,"+
											"`updated_at`	TEXT"+
											")";
	 
	        db.execSQL(CREATE_ALBUM_TABLE);
	        
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS albums");
		this.onCreate(db);
	}
	
	public void addNewAlbum(Album a){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cn = new ContentValues();
		cn.put(KEY_TITLE, a.getTitle());
		cn.put(KEY_DESC, a.getDesc());
		db.insert(TABLE_ALBUM, null, cn);
		db.close();
	}
	
	public ArrayList<Album> getAllAlbums(){
		String sql = "SELECT * FROM "+TABLE_ALBUM;
		SQLiteDatabase db = this.getWritableDatabase();
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

	public void delete(Album album) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		String whereClause = "id = ?";
		String[] whereArgs = new String[] { String.valueOf(album.getId()) } ;
		db.delete(TABLE_ALBUM, whereClause, whereArgs);
		db.close();
	}

	public void updateAlbum(Album a) {
		// TODO Auto-generated method stub
		//get Writable database
		SQLiteDatabase db = this.getWritableDatabase();
		
		//create key,value pair 
		ContentValues cn = new ContentValues();
		cn.put(KEY_TITLE, a.getTitle());
		cn.put(KEY_DESC, a.getDesc());
		
		//update to db
		db.update(TABLE_ALBUM, cn, KEY_ID+"= ?", new String[] {String.valueOf(a.getId())});
		
		//close db
		db.close();
	}
	
}
