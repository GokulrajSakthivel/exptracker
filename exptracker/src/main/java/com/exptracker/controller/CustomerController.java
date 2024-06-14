package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.dto.CustomerDto;
import com.exptracker.entity.Customer;
import com.exptracker.service.CustomerService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "createCustomer")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.createCustomer(customer),HttpStatus.OK);
	}

//	@RequestMapping(method = RequestMethod.GET,value = "readCustomer/{id}")
	@GetMapping(value = "readCustomer/{id}")
	public ResponseEntity<CustomerDto> readCustomerById(@PathVariable int id) {
		return new ResponseEntity<>(customerService.readCustomerById(id),HttpStatus.OK);
		
	}

	@PutMapping(value = "updateCustomer/{customerId}")
	public ResponseEntity<String> updateCustomerById(@RequestBody Customer customer , @PathVariable  int customerId) {
		return new ResponseEntity<>(customerService.updateCustomer(customer,customerId),HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "deleteCustomerById/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "loginCustomer/{userName}/{password}")
	public ResponseEntity<CustomerDto> loginCustomer(@PathVariable String userName , @PathVariable String password) {
		return new ResponseEntity<>(customerService.loginCustomer(userName, password),HttpStatus.OK);
	}
}
