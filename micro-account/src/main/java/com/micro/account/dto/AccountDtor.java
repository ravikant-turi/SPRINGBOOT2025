package com.micro.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtor {
	
	@NotBlank(message = "account number cant be empty")
	@Pattern(regexp = "\\d{16}", message = "Account no must be exactly 16 digit")
	private String accNo;

	@NotBlank(message = "bankName  cant be empty ")
	@Size(max = 50, min = 4, message = "bankName should be between the 4 to 50 char")
	private String bankName;
	
	
	@NotBlank(message = "IFCE code cant be empty")
	private String ifsc;

	@NotBlank(message = "address  cant be empty")
	@Size(max = 30, min = 4, message = "address  should be between the 4 to 300 char")
	private String address;

	@NotBlank(message = "EMPLOYEID  cant be empty")
	private String employeeId;

}
