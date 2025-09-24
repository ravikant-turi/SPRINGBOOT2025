package com.java.micro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.micro.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String>{

}
