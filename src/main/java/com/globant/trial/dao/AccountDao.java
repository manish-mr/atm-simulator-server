package com.globant.trial.dao;

import java.util.List;
import java.util.Map;

import com.globant.trial.dao.exception.DaoException;
import com.globant.trial.dbobject.Account;

/**
 * This interface provides methods for manipulating and accessing Account
 * entity data. The implementing class should provide the implementation of these
 * methods
 * 
 * @author Manish
 *
 */
public interface AccountDao {
	
	/**
	 * Gets the list of Accounts based on the criteria passed as parameter
	 * @param map a key value pair map used as criteria
	 * @return List of {@link Account} satisfying the criteria
	 * @throws DaoException
	 */
	List<Account> getAccountsBasedOnCriteria(Map<String, Object> map) throws DaoException;
	
	/**
	 * Get the {@link Account} object based on its accountNumber
	 * @param accountNumber
	 * @return
	 * @throws DaoException
	 */
	Account getAccountByAccountNumber(Integer accountNumber) throws DaoException;
	
	/**
	 * Update {@link Account}
	 * @param account {@link Account} object needs to be updated
	 * @throws DaoException
	 */
	void update(Account account) throws DaoException;
}
