package com.globant.trial.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import com.globant.trial.dao.AccountDao;
import com.globant.trial.dao.impl.AccountDaoImpl;
import com.globant.trial.dbobject.Account;
import com.globant.trial.service.AccountService;
import com.globant.trial.service.exception.ServiceException;

/**
 * Implementation for {@link AccountService} interface
 * 
 * @author Manish
 *
 */
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao = new AccountDaoImpl();

	private final static Logger LOGGER = Logger.getLogger(AccountServiceImpl.class.getName());
	
	@Override
	public boolean validateAccountDetails(Account account) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("accountNumber", account.getAccountNumber());
			map.put("cardNumber", account.getCardNumber());
			map.put("custFirstName", account.getCustFirstName());
			map.put("custLastName", account.getCustLastName());
			List<Account> lstAccount = accountDao.getAccountsBasedOnCriteria(map);
			if (lstAccount.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			LOGGER.severe("Error while validating account. Error: "+e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public Account validatePin(Account account) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("accountNumber", account.getAccountNumber());
			map.put("cardNumber", account.getCardNumber());
			map.put("custFirstName", account.getCustFirstName());
			map.put("custLastName", account.getCustLastName());
			map.put("pin", account.getPin());
			List<Account> lstAccount = accountDao.getAccountsBasedOnCriteria(map);
			if (lstAccount.isEmpty()) {
				return null;
			} else {
				return lstAccount.get(0);
			}
		} catch (Exception e) {
			LOGGER.severe("Error while validating PIN. Error: "+e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public Account validateToken(String token) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("authToken", token);
			List<Account> lstAccount = accountDao.getAccountsBasedOnCriteria(map);
			if (lstAccount.isEmpty()) {
				return null;
			} else {
				return lstAccount.get(0);
			}
		} catch (Exception e) {
			LOGGER.severe("Error while validating token. Error: "+e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Account getAccountByAccountNumber(Integer accountNumber) throws ServiceException {
		try {
			return accountDao.getAccountByAccountNumber(accountNumber);
		} catch (Exception e) {
			LOGGER.severe("Error while fetching account with accountNumber: "+accountNumber+". Error: "+e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateAccount(Account account) throws ServiceException {
		try {
			accountDao.update(account);
		} catch (Exception e) {
			LOGGER.severe("Error while updating account. Error: "+e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void assignRandomTokenToAccount(Account account) throws ServiceException {
		try {
			account.setAuthToken(UUID.randomUUID().toString());
			accountDao.update(account);
		} catch (Exception e) {
			LOGGER.severe("Error while assigning token to account with accountNumber: "+account.getAccountNumber()
			+". Error: "+e.getMessage());
			throw new ServiceException(e);
		}
	}
	
}
