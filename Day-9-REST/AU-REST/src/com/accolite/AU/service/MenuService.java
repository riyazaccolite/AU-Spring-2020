package com.accolite.AU.service;

import javax.ws.rs.core.Response;
import com.accolite.AU.model.*; 

public interface MenuService {
	public Response addMenuList(MenuList list);
	public Response addMenuItem(int menuListId, MenuItem item);
	
	public Response deleteItem(int menuId, int itemId);
	public Response deleteList(int id);
	
	public MenuList getList(int id);
	
	public MenuList[] getAllLists();
}
