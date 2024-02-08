package com.exptracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exptracker.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	
	List<Address> findAllByPinCode(int pinCode);
	
	List<Address> findAllByDoorNo(int doorNo);
	
	
}
