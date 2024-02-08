package com.exptracker.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.dto.TransactionDto;
import com.exptracker.entity.Account;
import com.exptracker.entity.Transaction;
import com.exptracker.enums.ExpendetureType;
import com.exptracker.exception.TrackerException;
import com.exptracker.repository.AccountRepository;
import com.exptracker.repository.TransactionRepository;
import com.exptracker.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String createTransaction(Transaction transaction , long accountNumber) throws TrackerException {

		Optional<Account> optionals = accountRepository.findById(accountNumber);

		if (optionals.isPresent()) {
			Account account = optionals.get();

//			if (!transaction.getTransactionDate().equals(LocalDate.now())) {
//				throw new TrackerException("Invalid Transaction Date");
//			}
			
			if (transaction.getTransactionDate().isAfter(LocalDate.now())) {
			    throw new TrackerException("Invalid Transaction Date: Future date not allowed");
			}
			
			if (transaction.getCredit() < 0.0 || transaction.getWithdrawal() < 0.0) {
				throw new TrackerException("  amount should not be negative ");
			}
			
			if (transaction.getCredit() == 0.0 && transaction.getWithdrawal() == 0.0) {
				throw new TrackerException(" amount should not 0.0");
			}
			
			if (transaction.getCredit() > 0.0 && transaction.getWithdrawal() > 0.0) {
				throw new TrackerException(" use only credit (or) widthdrawl at a time ");
			}
			
		
			if (transaction.getCredit() != 0.0 && transaction.getWithdrawal() == 0.0) {
				account.setAccountBalance(account.getAccountBalance() + transaction.getCredit());
				accountRepository.save(account);
				transaction.setClosingBalance(account.getAccountBalance());
//				transaction.setTransactionDate(LocalDate.now());
			}

			if (transaction.getCredit() == 0.0 && transaction.getWithdrawal() != 0.0) {
				if (account.getAccountBalance() >= transaction.getWithdrawal()) {
					account.setAccountBalance(account.getAccountBalance() - transaction.getWithdrawal());
					accountRepository.save(account);
					transaction.setClosingBalance(account.getAccountBalance());
//					transaction.setTransactionDate(LocalDate.now());
				} else {
					throw new TrackerException("Insufficient Account Balance ");
				}
			}
			
			transaction.setAccountRef(optionals.get());
			transactionRepository.save(transaction);
			
			return "Transaction Created Successfully";

		} else {
			throw new TrackerException("Invalid Account Number : " + accountNumber);
		}
	}

	@Override
	public TransactionDto readTransactionByTransactionId(int transactionId) throws TrackerException {
		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		if (!optional.isPresent()) {
			throw new TrackerException("Transaction not found for ID : " + transactionId);
		}
		return mapper.map(optional.get(), TransactionDto.class);
	}

	@Override
	public String updateTransaction(Transaction transaction) throws TrackerException {
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
			throw new TrackerException("Transaction not found for ID: " + transaction.getTransactionId());
		}
		return message;

	}

	@Override
	public String deleteTransactionByTransactionId(int transactionId) throws TrackerException {
		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		if (!optional.isPresent()) {
			throw new TrackerException("Transaction not found for ID: " + transactionId);
		}
		transactionRepository.delete(optional.get());
		return "Transaction Deleted Successfully";
	}

	@Override
	public float totalWithdrawlByBank(long accNumber) throws TrackerException {
		Optional<Account> accountOptional = accountRepository.findById(accNumber);
		float total = 0;
		if (accountOptional.isPresent()) {
			List<Transaction> listOftransactions = accountOptional.get().getTransactions();
			for (Transaction transaction : listOftransactions) {
				total = total + transaction.getWithdrawal();
			}
			return total;
		} else {
			throw new TrackerException("Account Not found for given Account Number : " + accNumber);
		}
	}
	
	
	@Override
	public float totalCreditByBank(long accNumber) throws TrackerException {
		Optional<Account> accountOptional = accountRepository.findById(accNumber);
		float total = 0;
		if (accountOptional.isPresent()) {
			List<Transaction> listOftransactions = accountOptional.get().getTransactions();
			for (Transaction transaction : listOftransactions) {
				total = total + transaction.getCredit();
			}
			return total;
		} else {
			throw new TrackerException("Account Not found for given Account Number : " + accNumber);
		}
	}

	@Override
	public float totalTransactionByExpenditureType(long accNumber, ExpendetureType expendetureType)
			throws TrackerException {
		Optional<Account> accountOptional = accountRepository.findById(accNumber);
		float total = 0;
		if (accountOptional.isPresent()) {
			List<Transaction> listOftransactions = accountOptional.get().getTransactions();
			for (Transaction transaction : listOftransactions) {
				if (transaction.getExpendetureType().equals(expendetureType)) {
					total = total + transaction.getWithdrawal();
				}
			}
			return total;
		} else {
			throw new TrackerException("Account Not found for given Account Number : " + accNumber);
		}
	}

	@Override
	public List<Transaction> overallTransactionByBank(long accNumber) throws TrackerException {
		Optional<Account> optional = accountRepository.findById(accNumber);
		List<Transaction> list = null;
		if (optional.isPresent()) {
			list = optional.get().getTransactions();
			if (list != null) {
				return list;
			} else {
				throw new TrackerException("No Transaction found for given Account Number  " + accNumber);
			}
		} else {
			throw new TrackerException("Account Not found for given Account Number : " + accNumber);
		}

//		List<Long> accountNumbers = new ArrayList<Long>();
//		accountNumbers.add(accNumber);
//		accountRepository.findAllById(accountNumbers);
//		return null;
	}

	

}
