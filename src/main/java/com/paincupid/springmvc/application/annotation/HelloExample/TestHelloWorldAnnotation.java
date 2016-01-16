package com.paincupid.springmvc.application.annotation.HelloExample;

import java.lang.reflect.InvocationTargetException;  
import org.junit.Test;  
import static org.junit.Assert.assertEquals;  
  
public class TestHelloWorldAnnotation {  
  
    public static void main(String[] args) throws IllegalArgumentException,  
            IllegalAccessException, InvocationTargetException,  
            SecurityException, NoSuchMethodException, InstantiationException {  
          
        //定义操作类  
        ParseAnnotationStub parse = new ParseAnnotationStub();  
          
        String returnValue = parse.parseMethod(HelloWorldStub.class);  
        
        System.out.println(returnValue);  
  
    }  
}  