package com.accolite.AU.SpringAssignment.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	@XmlAttribute(name = "id")
	private int id;
	
	@XmlElement(name = "age")
	private int age;
	
	@XmlElement(name = "name")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object user) {

		if (user == null)
			return false;
		if (this.getClass() != user.getClass())
			return false;
		User temp = (User)user;
		if(temp.getId() == this.getId())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
