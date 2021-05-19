package com.bank.service;

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

import com.bank.domain.Customer;
import com.bank.domain.DocumentType;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerServiceTest {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DocumentTypeService documentTypeService;

	@Test
	@Order(1)
	void debeCrearUnCustomer() throws Exception {
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
		
		Optional<DocumentType> documentTypeOptional=  documentTypeService.findById(1);
		if(documentTypeOptional.isPresent()==true) {
			documentType=documentTypeOptional.get();
		}
		
		customer.setDocumentType(documentType);

		// Act
		customerService.save(customer);
		// Assert
		assertNotNull(customer);
	}

	@Test
	@Order(2)
	
	void debeConsultarUnCustomer() throws Exception {
		// Arrange
	
		Optional<Customer> customerOptional=null;

		// Act
		customerOptional=customerService.findById(2020);
		// Assert
		assertTrue(customerOptional.isPresent());

	}

	@Test
	@Order(3)
	
	void debeModificarUnCustomer() throws Exception {
		// Arrange
		Optional<Customer> customerOptional=null;
		Customer customer=null;
		customerOptional=customerService.findById(2020);
		assertTrue(customerOptional.isPresent());
		customer=customerOptional.get();
		customer.setEnable("N");
		// Act
		customerService.update(customer);
		// Assert
		assertNotNull(customer);
		
	}

	@Test
	@Order(4)
	void debeBorrarUnCustomer() throws Exception {
		// Arrange
		Optional<Customer> customerOptional=null;
		Customer customer=null;
		customerOptional=customerService.findById(2020);
		assertTrue(customerOptional.isPresent());
		customer=customerOptional.get();
	
		// Act
		customerService.delete(customer);
		
	}

}
