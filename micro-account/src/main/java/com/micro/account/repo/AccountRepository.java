package com.micro.account.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	Optional<Account> findByAccNo(String accountNumber);

	Optional<Account> findByEmployeeId(String empId);

}
