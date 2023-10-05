package com.exptracker.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exptracker.entity.Account;
import com.exptracker.entity.Customer;
import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.AccountRepository;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.repository.TransactionRepository;
import com.exptracker.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Transaction createTransaction(Transaction transaction) throws CustomerException {
		Optional<Account> optional = accountRepository.findById(transaction.getAccountRef().getAccountNumber());
		Account account = optional.get();
		Transaction transaction2 = null;
		if (optional.isPresent()) {
			if (transaction.getCredit() != 0.0) {
				account.setAccountBalance(account.getAccountBalance() + transaction.getCredit());
				accountRepository.save(account);
				transaction.setClosingBalance(account.getAccountBalance());
				transaction2 = transactionRepository.save(transaction);
			} else if (transaction.getWithdrawal() != 0.0) {
				account.setAccountBalance(account.getAccountBalance() - transaction.getWithdrawal());
				accountRepository.save(account);
				transaction.setClosingBalance(account.getAccountBalance());
				transaction2 = transactionRepository.save(transaction);
			} else {
				throw new CustomerException("Something went wrong or invalid Transaction");
			}
			return transaction2;
		} else {
			throw new CustomerException("Invalid Account Number");
		}
	}

	@Override
	public Transaction readTransactionByTransactionId(int transactionId) throws CustomerException {
		Transaction transaction2 = null;
		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		if (optional.isPresent()) {
			transaction2 = optional.get();
		} else {
			throw new CustomerException("Transaction not found for ID : " + transactionId);
		}
		return transaction2;
	}

	@Override
	public String updateTransaction(Transaction transaction) throws CustomerException {
		Optional<Transaction> optional = transactionRepository.findById(transaction.getTransactionId());
		String message = "";
		Transaction transaction2 = new Transaction();
		if (optional.isPresent()) {
			transaction2.setTransactionDate(transaction.getTransactionDate());
			transaction2.setExpendetureType(transaction.getExpendetureType());
			transaction2.setCredit(transaction.getCredit());
			transaction2.setWithdrawal(transaction.getWithdrawal());
			transaction2.setClosingBalance(transaction.getClosingBalance());
			transaction2.setLocation(transaction.getLocation());
			transaction2.setOtherDetails(transaction.getOtherDetails());
			message = "Transaction Created Successfully ";

		} else {
			throw new CustomerException("Transaction not found for ID: " + transaction.getTransactionId());
		}
		return message;

	}

	@Override
	public String deleteTransactionByTransactionId(int transactionId) throws CustomerException {
		Transaction transaction2 = readTransactionByTransactionId(transactionId);
		String message = "";
		if (transaction2 != null) {
			transactionRepository.delete(transaction2);
			message = "Transaction Deleted Successfully";
		} else {
			throw new CustomerException("Transaction not found for ID: " + transactionId);
		}
		return message;
	}

	@Override
	public float overallTransactionByBank(long accNumber) throws CustomerException {
		Optional<Account> accountOptional = accountRepository.findById(accNumber);

		float total = 0;
		if (accountOptional.isPresent()) {
			List<Transaction> listOftransactions = accountOptional.get().getTransactions();
			for (Transaction transaction : listOftransactions) {
				total = total + transaction.getWithdrawal();
			}
			return total;
		} else {
			throw new CustomerException("Account Not found for given Account Number : " + accNumber);
		}
	}

	@Override
	public float overallTransactionByExpenditureType(long accNumber, ExpendetureType expendityreType)
			throws CustomerException {
		Optional<Account> accountOptional = accountRepository.findById(accNumber);

		float total = 0;
		if (accountOptional.isPresent()) {
			List<Transaction> listOftransactions = accountOptional.get().getTransactions();
			for (Transaction transaction : listOftransactions) {
				if (transaction.getExpendetureType().equals(expendityreType)) {
					total = total + transaction.getWithdrawal();
				}
			}
			return total;
		} else {
			throw new CustomerException("Account Not found for given Account Number : " + accNumber);
		}
	}
}
