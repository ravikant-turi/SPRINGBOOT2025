package com.java.micro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.micro.dto.EmployeeDto;
import com.java.micro.entity.Employee;
import com.java.micro.payload.ApiResponse;
import com.java.micro.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<ApiResponse<Employee>> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		ApiResponse<Employee> savedEmployeeApiResponse = this.employeeService.saveEmployee(employeeDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeApiResponse);

//		return new ResponseEntity<>(savedEmployeeApiResponse, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {

		ApiResponse<List<Employee>> allEmployeeApiResponse = this.employeeService.findAllEmployee();

		return ResponseEntity.status(HttpStatus.OK).body(allEmployeeApiResponse);
	}

	@GetMapping("{id}")
	public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable("id") String id) {
	    System.out.println("Fetching employee with ID: " + id);
		ApiResponse<Employee> foundApiResponse = this.employeeService.findEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(foundApiResponse);

	}

}
