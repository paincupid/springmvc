package com.paincupid.springmvc.json.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.ibatis.builder.ParameterExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.util.BaseJsonRst;
import com.paincupid.springmvc.util.CreatMockData;

@Controller
@RequestMapping("/jqueryFormPlugin")
public class JqueryFormPluginController {
	private static final Logger log = LoggerFactory.getLogger(JqueryFormPluginController.class);
	
	/**
	 * 在前台的访问路径为： http://localhost:8080/springmvc/jqueryFormPlugin/list
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String listPerson(Person person, Model model) {
		
		return "json/jqueryFormPlugin";
	}
	
	/**
	 * 请求接收的是一个Java类
	 * @param person
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getForm", method=RequestMethod.POST)
	public BaseJsonRst<List<Person>> getForm(Person person){
		log.info("\r\nid: "+person.getId()+", name: "+person.getName()+", comment: "+person.getComment());
		BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
		List<Person> list = CreatMockData.createPersonList(10);
		return ret.success(list);
	}
	
	
}