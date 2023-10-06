package com.exptracker.service;
import java.util.List;

import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.exception.CustomerException;

public interface TransactionService {
	
	public Transaction createTransaction(Transaction transaction ) throws CustomerException;

	public Transaction readTransactionByTransactionId(int transactionId) throws CustomerException;

	public String updateTransaction(Transaction transaction) throws CustomerException;

	public String deleteTransactionByTransactionId(int transactionId) throws CustomerException;
	
	public float totalTransactionByBank(long accNumber) throws CustomerException;

	public float totalTransactionByExpenditureType(long accNumber , ExpendetureType expendityreType) throws CustomerException;
	
	public List<Transaction> overallTransactionByBank(long accNumber) throws CustomerException;
}
