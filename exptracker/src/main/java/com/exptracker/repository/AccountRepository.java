package com.exptracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exptracker.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
