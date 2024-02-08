package com.exptracker.service;



import com.exptracker.dto.AddressDto;
import com.exptracker.entity.Address;
import com.exptracker.exception.TrackerException;

public interface AddressService {
	public String createAddress(Address address) throws TrackerException;
	
	public  AddressDto readAddressByAddressId(int addressId) throws TrackerException;

//	public List<Address> readAddressByPincode(int pincode) throws TrackerException;
//
//	public List<Address> readAddressByDoorNo(int doorNo) throws TrackerException;

	public String updateAddress(Address address , int addressId) throws TrackerException;

	public String deleteAddressByAddressId(int addressId) throws TrackerException;

}