package com.accolite.AU.service;

import com.accolite.AU.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

 
@Path("/menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuServiceImpl implements MenuService {

	private static Map<Integer,MenuList> lists = new HashMap<Integer,MenuList>();
	
	@Override
	@POST
    @Path("/add")
	public Response addMenuList(MenuList list) {
		GenericResponse response = new GenericResponse();
		if(lists.get(list.getId()) != null){
			response.setStatus(false);
			response.setMessage("List Already Exists");
			response.setErrorCode("L-01");
			return Response.status(422).entity(response).build();
		}
		lists.put(list.getId(), list);
		response.setStatus(true);
		response.setMessage("List created successfully");
		return Response.ok(response).build();
	}

	@Override
	@POST
    @Path("/add/{id}")
	public Response addMenuItem(@PathParam("id") int menuListId, MenuItem item) {
		GenericResponse response = new GenericResponse();
		if(lists.get(menuListId) == null){
			response.setStatus(false);
			response.setMessage("List Doesn't Exists");
			response.setErrorCode("L-02");
			return Response.status(404).entity(response).build();
		}
		MenuList list = lists.get(menuListId);
		if(list.getItems().get(item.getId()) != null){
			response.setStatus(false);
			response.setMessage("Item already Exists in the list");
			response.setErrorCode("L-02");
			return Response.status(422).entity(response).build();
		}
		list.addItem(item.getId(), item);
		lists.put(list.getId(), list);
		response.setStatus(true);
		response.setMessage("Item added successfully");
		return Response.ok(response).build();
	} 
	
	@Override
	@DELETE
    @Path("/delete/{id}")
	public Response deleteList(@PathParam("id") int id) {
		GenericResponse response = new GenericResponse();
		if(lists.get(id) == null){
			response.setStatus(false);
			response.setMessage("List Doesn't Exists");
			response.setErrorCode("L-03");
			return Response.status(404).entity(response).build();
		}
		lists.remove(id);
		response.setStatus(true);
		response.setMessage("List deleted successfully");
		return Response.ok(response).build();
	}

	@Override
	@DELETE
    @Path("/delete/{id}/{itemid}")
	public Response deleteItem(@PathParam("id") int id, @PathParam("itemid") int itemid) {
		GenericResponse response = new GenericResponse();
		if(lists.get(id) == null){
			response.setStatus(false);
			response.setMessage("List Doesn't Exists");
			response.setErrorCode("L-03");
			return Response.status(404).entity(response).build();
		}
		MenuList list = lists.get(id);
		if(list.getItem(itemid) == null) {
			response.setStatus(false);
			response.setMessage("Item Doesn't Exists");
			response.setErrorCode("L-04"); 
			return Response.status(404).entity(response).build();
		}
		list.removeItem(itemid);
		lists.put(list.getId(),list);
		response.setStatus(true);
		response.setMessage("List Item deleted successfully");
		return Response.ok(response).build();
	}
	
	@Override
	@GET
	@Path("/get/{id}")
	public MenuList getList(@PathParam("id") int id) {
		System.out.println("HELLO");
		System.out.println(lists.get(id).getItems());
		return lists.get(id);
	}

	@Override
	@GET
	@Path("/getAll")
	public MenuList[] getAllLists() {
		Set<Integer> ids = lists.keySet();
		MenuList[] allLists = new MenuList[ids.size()];
		int i=0;
		for(Integer id : ids){
			allLists[i] = lists.get(id);
			i++;
		}
		return allLists;
	}

}

