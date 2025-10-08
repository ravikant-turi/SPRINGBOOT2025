package com.micro.account.dto.external;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
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
public class EmployeeResponse {
	@Id
	private String id;
	
	@Column(length = 30)
	private String name;
	
	private double salary;
	@Column(length = 300)
	
	private String address;
	
	private String dateTime;

}
