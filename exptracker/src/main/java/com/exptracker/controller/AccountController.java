package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.entity.Account;
import com.exptracker.repository.AccountRepository;

@RestController
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;

	@PostMapping(value = "createTransaction")
	public Account createTransaction(@RequestBody Account account) {
		return accountRepository.save(account);
	}

}
