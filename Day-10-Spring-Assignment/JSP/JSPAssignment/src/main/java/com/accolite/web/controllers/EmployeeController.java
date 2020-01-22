package com.accolite.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.accolite.web.models.Employee;
import com.accolite.web.services.EmployeeService;

@RestController
public class EmployeeController {
	private final EmployeeService employeeService;
	
	EmployeeController(EmployeeService employeeService) { this.employeeService = employeeService; }
	
	@GetMapping("employee/{id}")
	public ModelAndView getEmployee(@PathVariable int id) { 
		ModelAndView model = new ModelAndView("employee");
		model.addObject("employee",employeeService.getEmployee(id));
		return model;
	}
	
	@GetMapping("/employee")
	public ModelAndView getAllEmployees() {
		ModelAndView model = new ModelAndView("employee");
		model.addObject("employees",employeeService.getAll());
		return model;
	}
	
	@GetMapping("/add")
	public ModelAndView addEmployeeForm() {
		ModelAndView model = new ModelAndView("AddEmployee");
		return model;
	}
	 
	@PostMapping("/add")
	public ModelAndView addEmployee(@RequestParam int employeeId,@RequestParam String employeeName, @RequestParam int salary) {
		Employee temp = new Employee();
		temp.setEmployeeId(employeeId);
		temp.setSalary(salary);
		temp.setEmployeeName(employeeName);
		employeeService.getAll().add(temp);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("employees",employeeService.getAll());
		return model;
	}
}
