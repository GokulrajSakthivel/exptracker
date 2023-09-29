package com.exptracker.service;

import com.exptracker.entity.Account;
import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;

public interface AccountInter {

	public Account createAccount(Account account) throws CustomerException;

	public Account readAccountByAccNumber(int accNumber) throws CustomerException;

	public String updateAccount(Account account) throws CustomerException;

	public String deleteAccountByAccNumber(int accNumber) throws CustomerException;

}
