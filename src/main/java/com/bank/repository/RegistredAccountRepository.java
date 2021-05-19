package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.domain.RegisteredAccount;

public interface RegistredAccountRepository extends JpaRepository<RegisteredAccount, Integer>{

}
