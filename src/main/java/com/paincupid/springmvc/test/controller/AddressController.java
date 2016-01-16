package com.paincupid.springmvc.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paincupid.springmvc.test.domain.Person;

@Controller( "addressController")
@RequestMapping( "/address")
public class AddressController {

	@RequestMapping( "/show")
    public  String show(Person person, Model model){
		System.out.println("addree show method");
		Date date = new Date();
        SimpleDateFormat format =new SimpleDateFormat("yy/MM/dd HH:mm"); 
		String formattedDate = format.format(date);
		model.addAttribute("serverTime", formattedDate );
		return   "address";
  }

}
