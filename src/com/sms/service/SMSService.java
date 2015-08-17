package com.sms.service;

import java.util.List;
import java.util.Map;

import com.sms.bean.Role;
import com.sms.bean.Student;
import com.sms.bean.User;

public interface SMSService {
	List<List<String>>  validateUser(String username, String password)throws Exception;
	public boolean saveUser(User user)throws Exception;
	boolean saveStudentProfile(Student student)throws Exception;
    public Student getStudentProfile(String studentId)throws Exception;
    public List<Role> getRoleList() throws Exception ;
}
