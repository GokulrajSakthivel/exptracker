package com.exptracker.service;
import java.util.List;

import com.exptracker.entity.Account;
import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.exception.CustomerException;

public interface TransactionService {
	
	public Transaction createTransaction(Transaction transaction) throws CustomerException;

	public Transaction readTransactionByTransactionId(int transactionId) throws CustomerException;

	public String updateTransaction(Transaction transaction) throws CustomerException;

	public String deleteTransactionByTransactionId(int transactionId) throws CustomerException;
	
	public float overallTransactionByBank(long accNumber) throws CustomerException;

	public float overallTransactionByExpenditureType(long accNumber , ExpendetureType expendityreType) throws CustomerException;
}
