package com.paincupid.springmvc.application.annotation.HelloExample;

import java.util.List;

import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.util.BaseJsonRst;

public class HelloWorldStub {  
	  
    @HelloWorldAnnotation(name = "ะกร๗")  
    public String sayHello(String name) {  
        if (name == null ) {  
            name = "";  
        }         
        return name + " say hello world!";  
    }
    
    @HelloWorldAnnotation(name = "123add")
    public BaseJsonRst<List<Person>> add(Person person){
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	
    	return ret;
    }
    @HelloWorldAnnotation(name = "456delete")
    public BaseJsonRst<List<Person>> delete(Person person){
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	
    	return ret;
    }
    
    @HelloWorldAnnotation(name = "update789")
    public BaseJsonRst<List<Person>> updatge(Person person){
    	BaseJsonRst<List<Person>> ret =  new BaseJsonRst<List<Person>>();
    	return ret;
    }
} 
