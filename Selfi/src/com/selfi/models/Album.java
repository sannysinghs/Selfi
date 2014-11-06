package com.selfi.models;

import java.sql.Date;
import java.util.HashMap;

public class Album{
	private int id;
	private String title;
	private String desc;
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Album(String title,String desc){
		this.title = title;
		this.desc = desc;
	}
	public Album(int id ,String title,String desc){
		this(title,desc);
		this.id=id;
	}
	
	public Album(){
		
	}
	public Album(int id ,String title,String desc,Date date) {
		// TODO Auto-generated constructor stub
		this(id,title,desc);
		this.date = date;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Album Object [ Id : "+ id +"Title : "+ title + " Description : "+ desc +"]";
	}

}
