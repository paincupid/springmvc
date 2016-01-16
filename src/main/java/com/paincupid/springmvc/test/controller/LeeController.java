package com.paincupid.springmvc.test.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paincupid.springmvc.test.domain.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/leeController")  
public class LeeController {
	private static final Logger logger = LoggerFactory.getLogger(LeeController.class);
	private TimeUnit lockTimeoutUnit = TimeUnit.SECONDS;
	public List<User> userList = new ArrayList<User>();
	//public User user;
	//public String userName;
	
	@RequestMapping(value = "/lee", method = RequestMethod.GET)
	public String lee(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate +"Lee !~~~~~~~~~~~~~~~~");
		ThreadLocal<Object> tl = new ThreadLocal<Object>(); 
		return "/lee";
	}
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public ModelAndView getList(String userName) {  
		System.out.println("userName is:"+userName);
		System.out.println("-----3-----");
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("userList",userList);
        return new ModelAndView("/lee",data);
	}
	
	@RequestMapping(path = "/addUser1", method = RequestMethod.POST)
    //public String addUser1(@PathVariable String userName,@PathVariable String password) {  
	public ModelAndView addUser1(String userName,String password) {
		System.out.println("come in 1");
        System.out.println("userName is:"+userName);  
        System.out.println("password is:"+password);
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        userList.add(user);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("userList",userList);
        return new ModelAndView("/lee",data);
    }  
    
	@RequestMapping(path = "/addUser2", method = RequestMethod.POST)
    public ModelAndView addUser2(HttpServletRequest request) {  
        String userName = request.getParameter("userName");  
        String password = request.getParameter("password");  
        System.out.println("userName is:"+userName);  
        System.out.println("password is:"+password);  
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        userList.add(user);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("userList",userList);
        return new ModelAndView("/lee",data);
    }  
    
    @RequestMapping(path = "/addUser3", method = RequestMethod.POST)
    public ModelAndView   addUser3(User user) {  
        System.out.println("userName is:"+user.getName());  
        System.out.println("password is:"+user.getPassword());  
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        userList.add(user);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("userList",userList);
        return new ModelAndView("/lee",data);
    }

	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
    
}
