package com.exptracker.service;

import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;

public interface ExptrackerInter {
	
	public Customer createCustomer(Customer customer) throws CustomerException;
	
	

}
