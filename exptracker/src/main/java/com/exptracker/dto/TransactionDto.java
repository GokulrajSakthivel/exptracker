package com.exptracker.dto;

import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.stereotype.Component;
import com.exptracker.enums.ExpendetureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TransactionDto {

	private LocalDate transactionDate;

	@Enumerated(EnumType.STRING)
	private ExpendetureType ExpendetureType;

	private float credit;

	private float withdrawal;

	private float closingBalance;

	private String location;

	private String otherDetails;

}
