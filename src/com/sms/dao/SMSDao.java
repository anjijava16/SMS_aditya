package com.sms.dao;


import java.util.List;

import com.sms.bean.Role;
import com.sms.bean.Student;
import com.sms.bean.User;
public interface SMSDao {
	//validate user ussing user credential
	List<List<String>>  validateUser(String username, String password) throws Exception;
  //save SMS user details in DB 
  boolean saveUser(User user) throws Exception;
  //save student details to db
  boolean saveStudentProfile(Student student) throws Exception;
  //get student details from db by student id
  Student getStudentProfile(String studentId) throws Exception;
  //get role details from db 
  List<Role>  getRoleList() throws Exception;
       
}
