package com.paincupid.springmvc.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paincupid.springmvc.test.controller.HomeController;
import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.test.service.PersonService;
import com.paincupid.springmvc.util.BaseJsonRst;
import com.paincupid.springmvc.util.CreatMockData;
import com.paincupid.springmvc.system.domain.SystemTree;

@Controller
@RequestMapping("/systemTree")
public class WifeTreeController {
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	PersonService personService;

	/**
	 * 在前台的访问路径为： http://localhost:8080/springmvc/wifeTree/init
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping("/wifeTreeInit")
	public String wifeTreeInit(Model model) {
		return "system/wifeTree";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addWifeTree", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public BaseJsonRst<List<SystemTree>> addWifeTree() {
		logger.info("获得系统菜单树");
		BaseJsonRst<List<SystemTree>> ret =  new BaseJsonRst<List<SystemTree>>();
		List<SystemTree> list = CreatMockData.mockSystemTreeWithOurUrl();
		return ret.success(list);

	}

}
