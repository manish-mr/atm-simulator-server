package com.globant.trial.endpoint;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.globant.trial.dbobject.Account;
import com.globant.trial.dto.WithdrawAmountDto;

/**
 * This interface provides methods for {@link Account} related client operations
 * The implementing class should provide the definition to all the methods
 * 
 * @author Manish
 *
 */
public interface AccountEndpoint extends Endpoint{
	
	/**
	 * Get the ATM card data and intimate the client whether card is valid or not
	 * 
	 * @param account {@link Account} object containing account details
	 * @return
	 */
	Response swipe(Account account);
	
	/**
	 * Validate ATM pin with other details of ATM card
	 * 
	 * @param account {@link Account} object containing account details
	 * @return
	 */
	Response validatePin(Account account);
	
	/**
	 * Check the account balance
	 * 
	 * @param securityContext
	 * @return
	 */
	Response checkBalance(SecurityContext securityContext);
	
	/**
	 * Withdrawal of money from account
	 * 
	 * @param securityContext
	 * @param amount {@link WithdrawAmountDto} object containing amount needs to be withdrawn
	 * @return
	 */
	Response withdraw(SecurityContext securityContext, WithdrawAmountDto amount);
	
	/**
	 * Done with the operations, now invalidate session
	 * 
	 * @param securityContext
	 * @return
	 */
	Response done(SecurityContext securityContext);
}
