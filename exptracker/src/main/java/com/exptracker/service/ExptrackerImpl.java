package com.exptracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.CustomerRepository;

@Service
public class ExptrackerImpl implements ExptrackerInter {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {

		Customer customer2 = null;
		if (customer.getCustomerId() >= 1000 && customer.getCustomerId() <= 9999) {
			if (customer.getCustomerName().length() >= 3) {
				customer2 = customerRepository.save(customer);
			} else {
				throw new CustomerException(" Invalid Customer Name ");
			}
		} else {
			throw new CustomerException(" Invalid Customer ID ");
		}

		return customer2;
	}

	@Override
	public Customer readCustomerById(int CustId) throws CustomerException {
		int id = 0;
		Customer customer = null;
		if (id >= 1000 && id <= 9999) {
			customer = customerRepository.findById(id);
		} else {
			throw new CustomerException("Invalid Customer ID");
		}
		return customer;
	}

}
