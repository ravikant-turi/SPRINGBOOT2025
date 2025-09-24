package com.java.micro.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	@NotBlank(message = "Name canot be empty")
	@Size(max = 30, min = 2, message = "name should be between 2 to 30 character")
	private String name;

	@DecimalMin(value= "1000.0" , message="Salary must be atleast 1000.0")
	@DecimalMax(value= "10000.0" , message="Salary must not exceed 10000.0")
	private double salary;

	@NotBlank(message = "Address can not be the empty or blanck")
	@Size(min=2 , max=300 , message="Name must be between 2 to 300 chars")
	private String address;

}
