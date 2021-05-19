package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.domain.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
