package com.exptracker.service;

import java.util.Optional;

import com.exptracker.entity.Account;
import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;

public interface CustomerInter {

	public Customer createCustomer(Customer customer) throws CustomerException;

	public Customer readCustomerById(int custId) throws CustomerException;

	public String updateCustomer(Customer customer , int custId) throws CustomerException;

	public String deleteCustomer(int custId) throws CustomerException;

}
