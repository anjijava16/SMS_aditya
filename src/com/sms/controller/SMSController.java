package com.sms.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sms.bean.Course;
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
				 return new ModelAndView("home"); 

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
		 
	  return "home"; 
	 }
	
	 @RequestMapping("/editProfile")  
	 public String editProfile(Model model) throws Exception {  

		 model.addAttribute("includePage", "editProfile");
		 
	  return "home"; 
	 } 
	
	@RequestMapping("/assignteacher")
	public ModelAndView assignteacher(Model model) throws Exception {
		model.addAttribute("includePage", "defaultHome");
		return new ModelAndView("home");

	}
	
	@RequestMapping("/examschedule")
	public ModelAndView examSchedule(Model model) throws Exception {
		model.addAttribute("includePage", "defaultHome");
		return new ModelAndView("home");

	}
	
	@RequestMapping("/getAllProfile")
	public ModelAndView getAllProfile(HttpServletRequest req,
			@RequestParam("courseCode") String courseCde,@RequestParam("status") String status,Model model) throws Exception {
		List<Student> profileList = smsService.getSearchedProfile(courseCde,status);
		model.addAttribute("includePage", "getAlProfile");
		req.getSession().setAttribute("profileList", profileList);
		model.addAttribute("profileList", profileList);
		return new ModelAndView("home");

	}
	@RequestMapping("/viewProfile")
	public ModelAndView viewProfile(HttpServletRequest req,@RequestParam("studentId") String studentId,Model model) throws Exception {
		List<Student> profileList = (List<Student>)req.getSession().getAttribute("profileList");
		
		Student selStudent = null;
		if(null != profileList && !profileList.isEmpty()){
			
			for(Student student : profileList){
				
				if(student.getStudentID().equals(studentId)){
					selStudent = student;
					break;
				}
			}
			
		}
		if("view".equals(req.getParameter("profileView"))){
			model.addAttribute("includePage", "viewOnlyPage");
		}else{
		model.addAttribute("includePage", "editProfile");
		model.addAttribute("homeEditPage","homeEditPage");
		}
		model.addAttribute("filePath",directoryPath);
		model.addAttribute("selectStudent", selStudent);
		
		return new ModelAndView("home");

	}
	@RequestMapping("/myclass")
	public ModelAndView getCourseDetails(HttpServletRequest request, Model model) throws Exception {
		String studentId = (String)request.getSession().getAttribute("username");
		List<List<String>> coursList = smsService.getCourseDetails(studentId);
			model.addAttribute("includePage", "MyClassDetails");
		    model.addAttribute("mycoursList", coursList);
		
	return new ModelAndView("home");

	}
	@RequestMapping("/viewprofilebyparent")
	public ModelAndView viewprofilebyparent(HttpServletRequest request, Model model) throws Exception {
		String studentId=null;
		String parentId = (String)request.getSession().getAttribute("username");
		List<Student> profileList = smsService.getStudentProfile(studentId,parentId);
		model.addAttribute("includePage", "viewListPage");
		
		if(null!= profileList && !profileList.isEmpty()){
		model.addAttribute("profileViewList", profileList);
		request.getSession().setAttribute("profileList", profileList);
		}
		
	return new ModelAndView("home");

	}
	
	@RequestMapping("/approveProfile")
	public ModelAndView approveProfiles(HttpServletRequest request, Model model) throws Exception {
		String[] studentIds = request.getParameterValues("approvedProf");
		if(studentIds.length > 0){
		//List<List<String>> coursList = smsService.getCourseDetails(studentId);
		boolean flag = smsService.approveProfiles(studentIds);
		model.addAttribute("includePage", "ProfileSunbmitted");
		}else{
			model.addAttribute("includePage", "atLeastOneChkBoxselet");
			
		}
		return new ModelAndView("home");

	}
}