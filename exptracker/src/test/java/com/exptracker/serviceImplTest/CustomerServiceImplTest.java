package com.exptracker.serviceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exptracker.dto.CustomerDto;
import com.exptracker.entity.Customer;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.serviceimpl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Test
	public void saveCustomerTest() {
		// Arrange
		CustomerDto newCustomer = new CustomerDto();
		
		newCustomer.setCustomerId(1);
		newCustomer.setCustomerName("Gokul");
		newCustomer.setContactNumber("9788116704");;
		newCustomer.setUserName("gokul@gmail.com");
		newCustomer.setPassword("Reset@123");
		

		Customer record = new Customer();
		record.setCustomerId(newCustomer.getCustomerId());
		record.setCustomerName(newCustomer.getCustomerName());
		record.setContactNumber(newCustomer.getContactNumber());
		record.setUserName(newCustomer.getUserName());
		record.setPassword(newCustomer.getPassword());
		
		
		 when(customerRepository.save(any(Customer.class))).thenReturn(record);
		 
		 String response = customerServiceImpl.createCustomer(record);
		 
//		 Customer Created Successfully
		 
		 assertNotNull(response);
		 assertEquals(response, "Customer Created Successfully");
		 
		 verify(customerRepository , times(1)).save(any(Customer.class));
		 
		 
//	        System.out.println(response);
		
		;
		
	}

}
