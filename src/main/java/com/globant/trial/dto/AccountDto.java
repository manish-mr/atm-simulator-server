package com.globant.trial.dto;

/**
 * Account DTO, used for transferring/sharing Account data via web service
 * interface
 * 
 * @author Manish
 *
 */

public class AccountDto {
	private Integer accountNumber;
	private String authToken;
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
