package com.paincupid.springmvc.application.annotation.StudentClassExample;

@Student(name="xingoo")
public class StudentTest {
	
	public static void query(Class c){
		System.out.println(c.getName());
		Student stu = (Student)c.getAnnotation(Student.class);

		if(stu != null){
            System.out.println("name:"+stu.name()+", age:"+stu.age());
        }else if(stu == null){
            System.out.println("student unknown!" + stu);
        }
	}
     public static void main(String[] args){
    	 StudentTest.query(StudentTest.class);
     }

 
}