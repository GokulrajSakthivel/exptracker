package com.exptracker.dto;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CustomerDto {
	
	private int customerId;
	private String customerName;
	private String contactNumber;

}
