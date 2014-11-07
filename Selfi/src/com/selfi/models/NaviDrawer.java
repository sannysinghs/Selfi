package com.selfi.models;

public class NaviDrawer {

	private String title;
	private int icon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public NaviDrawer(String title,int icon) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.icon = icon;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Title : "+title + " , Icon : "+icon;
	}
	
	
	
	
}
