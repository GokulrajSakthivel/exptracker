package com.exptracker.service;
import java.util.List;

import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.exception.TrackerException;

public interface TransactionService {
	
	public Transaction createTransaction(Transaction transaction ) throws TrackerException;

	public Transaction readTransactionByTransactionId(int transactionId) throws TrackerException;

	public String updateTransaction(Transaction transaction) throws TrackerException;

	public String deleteTransactionByTransactionId(int transactionId) throws TrackerException;
	
	public float totalTransactionByBank(long accNumber) throws TrackerException;

	public float totalTransactionByExpenditureType(long accNumber , ExpendetureType expendityreType) throws TrackerException;
	
	public List<Transaction> overallTransactionByBank(long accNumber) throws TrackerException;
}
