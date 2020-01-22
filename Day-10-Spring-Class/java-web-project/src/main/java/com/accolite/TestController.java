package com.accolite;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class TestController {
	
	@RequestMapping(value="hello", method = RequestMethod.GET)
	public @ResponseBody String sayHi() {
		return "Hello. Welcome to first controller";
	}
	
	@RequestMapping(value="goodbye")
	public @ResponseBody String sayBye() {
		return "Bye";
	}
}

