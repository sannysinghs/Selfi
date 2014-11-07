package com.selfi.models;

public class Photo {

	private int id;
	private String photo_id;
	private String photo_desc;
	private String photo_title;
	private String photo_url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}
	
	public String getPhoto_title() {
		return photo_title;
	}
	public void setPhoto_title(String photo_title) {
		this.photo_title = photo_title;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	public String getPhoto_desc() {
		return photo_desc;
	}
	public void setPhoto_desc(String photo_desc) {
		this.photo_desc = photo_desc;
	}
	public Photo() {
		
	}
	public Photo(String photo_id,String title,String photo_url){
		this.photo_id = photo_id;
		this.photo_title = title;
		this.photo_url = photo_url;
	}
	public Photo(int id, String photo_id, String title, String photo_url) {
		this(photo_id,title,photo_url);
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Title : " + photo_title;
	}
	
	
	
}
