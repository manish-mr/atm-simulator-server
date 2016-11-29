package com.globant.trial.service.exception;

/**
 * Service layer exception for the application
 * 
 * @author Manish
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1645657379136475644L;

	/** default constructor for {@link ServiceException}*/
	public ServiceException() {
		super();
	}
	
	/** Construct a new {@link ServiceException} with specified message */
	public ServiceException(String message) {
		super(message);
	}
	
	/** Construct a new {@link ServiceException} with specified {@link Throwable} instance */
	public ServiceException(Throwable th) {
		super(th);
	}
}
