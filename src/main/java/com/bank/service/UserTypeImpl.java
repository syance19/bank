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


import com.bank.domain.UserType;
import com.bank.repository.UserTypeRepository;

public class UserTypeImpl implements UserTypeService {

	@Autowired
	UserTypeRepository userTypeRepository;
	@Autowired
	Validator validator;
	
	
	@Override
	public void validate(UserType entity) throws Exception {
		Set<ConstraintViolation<UserType>> constrainsViolations = validator.validate(entity);
		if (!constrainsViolations.isEmpty()) {
			throw new ConstraintViolationException(constrainsViolations);
		}
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<UserType> findAll() {
		return userTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserType save(UserType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (userTypeRepository.existsById(entity.getUstyId()) == true) {
			throw new Exception("El user type con id" + entity.getUstyId() + "ya existe");
		}
		return userTypeRepository.save(entity);
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserType update(UserType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (userTypeRepository.existsById(entity.getUstyId()) == false) {
			throw new Exception("El user type con id" + entity.getUstyId() + "no existe");
		}
		return userTypeRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(UserType entity) throws Exception {
		userTypeRepository.deleteById(entity.getUstyId());
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		if(id==null|| id<=0) {
			throw new Exception("Id no valido o es nulo");
		}
		if(userTypeRepository.existsById(id)==false) {
			throw new Exception("El user type con id" + id + "no existe");
		}
		delete(userTypeRepository.findById(id).get());
		
	}

	@Override
	public Optional<UserType> findById(Integer id) throws Exception {
		return userTypeRepository.findById(id);
	}

	

	@Override
	public Long count() {
		return userTypeRepository.count();
	}

}
