package com.exptracker.service;

import java.util.List;

import com.exptracker.dto.AccountDto;
import com.exptracker.entity.Account;
import com.exptracker.exception.TrackerException;

public interface AccountService {
	
	
	public String createAccount(Account account , int customerId) throws TrackerException;

	public AccountDto readAccountByAccNumber(long accNumber) throws TrackerException;

	public String updateAccount(Account account, long accountNumber) throws TrackerException;

	public String deleteAccountByAccNumber(long accNumber) throws TrackerException;
	
	public List<Account> readAccountByCustomer(int customerId) throws TrackerException;
}
