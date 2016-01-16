package com.paincupid.springmvc.test.persistence;

import java.util.List;

import com.paincupid.springmvc.test.domain.Person;

public interface PersonMapper {
	List<Person> listPerson(Person persion);
	
}
