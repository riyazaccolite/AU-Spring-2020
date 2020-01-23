package com.AU.service;

import com.AU.beans.*;

public interface PersonService {

	public boolean addPerson(Person p);
	
	public boolean deletePerson(int id);
	
	public Person getPerson(int id);
	
	public Person[] getAllPersons();
}