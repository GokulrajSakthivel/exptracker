package com.exptracker.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	private int doorNo;

	@Column(length = 25)
	private String streetName;

	@Column(length = 25)
	private String city;

	@Column(length = 25)
	private String state;

	private int pinCode;

	@ManyToOne
	@JoinColumn(name = "customer_id" , nullable = false)
	private Customer customer;

}
