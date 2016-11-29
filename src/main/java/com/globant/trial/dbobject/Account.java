package com.globant.trial.dbobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Account entity
 * 
 * @author Manish
 *
 */

@Entity
@Table(name = "account")
@DynamicUpdate(value = true)
public class Account implements Serializable {
	
	@Id
	@Column(name = "account_number")
	private Integer accountNumber;
	
	@Column(name = "card_number")
	private Integer cardNumber;
	
	@Column(name = "cust_first_name")
	private String custFirstName;
	
	@Column(name = "cust_last_name")
	private String custLastName;
	
	@Column(name = "balance")
	private Integer balance;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "branch_code")
	private Integer branchCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "pin")
	private Integer pin;
	
	@Column(name = "auth_token")
	private String authToken;
	
	@Column(name = "status")
	private String status;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2945571063458675970L;
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCustFirstName() {
		return custFirstName;
	}
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	public String getCustLastName() {
		return custLastName;
	}
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Integer getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
