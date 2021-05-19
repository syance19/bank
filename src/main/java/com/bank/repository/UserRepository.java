package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.domain.User;

public interface UserRepository extends JpaRepository<User,String> {

}
