package com.bank.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cust_id")
	@NotNull
	private Integer custId;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "address", nullable = false)
	private String address;
	@NotNull
	@NotEmpty
	@Email
	@Size(max = 255)
	@Column(name = "email", nullable = false)
	private String email;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "enable", nullable = false)
	private String enable;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "name", nullable = false)
	private String name;
	@NotNull
	@NotEmpty
	@Size(min=4,max = 255)
	@Column(name = "phone", nullable = false)
	private String phone;
	@Column(name = "token")
	private String token;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="customer")
	private List<Account> accounts;

	//bi-directional many-to-one association to DocumentType
	@ManyToOne
	@JoinColumn(name="doty_id")
	private DocumentType documentType;

	//bi-directional many-to-one association to RegisteredAccount
	@OneToMany(mappedBy="customer")
	private List<RegisteredAccount> registeredAccounts;

	
}