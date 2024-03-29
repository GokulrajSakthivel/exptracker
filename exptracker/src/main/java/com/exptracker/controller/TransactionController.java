package com.exptracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.dto.TransactionDto;
import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.service.TransactionService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "createTransaction/{accountNumber}")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction , @PathVariable long accountNumber) {
		return new ResponseEntity<>(transactionService.createTransaction(transaction, accountNumber) ,HttpStatus.CREATED);
		
	}

	@GetMapping(value = "readTransaction/{transactionId}")
	public ResponseEntity<TransactionDto> readTransactionById(@PathVariable int transactionId) {
		return new ResponseEntity<>(transactionService.readTransactionByTransactionId(transactionId),HttpStatus.OK);

	}

	@DeleteMapping(value = "deleteTransaction/{transactionId}")
	public ResponseEntity<String> deleteTransaction(@PathVariable int transactionId) {
		return new ResponseEntity<>(transactionService.deleteTransactionByTransactionId(transactionId),HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "totalWithdrawl/{accNumber}")
	public ResponseEntity<Float> getTotalWithdrawlByAccNumber(@PathVariable long accNumber) {
		return new ResponseEntity<>(transactionService.totalWithdrawlByBank(accNumber),HttpStatus.OK);
	}
	
	@GetMapping(value = "totalCredit/{accNumber}")
	public ResponseEntity<Float> getTotalCreditByAccNumber(@PathVariable long accNumber) {
		return new ResponseEntity<>(transactionService.totalCreditByBank(accNumber),HttpStatus.OK);
	}

	@GetMapping(value = "totalWithdrawl/{accNumber}/{expenditureType}")
	public ResponseEntity<Float> getAllTransactionByAccNumber(@PathVariable int accNumber, @PathVariable ExpendetureType expenditureType) {
		return new ResponseEntity<>(transactionService.totalTransactionByExpenditureType(accNumber, expenditureType),HttpStatus.OK);
	}
	
	@GetMapping("overAllTransaction/{accNumber}")
	public ResponseEntity<List<Transaction>> overAllTransactionsByAccountNumber(@PathVariable long accNumber){
		return new ResponseEntity<>(transactionService.overallTransactionByBank(accNumber),HttpStatus.OK);
	}

}
