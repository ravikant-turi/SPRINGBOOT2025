package com.micro.plot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plots")
public class Plot {
	@Id
	private String id;

	@Column(length = 30)
	private String area;

	@Column(length = 100)
	private String coloneyName;

	@Column(length = 100)
	private String cityName;

	private int pincode;

	@NotBlank
	private String dateTime;
	
	private String employeeId;
}
