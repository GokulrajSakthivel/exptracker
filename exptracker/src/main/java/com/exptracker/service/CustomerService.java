package com.exptracker.service;

import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;

public interface CustomerService {

	public Customer createCustomer(Customer customer) throws CustomerException;

	public Customer readCustomerById(int custId) throws CustomerException;

	public String updateCustomer(Customer customer ) throws CustomerException;

	public String deleteCustomer(int custId) throws CustomerException;

}
