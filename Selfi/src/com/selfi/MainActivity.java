package com.selfi;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.selfi.adapters.NaviDrawerAdapter;
import com.selfi.fragments.FAlbum;
import com.selfi.fragments.FDrawer;
import com.selfi.fragments.FPhoto;
import com.selfi.models.NaviDrawer;

public class MainActivity extends Activity {
	private String[] navi_title_array;
	private TypedArray navi_icon_array;
	private List<NaviDrawer> navi_drawer_list;
	private NaviDrawerAdapter naviDrawerAdapter;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	private ListView mDrawerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mTitle = mDrawerTitle = getTitle();
		//Drawer layout of support widget
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		//List View 
	    mDrawerList = (ListView) findViewById(R.id.left_drawer);
	    //Retrieving the string array from res/values/strings.xml
		navi_title_array = getResources().getStringArray(R.array.nav_drawer_items);
		navi_icon_array = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		
		navi_drawer_list = new ArrayList<NaviDrawer>();
		for (int i = 0; i < navi_title_array.length; i++) {
			navi_drawer_list.add( new NaviDrawer(navi_title_array[i], navi_icon_array.getResourceId(i, -1)));
		}
		//construct adapter for left_drawer  
		naviDrawerAdapter = new NaviDrawerAdapter(this, navi_drawer_list);
		//set the adapter
		mDrawerList.setAdapter(naviDrawerAdapter);
		//Handling click event on list item of left_drawer
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				displayView(position);//show view
				
			}
		});
	
		//Handle Drawer cpen and close event
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name){
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		// set the photo fragment as default one 
		displayView(0);
		
	}
	//display selected view from left_drawer
	protected void displayView(int position) {
		// TODO Auto-generated method stub
		Fragment mFragment = null;
		switch (position) {
		case 0:
			mFragment = new FPhoto();
			break;
		case 1 :
			mFragment = new FDrawer();
			break;
		case 2:
			mFragment = new FAlbum();
			break;	
		default:
			break;
		}
		
		if (mFragment != null) {
			//Replace view
			FragmentManager fm = getFragmentManager();
			fm.beginTransaction().replace(R.id.content_frame,mFragment).commit();
			mDrawerList.setItemChecked(position, true);//mark as checked state
			mDrawerList.setSelection(position);//
			setTitle(navi_title_array[position]);
			mDrawerLayout.closeDrawer(mDrawerList);//close drawer
	  }
	}

	/**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		mTitle = title;
		getActionBar().setTitle(title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		 SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		 searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		    
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		return super.onPrepareOptionsMenu(menu);
	}
}
