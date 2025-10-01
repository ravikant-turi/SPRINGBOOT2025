package com.micro.plot.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotDto {

	@NotBlank(message = "Area can not be empty or null")
	@Size(min = 2, max = 50, message = "Area  must be between 2 to 50 character")
	private String area;

	@NotBlank(message = "coloneyName can not be empty or null")
	@Size(min = 2, max = 100, message = "coloneyName must be between 2 to 100 character")
	private String coloneyName;

	@NotBlank(message = "Area can not be null")
	@Size(min = 2, max = 100, message = "Area must be between 2 to 100 character")
	private String cityName;

	@Pattern(regexp = "\\d{6}", message = "PinCode must be exactly 6 digits")
	private String pincode;

	@NotBlank(message = "EmployeeId can not be null or empty")
	private String employeeId;

}
