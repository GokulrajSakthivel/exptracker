
package com.exptracker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.dto.AccountDto;
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
	
	@Autowired
	private ModelMapper mapper;
	

	
	@Override
	public String createAccount(Account account , int customerId) throws TrackerException {

		Optional<Customer> optional = customerRepository.findById(customerId);
		if (!optional.isPresent()) {
			throw new TrackerException(" Invalid Customer ID ");
		}

		if (account.getBankName().length() < 3) {
			throw new TrackerException("Invalid Bank Name");
		}
		
		if (account.getAccountBalance() < 0) {
			throw new TrackerException("Invalid Account Balance : " + account.getAccountBalance());
		}

		account.setCustomerRef(optional.get());
		accountRepository.save(account);
		return "Account Created Successfully";
	}

	@Override
	public AccountDto readAccountByAccNumber(long accNumber) throws TrackerException {
		Optional<Account> optional = accountRepository.findById(accNumber);
		if (!optional.isPresent()) {
			throw new TrackerException("Account Not found  :" + accNumber);	
		}
		return mapper.map(optional.get(), AccountDto.class);
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
		if (account.getBankName().length() < 3) {
			throw new TrackerException("Invalid Bank Name");
		}
		if (account.getAccountBalance() < 0) {
			throw new TrackerException("Invalid Account Balance : " + account.getAccountBalance());
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

	@Override
	public List<Account> readAccountByCustomer(int customerId) throws TrackerException {
      List<Account>	listOfAccounts = customerRepository.findById(customerId).get().getAccounts();
      
		return listOfAccounts;
	}
	
	
}
