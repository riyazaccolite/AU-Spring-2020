package com.accolite.AU.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "menulist")
public class MenuList {
	private String name;
	private int id;
	private Map<Integer,MenuItem> items = new HashMap<Integer,MenuItem>();
	
	public String getName(){ return this.name; }
	
	public int getId() { return this.id; }
	
	public Map<Integer,MenuItem> getItems() { return this.items; }
	
	public void setName(String name) { this.name = name; }
	
	public void setId(int id) { this.id = id; }
	
	public void setItems(Map<Integer,MenuItem> items) { this.items = items; } 
	
	public String toString() { return this.name; }
	
	public MenuItem getItem(int id) { return items.get(id); }
	
	public void addItem(int id, MenuItem item) { items.put(id, item); }
	
	public void removeItem(int id){ items.remove(id); }
}
