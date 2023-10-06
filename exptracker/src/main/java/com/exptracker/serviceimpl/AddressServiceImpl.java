package com.exptracker.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exptracker.entity.Address;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.AddressRepository;
import com.exptracker.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address createAddress(Address address) throws CustomerException {
		Address address2 = null;
		if (address.getDoorNo() >= 1000) {
			if (address.getState().length() >= 3) {
				address2 = addressRepository.save(address);
			} else {
				throw new CustomerException(" Invalid State Name :" + address.getState());
			}
		} else {
			throw new CustomerException(" Invalid Door Number " + address.getDoorNo());
		}
		return address2;
	}

	@Override
	public List<Address> readAddressByPincode(int pincode) throws CustomerException {
		List<Address> address = addressRepository.findAllByPinCode(pincode);
		if (!address.isEmpty()) {
			return address;
		} else {
			throw new CustomerException("Address Not found for Pincode : " + pincode);
		}
	}

	@Override
	public Address readAddressById(int doorNo) throws CustomerException {
		if (addressRepository.findById(doorNo).isPresent()) {
			Optional<Address> optional = addressRepository.findById(doorNo);
			Address address = optional.get();
			return address;
		} else {
			throw new CustomerException("Address Not found for doorNumber : " + doorNo);
		}

	}

	@Override
	public String updateAddress(Address address) throws CustomerException {

		
		Optional<Address> optional = addressRepository.findById(address.getAddressId());
		Address updatedAddress = new Address();

		if (optional.isPresent()) {
			Address existingAddress = addressRepository.findById(address.getAddressId()).get();
			updatedAddress.setAddressId(existingAddress.getAddressId());
			updatedAddress.setDoorNo(address.getDoorNo());
			updatedAddress.setCity(address.getCity());
			updatedAddress.setPinCode(address.getPinCode());
			updatedAddress.setState(address.getState());
			updatedAddress.setStreetName(address.getStreetName());
			updatedAddress.setCustomer(existingAddress.getCustomer());
			addressRepository.save(updatedAddress);
			return "Address Updated Successfully";
		} else {
			throw new CustomerException(" Address ID (or) Door Number Not found ");
		}

	}

	@Override
	public String deleteAddressByDoorNumber(int addressId) throws CustomerException {
		Optional<Address> optional = addressRepository.findById(addressId);
		if (optional.isPresent()) {
			addressRepository.delete(optional.get());
			return "Deleted Successfully";
		} else {
			throw new CustomerException("Address Not found (or) Invalid Door Number");
		}

	}

}