package com.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.User;
import com.bank.repository.UserRepository;

public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Validator validator;
	
	
	
	@Override
	public void validate(User entity) throws Exception {
		Set<ConstraintViolation<User>> constrainsViolations = validator.validate(entity);
		if (!constrainsViolations.isEmpty()) {
			throw new ConstraintViolationException(constrainsViolations);
		}
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User save(User entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (userRepository.existsById(entity.getUserEmail()) == true) {
			throw new Exception("El customer con id" + entity.getUserEmail() + "ya existe");
		}
		return userRepository.save(entity);
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User update(User entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (userRepository.existsById(entity.getUserEmail()) == false) {
			throw new Exception("El customer con correo" + entity.getUserEmail() + "no existe");
		}
		return userRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(User entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}

		if (entity.getUserEmail() == null || entity.getUserEmail().trim().equals("")) {
			throw new Exception("Id es nulo");
		}
		if (userRepository.existsById(entity.getUserEmail()) == false) {
			throw new Exception("El customer con correo" + entity.getUserEmail() + "ya existe");
		}
		
		userRepository.deleteById(entity.getUserEmail());
		
	}

	@Override
	public void deleteById(String id) throws Exception {
		if(id==null|| id.trim().equals("")) {
			throw new Exception("Id no valido o es nulo");
		}
		if(userRepository.existsById(id)==false) {
			throw new Exception("El customer con id" + id + "no existe");
		}
		delete(userRepository.findById(id).get());
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(String id) throws Exception {
		return userRepository.findById(id);
	}

	

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return userRepository.count();
	}

}
