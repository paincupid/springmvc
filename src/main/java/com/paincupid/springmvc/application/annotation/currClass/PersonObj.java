package com.paincupid.springmvc.application.annotation.currClass;

import java.util.List;

import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.util.BaseJsonRst;

public class PersonObj extends BaseLog{  
	  
    public BaseJsonRst<List<Person>> search(String id) {  
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	return ret;
    }
    
	@OpLogger(id = "123add")
    public BaseJsonRst<List<Person>> add(Person person){
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	logger();
    	return ret;
    }
	@OpLogger(id = "456delete")
    public BaseJsonRst<List<Person>> delete(Person person){
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	
    	return ret;
    }
    
	@OpLogger(id = "update789")
    public BaseJsonRst<List<Person>> updatge(Person person){
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	return ret;
    }
} 
