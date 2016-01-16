package com.paincupid.springmvc.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paincupid.springmvc.test.domain.Person;
import com.paincupid.springmvc.test.persistence.PersonMapper;


@Service(value =  "personService")
public class PersonService {
	@Autowired
	private PersonMapper mapper;
	
	public List<Person> listPerson(Person persion){
		List<Person> list = mapper.listPerson(persion);
		return list;
	}
	
}
