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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sms.bean.Role;
import com.sms.bean.Student;
import com.sms.bean.User;
import com.sms.service.SMSService;

@Controller
public class SMSController{
	 @Autowired 
	 SMSService smsService;  
	 
	//Get the path from properties file
	 @Value("${UPLOAD.DIRPATH}")
	 private String directoryPath;
	 
	 @RequestMapping("/home") 
	public ModelAndView doLogin(HttpServletRequest request,
		HttpServletResponse response, @RequestParam("username") String username,
		@RequestParam("password") String password,Model model) throws Exception {
		 String page = "";
		 
			 List<List<String>> userList = smsService.validateUser(username,password); 

			 if(null != userList && !userList.isEmpty()){
				 page ="defaultHome";
				 model.addAttribute("includePage", page);
				 request.getSession().setAttribute("username", username);
				 request.getSession().setAttribute("userList", userList);
				 return new ModelAndView("admin"); 

			 }else{
				 model.addAttribute("invalidUser", "Password Incorrect");
			 }

		 
		 return new ModelAndView("login");

	 }
	 
	 @RequestMapping("/login") 
		public String getHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 
			return "login";
	 
		}
	 
	 @RequestMapping("/logout") 
		public String logOut(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		 request.getSession().invalidate();
		 return "login";

	 }
	 @RequestMapping("/registerProfile")  
	 public String saveUser( @ModelAttribute("user") User user,Model model) throws Exception {  
	  boolean flag = smsService.saveUser(user);  
	  if(true){
		  model.addAttribute("includePage", "UserRegisterd");
		  
	  }else{
		  List<Role>  rolelist=smsService.getRoleList();
	         model.addAttribute("userRoleList",rolelist);
			 model.addAttribute("includePage", "registerProfile");
	  }
	  return "home"; 
	 }
	 
	 @RequestMapping("/registerUser")  
	 public String registerProfile(Model model) throws Exception {  
		 List<Role>  rolelist=smsService.getRoleList();
         model.addAttribute("userRoleList",rolelist);
		 model.addAttribute("includePage", "registerProfile");
		 
	  return "admin"; 
	 }
	
	 @RequestMapping("/editProfile")  
	 public String editProfile(Model model) throws Exception {  

		 model.addAttribute("includePage", "editProfile");
		 
	  return "admin"; 
	 } 
	@RequestMapping(value ="saveStdntDetails")
	 public ModelAndView saveStudentProfile(HttpServletRequest request,
				HttpServletResponse response , @ModelAttribute("student") Student student,Model model)throws Exception {  
		System.out.println("Upload");
		String fileName="";
		student.setStudentID((String)request.getSession().getAttribute("username"));
		if(student.getImage()!=null){
			fileName = student.getImage().getOriginalFilename();
			student.setFileName(student.getImage().getName());
		}
		  boolean flag = true;//smsService.saveStudentProfile(student); 
		  
		  if(flag){
			 saveImage(student.getStudentID() + "_"+fileName, student.getImage());
			 model.addAttribute("includePage", "ProfileSunbmitted");
		  }
		  return new ModelAndView("admin");
		 } 
	private void saveImage(String filename, MultipartFile image)throws RuntimeException, IOException {
			try {
			File file = new File(directoryPath + filename);
			 
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			
			} catch (IOException e) {
			throw e;
			}
	}

	@RequestMapping("/sms/getStdntdetails")
	public ModelAndView getStudentProfile(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("studentID") String studentId,Model model) throws Exception {
		model.addAttribute("includePage", "studentEditPage");
		Student student = smsService.getStudentProfile(studentId) ;
		return new ModelAndView("admin", "studentProfile", student);

	}
	/*
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		return new ModelAndView("CustomerPage", "msg","list() method");
 
	}
 */
}