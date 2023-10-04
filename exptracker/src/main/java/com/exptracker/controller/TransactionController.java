package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "createTransaction")
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		Transaction transaction2 = transactionService.createTransaction(transaction);
		return transaction2;
	}

	@GetMapping(value = "readTransaction/{transactionId}")
	public Transaction readTransactionById(@PathVariable int transactionId) {
		return transactionService.readTransactionByTransactionId(transactionId);

	}

	@DeleteMapping(value = "deleteTransaction/{transactionId}")
	public String deleteTransaction(@PathVariable int transactionId) {
		return transactionService.deleteTransactionByTransactionId(transactionId);
	}

	@GetMapping(value = "overAllTransaction/{accNumber}")
	public float getAllTransactionByAccNumber(@PathVariable long accNumber) {
		return transactionService.overallTransactionByBank(accNumber);

	}

	@GetMapping(value = "totalTransaction/{accNumber}/{expenditureType}")
	public float getAllTransactionByAccNumber(@PathVariable int accNumber, @PathVariable ExpendetureType expenditureType) {
		return transactionService.overallTransactionByExpenditureType(accNumber, expenditureType);
	}

}
