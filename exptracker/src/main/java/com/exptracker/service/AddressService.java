package com.exptracker.service;

import java.util.List;
import com.exptracker.entity.Address;
import com.exptracker.exception.CustomerException;

public interface AddressService {
	public Address createAddress(Address address) throws CustomerException;

	public List<Address> readAddressByPincode(int pincode) throws CustomerException;

	public Address readAddressById(int doorNo) throws CustomerException;

	public String updateAddress(Address address) throws CustomerException;

	public String deleteAddressByDoorNumber(int addressId) throws CustomerException;

}