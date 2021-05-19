package com.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	Validator validator;

	@Override
	public void validate(Customer entity) throws ConstraintViolationException {
		Set<ConstraintViolation<Customer>> constrainsViolations = validator.validate(entity);
		if (!constrainsViolations.isEmpty()) {
			throw new ConstraintViolationException(constrainsViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer save(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (customerRepository.existsById(entity.getCustId()) == true) {
			throw new Exception("El customer con id" + entity.getCustId() + "ya existe");
		}
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer update(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (customerRepository.existsById(entity.getCustId()) == false) {
			throw new Exception("El customer con id" + entity.getCustId() + "no existe");
		}
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}

		if (entity.getCustId() == null || entity.getCustId() == 0) {
			throw new Exception("Id es nulo");
		}
		if (customerRepository.existsById(entity.getCustId()) == false) {
			throw new Exception("El customer con id" + entity.getCustId() + "nno existe");
		}
		
		customerRepository.findById(entity.getCustId()).ifPresent(customer->{
			if(customer.getAccounts().isEmpty()==false) {
				throw new RuntimeException("El customer tiene una cuenta");
			}
			if(customer.getRegisteredAccounts().isEmpty()==false) {
				throw new RuntimeException("El customer tiene una cuenta registrada");
			}
		});

		customerRepository.deleteById(entity.getCustId());
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		if(id==null|| id<=0) {
			throw new Exception("Id no valido o es nulo");
		}
		if(customerRepository.existsById(id)==false) {
			throw new Exception("El customer con id" + id + "no existe");
		}
		delete(customerRepository.findById(id).get());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> findById(Integer id) throws Exception {

		return customerRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {

		return customerRepository.count();
	}

}
