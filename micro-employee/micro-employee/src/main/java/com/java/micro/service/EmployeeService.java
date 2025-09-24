package com.java.micro.service;

import java.util.List;

import com.java.micro.dto.EmployeeDto;
import com.java.micro.entity.Employee;
import com.java.micro.payload.ApiResponse;


public interface EmployeeService {

	ApiResponse<Employee> saveEmployee(EmployeeDto dto);

	ApiResponse<Employee> findEmployeeById(String id);

	ApiResponse<List<Employee>> findAllEmployee();

}
