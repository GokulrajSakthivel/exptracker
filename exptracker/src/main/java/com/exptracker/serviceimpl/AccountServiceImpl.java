package com.exptracker.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.entity.Account;
import com.exptracker.entity.Customer;
import com.exptracker.exception.TrackerException;
import com.exptracker.repository.AccountRepository;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Account createAccount(Account account) throws TrackerException {

		Optional<Customer> optional = customerRepository.findById(account.getCustomerRef().getCustomerId());
		if (!optional.isPresent()) {
			throw new TrackerException(" Invalid Customer ID ");
		}

		if (account.getBankName().length() <= 3) {
			throw new TrackerException("Invalid Bank Name");
		}

		
		return  accountRepository.save(account);
	}

	@Override
	public Account readAccountByAccNumber(long accNumber) throws TrackerException {
		Optional<Account> optional = accountRepository.findById(accNumber);
		if (!optional.isPresent()) {
			throw new TrackerException("Account Not found  :" + accNumber);	
		}
		return optional.get();
	}

	@Override
	public String updateAccount(Account account, long accountNumber) throws TrackerException {

//		Optional<Customer> customerOptional = customerRepository.findById(account.getCustomerRef().getCustomerId());
//		if (!customerOptional.isPresent()) {
//			throw new CustomerException(" Invalid Customer ID ");
//		}

		Optional<Account> optional = accountRepository.findById(accountNumber);
		if (!optional.isPresent()) {
			throw new TrackerException(" Account Number Not Found :" + accountNumber);
		}

		Account existingAccount = optional.get();

		existingAccount.setAccountBalance(account.getAccountBalance());
		existingAccount.setBankName(account.getBankName());
		existingAccount.setAccountType(account.getAccountType());

		accountRepository.save(existingAccount);
		return "Updated Successfully";
	}

	@Override
	public String deleteAccountByAccNumber(long accNumber) throws TrackerException {
		Optional<Account> optional = accountRepository.findById(accNumber);
		if (!optional.isPresent()) {
			throw new TrackerException("Account not found for account Number : " + accNumber);
		}
		accountRepository.delete(optional.get());
		return " Deleted Successfully";
	}
}
