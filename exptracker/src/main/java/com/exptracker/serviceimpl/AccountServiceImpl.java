package com.exptracker.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.entity.Account;
import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;
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
	public Account createAccount(Account account) throws CustomerException {
		Account account2 = null;

		if (account.getBankName().length() <= 3) {
			throw new CustomerException("Invalid Bank Name");
		}

		Optional<Customer> optional = customerRepository.findById(account.getCustomerRef().getCustomerId());
		
		if(!optional.isPresent()) {
			throw new CustomerException(" Invalid Customer ID ");
		}

		account2 = accountRepository.save(account);
		return account2;
	}

	@Override
	public Account readAccountByAccNumber(long accNumber) throws CustomerException {
		Optional<Account> optional = accountRepository.findById(accNumber);
		Account account = null;
		if (optional.isPresent()) {
			account = optional.get();
		} else {
			throw new CustomerException("Account Not found  :" + accNumber);
		}
		return account;
	}

	@Override
	public String updateAccount(Account account) throws CustomerException {
		Optional<Account> optional = accountRepository.findById(account.getAccountNumber());
		Account UpdatedAccount = new Account();
		if (optional.isPresent()) {

			Account existingAccount = accountRepository.findById(account.getAccountNumber()).get();
			UpdatedAccount.setAccountNumber(existingAccount.getAccountNumber());
			UpdatedAccount.setBankName(existingAccount.getBankName());
			UpdatedAccount.setAccountType(account.getAccountType());
			UpdatedAccount.setCustomerRef(existingAccount.getCustomerRef());
			accountRepository.save(UpdatedAccount);
			return "Updated Successfully";
		} else {
			throw new CustomerException(" Account Number Not Found :" + account.getAccountNumber());
		}

	}

	@Override
	public String deleteAccountByAccNumber(long accNumber) throws CustomerException {
		Account account = readAccountByAccNumber(accNumber);
		String message = "";
		if (account != null) {
			accountRepository.delete(account);
			message = " Deleted Successfully";
		} else {
			throw new CustomerException("Account not found for account Number : " + accNumber);
		}
		return message;
	}
}
