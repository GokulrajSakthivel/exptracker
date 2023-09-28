package com.exptracker.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

	@Id
	
	@Column(name = "account_number")
	private long accountNumber;

	@Column(length = 25)
	private String bankName;

	private String accountType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference
	private Customer customerRef;
	
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountRef")
	private List<Transaction> transactions;

}