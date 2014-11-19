package com.selfi.models;

public class PhotoOwner {
	private String owner_id;
	private String owner_name;
	private String owner_thumbnail;
	private String owner_url;
	
	
	public PhotoOwner() {}
	
	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getOwner_thumbnail() {
		return owner_thumbnail;
	}
	public void setOwner_thumbnail(String owner_thumbnail) {
		this.owner_thumbnail = owner_thumbnail;
	}
	public String getOwner_url() {
		return owner_url;
	}
	public void setOwner_url(String owner_url) {
		this.owner_url = owner_url;
	}
	
}
