package com.exptracker.serviceimpl;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.dto.AddressDto;
import com.exptracker.entity.Address;
import com.exptracker.entity.Customer;
import com.exptracker.exception.TrackerException;
import com.exptracker.repository.AddressRepository;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String createAddress(Address address) throws TrackerException {

		Optional<Customer> optional = customerRepository.findById(address.getCustomer().getCustomerId());
		if (!optional.isPresent()) {
			throw new TrackerException(" Invalid Customer ID ");
		}

		if (String.valueOf(address.getPinCode()).length() != 6) {
			throw new TrackerException("Enter a valid 6-digit Pin Code");
		}

		addressRepository.save(address);
		return "Address Created Successfully";
	}

	@Override
	public AddressDto readAddressByAddressId(int addressId) throws TrackerException {
		Optional<Address> optional = addressRepository.findById(addressId);
		if (!optional.isPresent()) {
			throw new TrackerException(" Address ID Not found ");

		}
		return mapper.map(optional.get(), AddressDto.class);
	}

//	@Override
//	public List<Address> readAddressByPincode(int pincode) throws TrackerException {
//		List<Address> address = addressRepository.findAllByPinCode(pincode);
//		if (address.isEmpty()) {
//			throw new TrackerException("Address Not found for Pincode : " + pincode);
//		}
//		return address;
//	}
//
//	@Override
//	public List<Address> readAddressByDoorNo(int doorNo) throws TrackerException {
//		List<Address> address= addressRepository.findAllByDoorNo(doorNo);
//		if (address.isEmpty()) {
//			throw new TrackerException("Address Not found for doorNumber : " + doorNo);
//		}
//		return address;
//	}

	@Override
	public String updateAddress(Address address, int addressId) throws TrackerException {

//		Optional<Customer> customerOptional = customerRepository.findById(address.getCustomer().getCustomerId());
//		if (!customerOptional.isPresent()) {
//			throw new CustomerException(" Invalid Customer ID ");
//		}

		Optional<Address> optional = addressRepository.findById(addressId);
		if (!optional.isPresent()) {
			throw new TrackerException(" Address ID Not found ");
		}

		if (String.valueOf(address.getPinCode()).length() != 6) {
			throw new TrackerException("Enter a valid 6-digit Pin Code");
		}

		Address existingAddress = optional.get();

		existingAddress.setDoorNo(address.getDoorNo());
		existingAddress.setCity(address.getCity());
		existingAddress.setPinCode(address.getPinCode());
		existingAddress.setState(address.getState());
		existingAddress.setStreetName(address.getStreetName());

		addressRepository.save(existingAddress);
		return "Address Updated Successfully";

	}

	@Override
	public String deleteAddressByAddressId(int addressId) throws TrackerException {
		Optional<Address> optional = addressRepository.findById(addressId);
		if (!optional.isPresent()) {
			throw new TrackerException("Address Not found (or) Invalid AddressId");
		}
		addressRepository.delete(optional.get());
		return "Deleted Successfully";

	}

}