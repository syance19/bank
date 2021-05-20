package com.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.domain.DocumentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeCrearUnCustomer() {
		// Arrange
		DocumentType documentType = null;
		Customer customer = new Customer();
		customer.setAccounts(null);
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setCustId(2020);
		customer.setEmail("homerjsimpson@mapu.com");
		customer.setEnable("Y");
		customer.setName("Homero J Simpson");
		customer.setPhone("5555555");
		customer.setRegisteredAccounts(null);
		customer.setToken(UUID.randomUUID().toString().toUpperCase());
		
		Optional<DocumentType> documentTypeOptional=  documentTypeRepository.findById(1);
		if(documentTypeOptional.isPresent()==true) {
			documentType=documentTypeOptional.get();
		}
		
		customer.setDocumentType(documentType);

		// Act
		customerRepository.save(customer);
		// Assert
		assertNotNull(customer);
	}

	@Test
	@Order(2)
	@Transactional(readOnly = true)
	void debeConsultarUnCustomer() {
		// Arrange
	
		Optional<Customer> customerOptional=null;

		// Act
		customerOptional=customerRepository.findById(2020);
		// Assert
		assertTrue(customerOptional.isPresent());

	}

	@Test
	@Order(3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void debeModificarUnCustomer() {
		// Arrange
		Optional<Customer> customerOptional=null;
		Customer customer=null;
		customerOptional=customerRepository.findById(2020);
		assertTrue(customerOptional.isPresent());
		customer=customerOptional.get();
		customer.setEnable("N");
		// Act
		customerRepository.save(customer);
		// Assert
		assertNotNull(customer);
		
	}

	@Test
	@Order(4)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)

	void debeBorrarUnCustomer() {
		// Arrange
		Optional<Customer> customerOptional=null;
		Customer customer=null;
		customerOptional=customerRepository.findById(2020);
		assertTrue(customerOptional.isPresent());
		customer=customerOptional.get();
	
		// Act
		customerRepository.delete(customer);
		
	}

}
