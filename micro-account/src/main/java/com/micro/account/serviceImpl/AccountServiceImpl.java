package com.micro.account.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import com.micro.account.dto.AccountDto;
import com.micro.account.dto.external.EmployeeResponse;
import com.micro.account.entity.Account;
import com.micro.account.exceptiosns.DuplicateResourceException;
import com.micro.account.exceptiosns.ResourceNotFoundException;
import com.micro.account.payload.ApiResponse;
import com.micro.account.repo.AccountRepository;
import com.micro.account.service.AccountService;
import com.micro.account.util.external.EmployeeClient;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EmployeeClient employeeClient;

	@Override
	public ApiResponse<Account> saveAccount(AccountDto accountDto) {
		// check if account no is already exist
		if (accountRepository.findByAccNo(accountDto.getAccNo()).isPresent()) {
			throw new DuplicateResourceException("ACCOUNT_NUMBER_ALREADY_EXIST " + accountDto.getAccNo());
		}
		employeeClient.getEmployeeById(accountDto.getEmployeeId());
		Account account = modelMapper.map(accountDto, Account.class);
		account.setDateTime(LocalDateTime.now().toString());
		account.setId(UUID.randomUUID().toString());
		Account saveAccount = this.accountRepository.save(account);
		return new ApiResponse<Account>("SUCCESS", "Account data is saved", saveAccount);
	}

	/*
	 * USING REST TEMPLATE
	 */
//	@Override
//	public ApiResponse<Account> saveAccount(AccountDto accountDto) {
//		// check if account no is already exist
//		if (accountRepository.findByAccNo(accountDto.getAccNo()).isPresent()) {
//			System.out.println("accountRepository.findByAccNo(accountDto.getAccNo()).isPresent(): "
//					+ accountRepository.findByAccNo(accountDto.getAccNo()).isPresent());
//			
//			System.out.println("accountRepository.findByAccNo(accountDto.getAccNo()).isPresent(): "
//					+ accountRepository.findByAccNo(accountDto.getAccNo()).isPresent());
//			
//			throw new DuplicateResourceException("ACCOUNT_NUMBER_ALREADY_EXIST " + accountDto.getAccNo());
//		}
//		// stop default exception handling behavior [ANNONIMOUS INNER CLASS]
//		new DefaultResponseErrorHandler();
//		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
//			@Override
//			public boolean hasError(ClientHttpResponse response) throws IOException {
//				return false;
//			}
//		});
//		System.out.println("=============================================");
//		System.out.println("=============================================");
//		System.out.println("=============================================");
//		System.out.println("=============================================");
//		System.out.println("=============================================");
	// check if employee id is not present
//		ResponseEntity<ApiResponse<EmployeeResponse>> response = restTemplate.exchange(
//				// URl : API end point
//				"http://localhost:8081/api/employees/" + accountDto.getEmployeeId(),
//				// GET METHOD
//				HttpMethod.GET,
//				// Header will be null because it is not post request but get request
//				null, // post request
//
//				// the response you want i.e ApiResponse and by default response is in LindHashMap
//				new ParameterizedTypeReference<ApiResponse<EmployeeResponse>>() {
//				});
//		ApiResponse<EmployeeResponse> employeeResponse = response.getBody();
//		System.out.println("employeeResponse : " + employeeResponse);
//		System.out.println("Employee ID: " + accountDto.getEmployeeId());
//		System.out.println("Calling URL: http://localhost:8081/api/employees/" + accountDto.getEmployeeId());
//		System.out.println("Response status: " + response.getStatusCode());
//		System.out.println("Response status: " + employeeResponse.getStatus());
//		System.out.println("Response body: " + employeeResponse);
//		if (employeeResponse == null || !"SUCCUSS".equalsIgnoreCase(employeeResponse.getStatus().trim())
//				|| employeeResponse.getData() == null) {
//			throw new ResourceNotFoundException("Employee not found with ID: " + accountDto.getEmployeeId()
//					+ " | URL attempted: http://localhost:8081/api/employees/" + accountDto.getEmployeeId());
//		}
//		Account account = modelMapper.map(accountDto, Account.class);
//		account.setDateTime(LocalDateTime.now().toString());
//		account.setId(UUID.randomUUID().toString());
//		Account saveAccount = this.accountRepository.save(account);
//		return new ApiResponse<Account>("SUCCESS", "Account data is saved", saveAccount);
//	}

	@Override
	public ApiResponse<Account> findAccountByAccountNumber(String accountNumber) {

		Account accountFound = this.accountRepository.findByAccNo(accountNumber).orElseThrow(
				() -> new ResourceNotFoundException("Account is not found with this id : " + accountNumber));
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

	@Override
	public ApiResponse<Account> updateAccount(String id, AccountDto updatedData) {

		Account account = this.accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));

		account.setAccNo(updatedData.getAccNo());
		account.setBankName(updatedData.getBankName());
		account.setIfsc(updatedData.getIfsc());
		account.setAddress(updatedData.getAddress());
		account.setDateTime(LocalDateTime.now().toString());
		account.setEmployeeId(updatedData.getEmployeeId());

		Account updatedAccount = this.accountRepository.save(account);
		return new ApiResponse<>("SUCCESS", "ACCOUNT_UPDATED_SUCCESSFULLY", updatedAccount);
	}

	@Override
	public ApiResponse<Void> deleteAccount(String id) {

		Account account = this.accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));

		this.accountRepository.delete(account);

		return new ApiResponse<>("SUCCESS", "ACCOUNT_DELETED_SUCCESSFULLY", null);
	}

	@Override
	public ApiResponse<Account> getAccountByEmployeeId(String id) {

		Account account = this.accountRepository.findByEmployeeId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee is not found with id: " + id));

		return new ApiResponse<>("SUCCESS", "ACCOUNT_DELETED_SUCCESSFULLY", account);

	}

}
