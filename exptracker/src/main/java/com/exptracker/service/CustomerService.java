package com.exptracker.service;

import com.exptracker.dto.CustomerDto;
import com.exptracker.entity.Customer;
import com.exptracker.exception.TrackerException;

public interface CustomerService {

	public String createCustomer(Customer customer) throws TrackerException;

	public CustomerDto readCustomerById(int custId) throws TrackerException;

	public String updateCustomer(Customer customer , int customerId ) throws TrackerException;

	public String deleteCustomer(int custId) throws TrackerException;
	
	public CustomerDto loginCustomer(String userName, String password)throws TrackerException;

}
