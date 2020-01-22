package com.accolite.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.accolite.web.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		try {
			response.getWriter().write("This response is from the servlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		User myUser = new User();
		myUser.setName("NameTemp");
		myUser.setAge(10);
		myUser.setId(1231);
		String str = mapper.writeValueAsString(myUser);
		response.getWriter().write(str);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
}

