package com.exptracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.CustomerRepository;

@Service
public class CustomerImpl implements CustomerInter {

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

		Optional<Customer> customer = customerRepository.findById(CustId);
		Customer customer2 = null;
		if (customer.isPresent()) {
			customer2 = customer.get();
		} else {
			throw new CustomerException("Customer not found for ID: " + CustId);
		}
		return customer2;
	}

	@Override
	public String updateCustomer(Customer customer, int custId) throws CustomerException {

		Customer customer2 = new Customer();

		Optional<Customer> data = customerRepository.findById(custId);

		String message = "";
		if (data.isPresent()) {
			customer2.setCustomerName(customer.getCustomerName());
			customer2.setContactNumber(customer.getContactNumber());
			customerRepository.save(customer2);
			message = "Updated Successfully";
		} else {
			throw new CustomerException("Customer not found for ID: " + customer.getCustomerId());
		}
		return message;
	}

	@Override
	public String deleteCustomer(int custId) throws CustomerException {

		Customer customer2 = readCustomerById(custId);
		String message = "";
		if (customer2 != null) {
			customerRepository.delete(customer2);
			message = " Deleted Successfully";
		} else {
			throw new CustomerException("Customer not found for ID: " + custId);
		}

		return message;
	}

}
