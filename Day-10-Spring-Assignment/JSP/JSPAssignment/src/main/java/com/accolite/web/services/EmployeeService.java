package com.accolite.web.services;

import org.springframework.stereotype.Service;

import com.accolite.web.models.Employee;

import java.util.*;

import javax.annotation.PostConstruct;

@Service
public class EmployeeService {

	List<Employee> employeeList;
	
	@PostConstruct
	void setUp() {
		employeeList = new ArrayList<Employee>();
		Employee temp = new Employee();
		temp.setEmployeeId(1);
		temp.setEmployeeName("Name1");
		temp.setSalary(123123);
		employeeList.add(temp);
	}
	
	public Employee getEmployee(int id) {
		return employeeList.stream().filter( employee -> employee.getEmployeeId() == id).findFirst().get();
	}
	
	public List<Employee> getAll() {
		return employeeList;
	}
	
}
