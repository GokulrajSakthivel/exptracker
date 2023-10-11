package com.exptracker.service;

import com.exptracker.dto.AccountDto;
import com.exptracker.entity.Account;
import com.exptracker.exception.TrackerException;

public interface AccountService {

	public String createAccount(Account account) throws TrackerException;

	public AccountDto readAccountByAccNumber(long accNumber) throws TrackerException;

	public String updateAccount(Account account, long accountNumber) throws TrackerException;

	public String deleteAccountByAccNumber(long accNumber) throws TrackerException;
}
