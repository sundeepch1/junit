package com.skc.junit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.skc.junit.model.Employee;

@SpringBootTest
class EmployeeControllerTest {

	@Autowired
	private EmployeeController controller;
	
	@Test
	@Disabled
	public void testGetConEmployeeListSuccess() throws URISyntaxException {
		
		// Verify request succeed
		//assertEquals(200, result.getStatusCodeValue());
		//Employees employees = 
		System.out.println();
		assertEquals(true, controller.getEmployees());
	}
	
	@Test
	//@Disabled
	public void testGetEmployeeListSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 8080 + "/employees";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody().contains("employeeList"));
	}

	@Test
	@Disabled
	public void testAddEmployeeMissingHeader() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + 8080 + "/employees/";
		URI uri = new URI(baseUrl);
		Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

		try {
			ResponseEntity<String> entity =restTemplate.postForEntity(uri, request, String.class);
			System.out.println(entity);
			//fail();
		} catch (HttpClientErrorException ex) {
			// Verify bad request and missing header
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
}
