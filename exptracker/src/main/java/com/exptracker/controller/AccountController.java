package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.entity.Account;
import com.exptracker.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping(value = "createAccount")
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping(value = "readAccount/{accNum}")
	public Account readAccountByAccNumber(@PathVariable long accNum) {
		return accountService.readAccountByAccNumber(accNum);
	}

	@PutMapping(value = "updateAccount")
	public String updateAccountByAccountNumber(@RequestBody Account account) {
		return accountService.updateAccount(account);
	}

	@DeleteMapping(value = "deleteAccount/{accNumber}")
	public String deleteaccount(long accNumber) {
		return accountService.deleteAccountByAccNumber(accNumber);
	}

}
