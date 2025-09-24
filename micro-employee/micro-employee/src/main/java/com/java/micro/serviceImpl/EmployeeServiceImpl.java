package com.java.micro.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.micro.dto.EmployeeDto;
import com.java.micro.entity.Employee;
import com.java.micro.exceptions.ResourceNotFoundException;
import com.java.micro.payload.ApiResponse;
import com.java.micro.repo.EmployeeRepo;
import com.java.micro.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse<Employee> saveEmployee(EmployeeDto employeeDto) {

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		employee.setId(UUID.randomUUID().toString());

		employee.setDateTime(LocalDateTime.now().toString());

		Employee savedEmployee = this.employeeRepo.save(employee);

		return new ApiResponse<>("SUCCESS", "EMPLOYEE_DATA_CREATED", savedEmployee);
	}

	@Override
	public ApiResponse<Employee> findEmployeeById(String id) {

		Employee foudnEmployee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee is not Found with this id :" + id));
		return new ApiResponse<Employee>("SUCCUSS", "EMPLOYEE_DATA_FOUND", foudnEmployee);
	}

	@Override
	public ApiResponse<List<Employee>> findAllEmployee() {

		List<Employee> emplList = this.employeeRepo.findAll();

		if (emplList.isEmpty()) {

			return new ApiResponse<List<Employee>>("SUCCESS", "EMPLOYEE_DATA_NOT_FOUND", emplList);

		}

		return new ApiResponse<List<Employee>>("SUCCESS", "EMPLOYEE_DATA_FOUND", emplList);
	}
}
