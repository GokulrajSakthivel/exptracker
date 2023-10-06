package com.exptracker.dto;
import java.util.List;

import org.springframework.stereotype.Component;

import com.exptracker.entity.Account;

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
	private long contactNumber;

}
