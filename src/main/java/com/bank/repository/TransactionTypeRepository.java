package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.domain.TransactionType;

public interface TransactionTypeRepository extends  JpaRepository<TransactionType, Integer> {

}
