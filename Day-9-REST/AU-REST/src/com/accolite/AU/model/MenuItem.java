package com.accolite.AU.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "menuitem")
public class MenuItem {
	private String name;
	private int id;
	
	public String getName(){ return this.name; }
	
	public int getId() { return this.id; }
	
	public void setName(String name) { this.name = name; }
	
	public void setId(int id) { this.id = id; }
	
	public String toString(){ return name; }
}
