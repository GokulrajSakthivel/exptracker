package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.entity.Customer;
import com.exptracker.service.CustomerInter;

@RestController
public class CustomerController {


	@Autowired
	private CustomerInter customerInter;

	@PostMapping(value = "createCustomer")
	public Customer createTransaction(@RequestBody Customer customer) {
		Customer customer2 = customerInter.createCustomer(customer);
		return customer2;
	}

	@GetMapping(value = "readCustomer/{id}")
	public Customer readCustomerById(@PathVariable int id) {

		Customer findById = customerInter.readCustomerById(id);

		return findById;
	}



	@PutMapping(value = "updateCustomer/{id}")
	public ResponseEntity<String> updateCustomerById(@RequestBody Customer customer ,@PathVariable int id) {

		String message = customerInter.updateCustomer(customer, id);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@DeleteMapping(value = "deleteCustomerById/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		String message = customerInter.deleteCustomer(id);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

}
