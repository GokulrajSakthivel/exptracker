package com.exptracker.dto;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TransactionDto {

	private LocalDate transactionDate;
	private float credit;
	private float withdrawal;
	private float closingBalance;
	private String location;

}
