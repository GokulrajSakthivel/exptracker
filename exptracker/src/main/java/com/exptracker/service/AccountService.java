package com.exptracker.service;

import com.exptracker.entity.Account;
import com.exptracker.exception.CustomerException;

public interface AccountService {

	public Account createAccount(Account account) throws CustomerException;

	public Account readAccountByAccNumber(long accNumber) throws CustomerException;

	public String updateAccount(Account account) throws CustomerException;

	public String deleteAccountByAccNumber(long accNumber) throws CustomerException;
}
