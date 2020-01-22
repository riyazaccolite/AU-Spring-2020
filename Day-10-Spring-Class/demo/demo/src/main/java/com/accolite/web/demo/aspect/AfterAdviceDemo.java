package com.accolite.web.demo.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterAdviceDemo {
	
	@AfterReturning("execution(* com.accolite.web.demo.services.UserService.*(..))")
	public void doLogAfterService() {
		System.out.println("RETURNED HELLO");
	}
}
