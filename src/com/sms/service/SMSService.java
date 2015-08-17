package com.sms.service;

import java.util.List;
import java.util.Map;

import com.sms.bean.Course;
import com.sms.bean.Role;
import com.sms.bean.Student;
import com.sms.bean.User;

public interface SMSService {
	List<List<String>>  validateUser(String username, String password)throws Exception;
	public boolean saveUser(User user)throws Exception;
	boolean saveStudentProfile(Student student)throws Exception;
    public List<Student> getStudentProfile(String studentId, String parentId)throws Exception;
    public List<Role> getRoleList() throws Exception ;
	List<List<String>> getCourseDetails(String studentId);
	List<Course> getCourseList() throws Exception;
	List<Student>  getSearchedProfile(String code, String status) throws Exception;
	boolean approveProfiles(String[] studentIds);
}
