package com.sms.bean;

import org.springframework.web.multipart.MultipartFile;

public class Student {
private String studentID;
private String firstName;
private String	lastName ;	
private String 	dobDate;
private	String gender;
private	String admDate;	
private	String admClass;	
private String	nationality ;
private	String religion ;	
private	String club; 	
private	String medCond;	
private String 	rescAddress;	
private	String emerContact ;
private	String parentID;
private	String fileName; ;
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
private	MultipartFile image ;
private Parent parent;
private	Class smsclass;
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getAdmDate() {
	return admDate;
}
public void setAdmDate(String admDate) {
	this.admDate = admDate;
}
public String getAdmClass() {
	return admClass;
}
public void setAdmClass(String admClass) {
	this.admClass = admClass;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String getReligion() {
	return religion;
}
public void setReligion(String religion) {
	this.religion = religion;
}
public String getClub() {
	return club;
}
public void setClub(String club) {
	this.club = club;
}
public String getMedCond() {
	return medCond;
}
public void setMedCond(String medCond) {
	this.medCond = medCond;
}
public String getEmerContact() {
	return emerContact;
}
public void setEmerContact(String emerContact) {
	this.emerContact = emerContact;
}
public String getParentID() {
	return parentID;
}
public void setParentID(String parentID) {
	this.parentID = parentID;
}
public MultipartFile getImage() {
	return image;
}
public void setImage(MultipartFile image) {
	this.image = image;
}

public String getStudentID() {
	return studentID;
}
public void setStudentID(String studentID) {
	this.studentID = studentID;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public Parent getParent() {
	return parent;
}
public void setParent(Parent parent) {
	this.parent = parent;
}
public Class getSmsclass() {
	return smsclass;
}
public void setSmsclass(Class smsclass) {
	this.smsclass = smsclass;
}
public String getDobDate() {
	return dobDate;
}
public void setDobDate(String dobDate) {
	this.dobDate = dobDate;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getRescAddress() {
	return rescAddress;
}
public void setRescAddress(String rescAddress) {
	this.rescAddress = rescAddress;
}

}
