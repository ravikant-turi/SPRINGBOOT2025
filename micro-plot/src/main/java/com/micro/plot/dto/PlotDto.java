package com.micro.plot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlotDto {

	@NotBlank(message = "Area can not be empty or null")
	@Size(min = 2, max = 50, message = "Area  must be between 2 to 50 character")
	private String area;

	@NotBlank(message = "coloneyName can not be empty or null")
	@Size(min = 2, max = 100, message = "coloneyName must be between 20 to 100 character")
	private String coloneyName;

	@NotBlank(message = "Area can not be null")
	@Size(min = 30, max = 100, message = "Area must be between 20 to 100 character")
	private String cityName;

	@Min(value = 1000, message = "PinCode must exactly 6 digit")
	@Max(value = 200, message = "PinCode must exactly 6 digit")
	private int pincode;

	@NotBlank(message = "EmployeeId can not be null or empty")
	private String employeeId;

}
