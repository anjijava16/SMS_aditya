package com.sms.bean;

import org.springframework.web.multipart.MultipartFile;


public class Student1 {
	private String firstName;
	private MultipartFile image;
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
