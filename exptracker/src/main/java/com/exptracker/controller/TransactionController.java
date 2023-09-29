package com.exptracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.entity.Customer;
import com.exptracker.entity.Transaction;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.AccountRepository;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.repository.TransactionRepository;
import com.exptracker.service.ExptrackerInter;

@RestController
public class TransactionController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ExptrackerInter exptrackerInter;

	@PostMapping(value = "createCustomer")
	public Customer createTransaction(@RequestBody Customer customer) {
		Customer customer2 = exptrackerInter.createCustomer(customer);
		return customer2;
	}

	@GetMapping(value = "read/{id}")
	public Customer readCustomerById(@PathVariable int id) {

		Optional<Customer> findById = customerRepository.findById(exptrackerInter.readCustomerById(id));
		Customer customer = null;
		if (findById != null) {
			customer = findById.get();
		}
		return customer;
	}

	@PostMapping(value = "createTransaction")
	public Transaction createTransaction(@RequestBody Transaction transaction) {

		return transactionRepository.save(transaction);

	}

}
