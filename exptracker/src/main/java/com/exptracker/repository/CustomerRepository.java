package com.exptracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.exptracker.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByUserNameAndPassword(String userName , String password);

}
