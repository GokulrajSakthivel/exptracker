package com.exptracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exptracker.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
