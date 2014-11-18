/**
 * 
 */
package com.selfi.models;

/**
 * @author tagitdev
 *
 */
public class PhotoDetail {
	private String photo_date;
	private String photo_owner_id;
	private String photo_desc;
	private String photo_comment_count;
	private PhotoOwner photo_owner;
	
	public PhotoDetail() {	}
	
	
	public String getPhoto_date() {
		return photo_date;
	}
	public void setPhoto_date(String photo_date) {
		this.photo_date = photo_date;
	}
	
	public String getPhoto_desc() {
		return photo_desc;
	}
	public void setPhoto_desc(String photo_desc) {
		this.photo_desc = photo_desc;
	}
	public String getPhoto_comment_count() {
		return photo_comment_count;
	}
	public void setPhoto_comment_count(String photo_comment_count) {
		this.photo_comment_count = photo_comment_count;
	}
	public String getPhoto_owner_id() {
		return photo_owner_id;
	}
	public void setPhoto_owner_id(String photo_owner_id) {
		this.photo_owner_id = photo_owner_id;
	}

	public PhotoOwner getPhoto_owner() {
		return photo_owner;
	}
	public void setPhoto_owner(PhotoOwner photo_owner) {
		this.photo_owner = photo_owner;
	}


	public void setInstace(PhotoDetail photoDetailFromJSONObj) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
