package com.example.demo.dto;

public class LoginDto {

	private String uid;
	private String upwd;
	private boolean uid_save;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public boolean isUid_save() {
		return uid_save;
	}
	public void setUid_save(boolean uid_save) {
		this.uid_save = uid_save;
	}


}
