package com.globant.trial.dao.exception;

/**
 * DAO layer exception for the application
 * 
 * @author Manish
 *
 */
public class DaoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8488449648555760588L;

	/** default constructor for {@link DaoException}*/
	public DaoException() {
		super();
	}
	
	/** Construct a new {@link DaoException} with specified message */
	public DaoException(String message) {
		super(message);
	}
	
	/** Construct a new {@link DaoException} with specified {@link Throwable} instance */
	public DaoException(Throwable th) {
		super(th);
	}
}
