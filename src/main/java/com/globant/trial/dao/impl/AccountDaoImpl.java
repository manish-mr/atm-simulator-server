package com.globant.trial.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.globant.trial.dao.AccountDao;
import com.globant.trial.dao.exception.DaoException;
import com.globant.trial.dbobject.Account;
import com.globant.trial.util.HibernateUtil;

/**
 * This is concrete class providing implementation to {@link AccountDao}
 * 
 * @author Manish
 *
 */
public class AccountDaoImpl implements AccountDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private final static Logger LOGGER = Logger.getLogger(AccountDaoImpl.class.getName());

	@Override
	public List<Account> getAccountsBasedOnCriteria(Map<String, Object> map) throws DaoException {
		try {
			Session session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			List<Account> lstAccount = session.createCriteria(Account.class).add(Restrictions.allEq(map)).list();
			session.close();
			return lstAccount;
		} catch (Exception e) {
			LOGGER.severe("Error while fetching accounts based on criteria; Error message: "+e.getMessage());
			throw new DaoException(e);
		}
	}

	@Override
	public Account getAccountByAccountNumber(Integer accountNumber) throws DaoException {
		try {
			Session session = sessionFactory.openSession();
			Account account = (Account) session.get(Account.class, accountNumber);
			session.close();
			return account;
		} catch (Exception e) {
			LOGGER.severe("Error while fetching account with accountNumber: "+accountNumber+". Error: "+e.getMessage());
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Account account) throws DaoException {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			LOGGER.severe("Error while updating account with accountNumber: "+account.getAccountNumber()+". Error: "
					+ e.getMessage());
			throw new DaoException(e);
		}
	}
}
