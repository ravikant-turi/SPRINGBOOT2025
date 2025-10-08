package com.micro.account.service;

import java.util.List;

import com.micro.account.dto.AccountDto;
import com.micro.account.entity.Account;
import com.micro.account.payload.ApiResponse;

public interface AccountService {

	ApiResponse<Account> saveAccount(AccountDto account);

	ApiResponse<Account> findAccountByAccountNumber(String accountNumber);

	ApiResponse<List<Account>> findAllAccount();

	ApiResponse<Account> findAccountById(String id);
	
	ApiResponse<Account> updateAccount(String id, AccountDto updatedData);

	ApiResponse<Void> deleteAccount(String id);
	
	ApiResponse<Account>getAccountByEmployeeId (String id);


}
