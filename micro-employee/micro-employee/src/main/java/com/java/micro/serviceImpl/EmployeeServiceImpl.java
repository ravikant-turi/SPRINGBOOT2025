package com.java.micro.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.micro.dto.EmployeeDto;
import com.java.micro.entity.Employee;
import com.java.micro.payload.ApiResponse;
import com.java.micro.repo.EmployeeRepo;
import com.java.micro.service.EmployeeService;

import jakarta.persistence.Entity;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse<Employee> saveEmployee(EmployeeDto employeeDto) {

		modelMapper.map(employeeDto, Employee.class);
		
		
		return null;
	}

	@Override
	public ApiResponse<Employee> findEmployeeById(int id) {
		
		return null;
	}

	@Override
	public List<ApiResponse<Entity>> findAllEmployee() {
		return null;
	}

}
