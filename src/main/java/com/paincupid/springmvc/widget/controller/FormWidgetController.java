package com.paincupid.springmvc.widget.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paincupid.springmvc.system.domain.UserRole;
import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.util.BaseJsonRst;
import com.paincupid.springmvc.util.CreatMockData;

@Controller
@RequestMapping("/formWidget")
public class FormWidgetController {
	private static final Logger log = LoggerFactory.getLogger(FormWidgetController.class);
	
	/**
	 * 在前台的访问路径为： http://localhost:8080/springmvc/formWidget/view
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String view() {
		return "widget/formWidget";
	}
	
	/**
	 * 请求接收的是一个Java类
	 * @param person
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getForm", method=RequestMethod.POST)
	public BaseJsonRst<List<Person>> getForm(Person person, @RequestParam("currentPage") int currentPage){
		log.info("\r\nid: "+person.getId()+", name: "+person.getName()+", currentPage: "+currentPage);
		BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
		List<Person> list = CreatMockData.createPersonList(20,currentPage);
		ret.setResult(list);
		ret.setTotalCounts(250);
		ret.setCurrentPage(person.getCurrentPage());
		ret.setSuccess(true);
		return ret;
	}
	
	/**
	 * 百度下拉提示框
	 **/
	@ResponseBody
	@RequestMapping(value="/getUserRoleDropDownTips", method=RequestMethod.POST)
	public BaseJsonRst<List<UserRole>> getUserRoleDropDownTips(@RequestParam("personId") String personId){
		log.info("\r\ngetDropDownTips come in");
		BaseJsonRst<List<UserRole>> ret =  new BaseJsonRst<List<UserRole>>();
		List<UserRole> list = CreatMockData.createUserRole(20, personId);
		ret.setResult(list);
		ret.setSuccess(true);
		return ret;
	}
	/**
	 * 模拟所有person的下拉列表，id隐藏，姓名显示
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPersonDropDownTips", method=RequestMethod.GET)
	public BaseJsonRst<List<Person>> getPersonDropDownTips(){
		log.info("\r\n getPersonDropDownTips come in");
		BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
		List<Person> list = CreatMockData.createPersonDropDownList(22);
		ret.setResult(list);
		ret.setSuccess(true);
		return ret;
	}
	
	public static void main(String[] args){
	}
}