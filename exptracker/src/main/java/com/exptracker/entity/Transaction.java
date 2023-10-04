package com.exptracker.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.exptracker.enums.ExpendetureType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

	@Id

	@Column(name = "transaction_Id")
	private int transactionId;

	private LocalDate transactionDate;

	@Enumerated(EnumType.STRING)
	private ExpendetureType ExpendetureType;

	private float credit;

	private float withdrawal;

	private float closingBalance;

	private String location;

	private String otherDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_number", nullable = false)
	@JsonBackReference
	private Account accountRef;

}
