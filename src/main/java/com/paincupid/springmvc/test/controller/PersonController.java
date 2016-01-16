package com.paincupid.springmvc.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.test.service.PersonService;

@Controller( "personController")
@RequestMapping( "/person")
public   class  PersonController{
    @Autowired
    PersonService personService;   
    
    @RequestMapping( "/list")
      public  String listPerson(Person person, Model model){
    	List<Person> personList   =  personService.listPerson(person);
        model.addAttribute( "personList", personList);
        
        return   "listperson";
    }
    
    
    /*@RequestMapping( "/save.action")
      public  String savePerson(Person person){
        person.setPid(UUID.randomUUID().toString());
          this.personService.savePerson(person);
          return   "listperson";
    }
    @RequestMapping( "/view.action")
      public  String viewPerson(String pid, Model model){
        Person p   =   this.personService.findPersonById(pid);
        model.addAttribute( "p", p);
          return   "viewperson";
    }
    @RequestMapping( "/updatePerson.action")
      public  String updatePerson(Person person, RedirectAttributes att){
          this.personService.updatePerson(person);
          //控制器之间带参数的重定向跳转
        att.addAttribute( "name", person.getName());
          return   "redirect:list.action";
    }*/
}
