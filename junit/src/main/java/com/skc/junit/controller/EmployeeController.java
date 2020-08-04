package com.skc.junit.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skc.junit.dao.EmployeeDAO;
import com.skc.junit.model.Employee;
import com.skc.junit.model.Employees;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDao;

	@GetMapping(produces = "application/json")
	public Employees getEmployees() {
		
		Employees employees = new Employees();
		employees.setEmployeeList(employeeDao.findAll());
		return employees;
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) throws Exception {
		Employee emp = employeeDao.save(employee);

		// Create resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emp.getId())
				.toUri();

		// Send location in response
		return ResponseEntity.created(location).build();
	}
}
