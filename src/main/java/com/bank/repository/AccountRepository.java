package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.domain.Account;

public interface AccountRepository  extends JpaRepository<Account, String>{

}
