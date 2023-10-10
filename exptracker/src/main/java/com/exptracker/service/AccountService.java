package com.exptracker.service;

import com.exptracker.entity.Account;
import com.exptracker.exception.TrackerException;

public interface AccountService {

	public Account createAccount(Account account) throws TrackerException;

	public Account readAccountByAccNumber(long accNumber) throws TrackerException;

	public String updateAccount(Account account, long accountNumber) throws TrackerException;

	public String deleteAccountByAccNumber(long accNumber) throws TrackerException;
}
