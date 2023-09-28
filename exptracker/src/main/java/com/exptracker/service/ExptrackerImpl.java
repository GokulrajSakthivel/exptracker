package com.exptracker.service;

import org.springframework.stereotype.Service;

import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;

@Service
public class ExptrackerImpl implements ExptrackerInter {

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {

		Customer customer2 = null;
		if (customer.getCustomerId() >= 3) {
			customer2 = customer;
		} else {
			throw new CustomerException(" Invalid Customer ID ");
		}

		return customer2;
	}

}
