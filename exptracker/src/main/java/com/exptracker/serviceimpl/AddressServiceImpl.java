package com.exptracker.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	@Override
	public Address createAddress(Address address) throws TrackerException {

		Optional<Customer> optional = customerRepository.findById(address.getCustomer().getCustomerId());
		if (!optional.isPresent()) {
			throw new TrackerException(" Invalid Customer ID ");
		}

		if (String.valueOf(address.getPinCode()).length() == 6) {
			throw new TrackerException("Enter a valid 6-digit Pin Code");
		}

		return addressRepository.save(address);
	}

	@Override
	public List<Address> readAddressByPincode(int pincode) throws TrackerException {
		List<Address> address = addressRepository.findAllByPinCode(pincode);
		if (!address.isEmpty()) {
			return address;
		} else {
			throw new TrackerException("Address Not found for Pincode : " + pincode);
		}
	}

	@Override
	public Address readAddressByDoorNo(int doorNo) throws TrackerException {
		Optional<Address> optional = addressRepository.findById(doorNo);
		if (!optional.isPresent()) {
			throw new TrackerException("Address Not found for doorNumber : " + doorNo);
		}
		return optional.get();
	}

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

		if (String.valueOf(address.getPinCode()).length() == 6) {
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