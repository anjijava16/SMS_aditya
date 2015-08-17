package com.sms.bean;

public class User {
	private String userName;
	private String userId;
	private String Password;
	private String creationDate;
	private String status;
	private Role smsRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Role getSmsRole() {
		return smsRole;
	}

	public void setSmsRole(Role smsRole) {
		this.smsRole = smsRole;
	}

}
