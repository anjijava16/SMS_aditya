package com.sms.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sms.bean.Course;
import com.sms.bean.Student;
import com.sms.service.SMSService;

@Controller
public class SMSStudentController{
	@Autowired 
	SMSService smsService;  

	//Get the path from properties file
	@Value("${UPLOAD.DIRPATH}")
	private String directoryPath;
	@RequestMapping("/profile")
	public String getMyProfile(HttpServletRequest request,Model model) throws Exception {
		
		List<Course> courselist= smsService.getCourseList();
		String parentId =null;
		String studentId = (String)request.getSession().getAttribute("username");
		List<Student> students = smsService.getStudentProfile(studentId,parentId);
		 model.addAttribute("userCourseList",courselist);
		 if(null != students && !students.isEmpty() && students.get(0)!= null){
			 model.addAttribute("filePath",directoryPath);
		 model.addAttribute("selectStudent", students.get(0));
		 model.addAttribute("studentViewPage", "studentViewPage");
		 }
		model.addAttribute("includePage", "myProfile");

		return "home"; 

	}
	
	@RequestMapping(value ="/saveStdntDetails")
	 public ModelAndView saveStudentProfile(HttpServletRequest request,
				HttpServletResponse response , @ModelAttribute("student") Student student,Model model)throws Exception {  
		System.out.println("Upload");
		String fileName="";
		student.setStudentID((String)request.getSession().getAttribute("username"));
		if(student.getImage()!=null){
			fileName = student.getImage().getOriginalFilename();
			student.setFileName(fileName);
		}
		  boolean flag = smsService.saveStudentProfile(student); 
		  
		  if(flag){
			 saveImage(student.getStudentID() + "_"+fileName, student.getImage());
			 model.addAttribute("includePage", "ProfileSunbmitted");
		  }
		  return new ModelAndView("home");
		 }
	
	private void saveImage(String filename, MultipartFile image)throws RuntimeException, IOException {
		try {
		File file = new File(directoryPath + filename);
		 
		FileUtils.writeByteArrayToFile(file, image.getBytes());
		
		} catch (IOException e) {
		throw e;
		}
}

	
}