package com.selfi.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.selfi.daos.AlbumDAO.AlbumEntry;

public class DAOHelper {

	private static Context context;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "Selfi";
	
	private static final String CREATE_TABLE_ALBUM =  "CREATE TABLE "+AlbumEntry.TABLE_NAME+" ("+
			AlbumEntry._ID  + "  INTEGER PRIMARY KEY  ,"+
			AlbumEntry.COLUMN_NAME_TITLE+" TEXT,"+
			AlbumEntry.COLUMN_NAME_DESCRIPTION+" TEXT,"+
			AlbumEntry.COLUMN_NAME_CREATED_AT+"	TEXT,"+
			AlbumEntry.COLUMN_NAME_UPDATED_AT+"	TEXT )";
	
	public DAOHelper(Context ctx) {
		// TODO Auto-generated constructor stub
		context = ctx;
		dbHelper = new DBHelper();
	}
	
	public static class DBHelper extends SQLiteOpenHelper {

		public DBHelper() {
			// TODO Auto-generated constructor stub
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE_ALBUM);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE_ALBUM);
		}
		
	}

	public DBHelper getDbHelper() {
		return dbHelper;
	}
	
	
	
			
	
}
