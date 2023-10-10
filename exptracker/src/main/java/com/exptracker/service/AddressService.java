package com.exptracker.service;

import java.util.List;
import com.exptracker.entity.Address;
import com.exptracker.exception.TrackerException;

public interface AddressService {
	public Address createAddress(Address address) throws TrackerException;

	public List<Address> readAddressByPincode(int pincode) throws TrackerException;

	public Address readAddressByDoorNo(int doorNo) throws TrackerException;

	public String updateAddress(Address address , int addressId) throws TrackerException;

	public String deleteAddressByAddressId(int addressId) throws TrackerException;

}