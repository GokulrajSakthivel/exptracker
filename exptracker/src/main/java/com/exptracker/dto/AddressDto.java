package com.exptracker.dto;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AddressDto {
	private int addressId;
	private int doorNo;
	private String streetName;
	private String city;
	private String state;
	private int pinCode;
	
}
