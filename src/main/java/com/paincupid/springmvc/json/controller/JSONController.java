package com.paincupid.springmvc.json.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.test.service.PersonService;
import com.paincupid.springmvc.util.BaseJsonRst;

@Controller
@RequestMapping("/json")
public class JSONController {
	@Autowired
	PersonService personService;

	// @RequestMapping(value="{name}", method = RequestMethod.GET)
	@ResponseBody
	@RequestMapping(value = "/getPerson", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public Person getShopInJSON(@PathVariable String name) {
		Person p = new Person();
		p.setId("123");
		p.setName("nameaa");
		return p;
	}
	
	/**
	 * 在前台的访问路径为： http://localhost:8080/springmvc/json/list
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String listPerson(Person person, Model model) {
		List<Person> personList = personService.listPerson(person);
		model.addAttribute("personList", personList);
		return "json/listJson";
	}
	
	/**
	 * Ajax请求
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public BaseJsonRst<List<Person>> getList() {
		Person p = new Person();
		List<Person> personList = personService.listPerson(p);
		System.out.println("personList size: "+personList.size());
		BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
		ret.setSuccess(true);
		ret.setResult(personList);
		return ret;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getClass", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public BaseJsonRst<Person> getVO() {
		BaseJsonRst<Person> ret = new BaseJsonRst<Person>();
		Person p = new Person();
		p.setId("1");
		p.setName("lee李");
		ret.setSuccess(true);
		ret.setResult(p);
		return ret;
	}
}