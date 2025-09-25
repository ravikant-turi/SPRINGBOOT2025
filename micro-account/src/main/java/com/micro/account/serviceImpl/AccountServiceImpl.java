package com.micro.account.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.account.dto.AccountDto;
import com.micro.account.entity.Account;
import com.micro.account.exceptiosns.ResourceNotFoundException;
import com.micro.account.payload.ApiResponse;
import com.micro.account.repo.AccountRepository;
import com.micro.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse<Account> saveAccount(AccountDto accountDto) {

		Account account = modelMapper.map(accountDto, Account.class);

		account.setDateTime(LocalDateTime.now().toString());
		account.setId(UUID.randomUUID().toString());

		Account saveAccount = this.accountRepository.save(account);
		return new ApiResponse<Account>("SUCCESS", "Account data is saved", saveAccount);
	}

	@Override
	public ApiResponse<Account> findAccountByAccountNumber(String accountNumber) {

		Account accountFound = this.accountRepository.findByAccNo(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account is not found with this id : " + accountNumber));
		return new ApiResponse<Account>("SUCCESS", "ACCOUNT_DATA_FOUND", accountFound);
	}

	@Override
	public ApiResponse<List<Account>> findAllAccount() {
		List<Account> accounts;

		accounts = this.accountRepository.findAll();

		if (accounts.isEmpty()) {
			return new ApiResponse<List<Account>>("SUCCESS", "account data is empty", accounts);
		}
		return new ApiResponse<List<Account>>("SUCCESS", "ACCOUNT_DATA_FOUND", accounts);
	}

	@Override
	public ApiResponse<Account> findAccountById(String id) {

		Account accountFound = this.accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account is not found with this id : " + id));
		return new ApiResponse<Account>("SUCCESS", "ACCOUNT_DATA_FOUND", accountFound);
	}

}
