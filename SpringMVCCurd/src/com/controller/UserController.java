package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.User;
import com.service.UserService;

@Controller
public class UserController 
{ 
 @Autowired
 private UserService service;
	
@RequestMapping("/") 
 public String landingPage(){
	System.out.println("Login Controller");
	 return "login";
 }
@RequestMapping("/registration") 
public String registerPage(){
	 return "registration";
}
 
	@RequestMapping("/reg")
	public String addUser(@ModelAttribute User user)
	{
		
		System.out.println("--USER CONTROLLER--");
		System.out.println(user.getName());
		
		int id=service.saveUser(user);
		if(id>0){
			return "login";
		}
		else
		{
			return "registration";
		}
	}
	
	
	@RequestMapping("/login")
	public String authenticationCheck(HttpServletRequest request,HttpServletResponse response,User user,ModelMap m)
	{
		System.out.println(request.getParameter("uname"));
		System.out.println("Login Controler");
		user.setUname(request.getParameter("uname"));
		user.setPassword(request.getParameter("password"));
		System.out.println(user.getUname());
		System.out.println(user.getPassword());
	
		int id= service.loginCheck(user);
		
		List<User> list=service.displayAll(user);
		for (User user2 : list) {
			System.out.println(user2.getName());
			System.out.println(user2.getMobileno());
			System.out.println(user2.getUid());
			System.out.println(user2.getUname());
			System.out.println(user2.getPassword());
			
		}
		
		if(id>0)
		{
			m.addAttribute("data", list);
			return "success";
			//return new ModelAndView("success","data",list);
			}
		else{
			//return new ModelAndView("login","msg","UNAUTORIZED USER...");
			return "login";
		}
		
	}
	
	@RequestMapping("/delete")
	public String deleteUser(@ModelAttribute User user,ModelMap m){
		
		System.out.println(user.getUid());
		int id=service.deleteUser(user);
		List<User> list=service.displayAll(user);
		if(id>0)
		{
			m.addAttribute("data", list);
			return "success";
			//return new ModelAndView("success","data",list);
		}
		else{
			m.addAttribute("data", list);
			return "success";
			//return new ModelAndView("success","data",list);
		}
		
	}
	
	@RequestMapping("/edit")
	public String editUser(@ModelAttribute User user,ModelMap m)
	{
	User user1=service.editUser(user);
	List<User> list=service.displayAll(user);
	if(user1!=null)
	{
		m.addAttribute("data", user1);
		return "edit";
		//return new ModelAndView("edit","data",user1);
	}
	else{
		m.addAttribute("data", list);
		return "success";
		//return new ModelAndView("success","data",list);
	}
	
	}
	
	@RequestMapping("/update")
	public String updateUser(@ModelAttribute User user,ModelMap m)
	{
		int id=service.updateUser(user);
		List<User> list=service.displayAll(user);
		if(id>0)
		{
			m.addAttribute("data", list);
			return "success";
		//	return new ModelAndView("success","data",list);

		}
		m.addAttribute("data", user);
		return "edit";
	
		//	return new ModelAndView("edit","data",user);
	}
	
}
