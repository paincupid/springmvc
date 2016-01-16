package com.paincupid.springmvc.application.annotation.StudentClassExample;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Student{
    String name() default "";
    int age() default -1;
}