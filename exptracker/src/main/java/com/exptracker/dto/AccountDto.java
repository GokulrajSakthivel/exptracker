package com.exptracker.dto;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.stereotype.Component;
import com.exptracker.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountDto {

	private long accountNumber;
	private String bankName;
	private AccountType accountType;
	private float accountBalance;

}
