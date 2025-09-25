package com.micro.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
