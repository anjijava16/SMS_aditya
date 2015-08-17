package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sms.bean.Course;
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
	public List<Student> getStudentProfile(String studentId, String parentId) throws Exception {
		// TODO Auto-generated method stub
		return smsdao.getStudentProfile(studentId,parentId);
		
	}
	@Override
	public List<Role> getRoleList() throws Exception {
		// TODO Auto-generated method stub
		return smsdao.getRoleList();
	}
	@Override
	public List<List<String>> getCourseDetails(String studentId) {
		// TODO Auto-generated method stub
		return smsdao.getCourseDetails(studentId);
	}
	@Override
	public List<Course> getCourseList() throws Exception {
		// TODO Auto-generated method stub
		return smsdao.getCourseList();
	}
	@Override
	public List<Student> getSearchedProfile(String code, String status)
			throws Exception {
		// TODO Auto-generated method stub
		return smsdao.getSearchedProfile(code, status);
	}
	@Override
	public boolean approveProfiles(String[] studentIds) {
		// TODO Auto-generated method stub
		return smsdao.approveProfiles(studentIds);
	}

}
