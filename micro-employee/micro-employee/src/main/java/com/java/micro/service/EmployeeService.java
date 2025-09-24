package com.java.micro.service;

import java.util.List;

import com.java.micro.dto.EmployeeDto;
import com.java.micro.entity.Employee;
import com.java.micro.payload.ApiResponse;

import jakarta.persistence.Entity;

public interface EmployeeService {

	ApiResponse<Employee> saveEmployee(EmployeeDto dto);

	ApiResponse<Employee> findEmployeeById(int id);

	List<ApiResponse<Entity>> findAllEmployee();

}
