package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sms.bean.Role;
import com.sms.bean.Student;
import com.sms.bean.User;
import com.sms.dao.SMSDao;

public class SMSServiceImpl implements SMSService {

	 @Autowired  
	 SMSDao smsdao;  
	public List<List<String>> validateUser(String username, String password)throws Exception {
		// TODO Auto-generated method stub
		return smsdao.validateUser(username,password) ;
	}
	@Override
	public boolean saveUser(User user) throws Exception{
		// TODO Auto-generated method stub
		return smsdao.saveUser(user);
		
	}
	@Override
	@Transactional
	public boolean saveStudentProfile(Student student) throws Exception{
		// TODO Auto-generated method stub
		return smsdao.saveStudentProfile(student);
		
	}
	@Override
	public Student getStudentProfile(String studentId) throws Exception {
		// TODO Auto-generated method stub
		return smsdao.getStudentProfile(studentId);
		
	}
	@Override
	public List<Role> getRoleList() throws Exception {
		// TODO Auto-generated method stub
		return smsdao.getRoleList();
	}

}
