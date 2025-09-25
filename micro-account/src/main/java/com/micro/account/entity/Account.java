package com.micro.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {

	@Id
	private String id;

	@Column(unique = true)
	private String accNo;

	@Column(length = 50)
	private String bankName;

	private String ifsc;

	@Column(length = 300)
	private String address;

	private String dateTime;

	private String employeeId;
}
