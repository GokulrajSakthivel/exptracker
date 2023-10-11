package com.exptracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exptracker.dto.AccountDto;
import com.exptracker.entity.Account;
import com.exptracker.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping(value = "createAccount")
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		return new ResponseEntity<>(accountService.createAccount(account),HttpStatus.CREATED);
	}

	@GetMapping(value = "readAccount/{accNum}")
	public ResponseEntity<AccountDto> readAccountByAccNumber(@PathVariable long accNum) {
		return new ResponseEntity<>(accountService.readAccountByAccNumber(accNum),HttpStatus.OK);
	}

	@PutMapping(value = "updateAccount/{accountNumber}")
	public ResponseEntity<String> updateAccountByAccountNumber(@RequestBody Account account ,@PathVariable long accountNumber) {
		return new ResponseEntity<>(accountService.updateAccount(account , accountNumber),HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "deleteAccount/{accNumber}")
	public ResponseEntity<String> deleteaccount(long accNumber) {
		return new ResponseEntity<>(accountService.deleteAccountByAccNumber(accNumber),HttpStatus.ACCEPTED);
	}

}
