package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.dto.CustomerDto;
import com.exptracker.entity.Customer;
import com.exptracker.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "createCustomer")
	public Customer createTransaction(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@GetMapping(value = "readCustomer/{id}")
	public Customer readCustomerById(@PathVariable int id) {
		Customer findById = customerService.readCustomerById(id);
		return findById;
	}

	@PutMapping(value = "updateCustomer")
	public String updateCustomerById(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping(value = "deleteCustomerById/{id}")
	public String deleteCustomer(@PathVariable int id) {
		return customerService.deleteCustomer(id);
	}
	
	@GetMapping(value = "loginCustomer/{userName}/{password}")
	public CustomerDto loginCustomer(@PathVariable String userName , @PathVariable String password) {
		return customerService.loginCustomer(userName, password);
	}
}
