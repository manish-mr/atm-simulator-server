package com.globant.trial.service;

import com.globant.trial.dbobject.Account;
import com.globant.trial.service.exception.ServiceException;

/**
 * This interface provides methods for handling the business logic related to
 * {@link Account} entity
 * 
 * @author Manish
 *
 */
public interface AccountService {
	
	/**
	 * Validate account details passed as a parameter
	 * 
	 * @param account {@link Account} object which contains account details needs 
	 * to be validated
	 * @return true if {@link Account} exists, false otherwise
	 * @throws ServiceException
	 */
	boolean validateAccountDetails(Account account) throws ServiceException;
	
	/**
	 * Validate account based on passed details and pin
	 * 
	 * @param account {@link Account} object which also contains pin
	 * @return {@link Account} object satisfying all conditions which is retrieved from 
	 * database, null otherwise
	 * @throws ServiceException
	 */
	Account validatePin(Account account) throws ServiceException;
	
	/**
	 * Validate the authentication token and return the related {@link Account} object
	 * 
	 * @param token authentication token that needs to be validated
	 * @return {@link Account} object having required authentication token, null otherwise
	 * @throws ServiceException
	 */
	Account validateToken(String token) throws ServiceException;
	
	/**
	 * Get {@link Account} based on account number
	 * 
	 * @param accountNumber
	 * @return
	 * @throws ServiceException
	 */
	Account getAccountByAccountNumber(Integer accountNumber) throws ServiceException;
	
	/**
	 * Update {@link Account}
	 * 
	 * @param account
	 * @throws ServiceException
	 */
	void updateAccount(Account account) throws ServiceException;

	/**
	 * Generates and assigns random UUID to Account
	 * @param account {@link Account} that needs to be manipulated
	 * @throws ServiceException
	 */
	void assignRandomTokenToAccount(Account account) throws ServiceException;	
}
