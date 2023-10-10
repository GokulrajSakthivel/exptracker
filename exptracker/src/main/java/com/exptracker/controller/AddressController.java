package com.exptracker.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.exptracker.entity.Address;
import com.exptracker.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping(value = "createAddress")
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.CREATED);
	}

	@GetMapping(value = "readAddress/{pincode}")
	public ResponseEntity<List<Address>> readAddress(@PathVariable int pincode) {
		return new ResponseEntity<>(addressService.readAddressByPincode(pincode), HttpStatus.OK);
	}

	@GetMapping(value = "readAddressByDoorNum/{doorNo}")
	public ResponseEntity<Address> readAddressByDoorNo(@PathVariable int doorNo) {
		return new ResponseEntity<>(addressService.readAddressByDoorNo(doorNo), HttpStatus.OK);
	}

	@PutMapping(value = "updateAddress/{addressId}")
	public ResponseEntity<String> updateAddress(@RequestBody Address address , @PathVariable int addressId) {
		return new ResponseEntity<>(addressService.updateAddress(address , addressId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "deleteAddress/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable int addressId) {
		return new ResponseEntity<>(addressService.deleteAddressByAddressId(addressId), HttpStatus.ACCEPTED);

	}
}
