package com.micro.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.account.dto.AccountDto;
import com.micro.account.entity.Account;
import com.micro.account.payload.ApiResponse;
import com.micro.account.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping
	public ResponseEntity<ApiResponse<Account>> saveAccount(@Valid @RequestBody AccountDto accountDto) {

		ApiResponse<Account> apiRespons = this.accountService.saveAccount(accountDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(apiRespons);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Account>>> findAllAccount() {
		ApiResponse<List<Account>> apiRespons = this.accountService.findAllAccount();
		return ResponseEntity.status(HttpStatus.CREATED).body(apiRespons);

	}

	@GetMapping("{id}")
	public ResponseEntity<ApiResponse<Account>> findAccountById(@PathVariable("id") String id) {
		ApiResponse<Account> apiRespons = this.accountService.findAccountById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiRespons);

	}

	@GetMapping("/accNo/{id}")
	public ResponseEntity<ApiResponse<Account>> findAccountByAccNo(@PathVariable("id") String id) {
		ApiResponse<Account> apiRespons = this.accountService.findAccountByAccountNumber(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiRespons);

	}

	@GetMapping("/emp/{id}")
	public ResponseEntity<ApiResponse<Account>> findAccountByEmployeeNo(@PathVariable("id") String id) {
		ApiResponse<Account> apiRespons = this.accountService.getAccountByEmployeeId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiRespons);

	}

}
