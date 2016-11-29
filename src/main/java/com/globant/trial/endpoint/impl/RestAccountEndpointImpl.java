package com.globant.trial.endpoint.impl;

import java.security.Principal;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.globant.trial.common.Constants;
import com.globant.trial.dbobject.Account;
import com.globant.trial.dto.AccountDto;
import com.globant.trial.dto.WithdrawAmountDto;
import com.globant.trial.endpoint.AccountEndpoint;
import com.globant.trial.security.Secured;
import com.globant.trial.service.AccountService;
import com.globant.trial.service.impl.AccountServiceImpl;


/**
 * REST implementation of {@link AccountEndpoint}
 * 
 * @author Manish
 *
 */
@Path("account")
public class RestAccountEndpointImpl implements AccountEndpoint{
	
	AccountService accountService = new AccountServiceImpl();
	
	private final static Logger LOGGER = Logger.getLogger(RestAccountEndpointImpl.class.getName());


	@POST
	@Path("/swipe")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response swipe(Account account) {
		try {
			boolean isValidAccount = accountService.validateAccountDetails(account);
			if(isValidAccount){
				return Response.status(Response.Status.OK).entity(RESPONSE_MSG_SUCCESS).build();
			} else {
				return Response.status(Response.Status.FORBIDDEN).entity(RESPONSE_MSG_INVALID).build();
			}	
		} catch (Exception e) {
        	LOGGER.severe("ATM swipe failed. Error"+e.getMessage());
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
	}

	@POST
	@Path("/validatePin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response validatePin(Account account) {
		try {
			account = accountService.validatePin(account);
			if(account != null){
				accountService.assignRandomTokenToAccount(account);
				return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).
						entity(transformAccountToAccountDto(account)).build();
			} else {
				return Response.status(Response.Status.FORBIDDEN).entity("Incorrect PIN").build();
			}	
		} catch (Exception e) {
        	LOGGER.severe("ATM validate PIN failed. Error: "+e.getMessage());
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
	}

	@GET
	@Secured
	@Path("/balance")
	@Override
	public Response checkBalance(@Context SecurityContext securityContext) {
		try {
			Principal principal = securityContext.getUserPrincipal();
			Integer accountNumber = Integer.parseInt(principal.getName());
			Account account = accountService.getAccountByAccountNumber(accountNumber);
			if(account != null){
				return Response.status(Response.Status.OK).entity(account.getBalance()).build();
			} else {
				return Response.status(Response.Status.FORBIDDEN).entity(RESPONSE_MSG_ERROR).build();
			}
		} catch (Exception e) {
        	LOGGER.severe("Balance check failed. Error: "+e.getMessage());
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
	}

	@POST
	@Secured
	@Path("/withdraw")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response withdraw(@Context SecurityContext securityContext, WithdrawAmountDto amount) {
		try {
			Principal principal = securityContext.getUserPrincipal();
			Integer accountNumber = Integer.parseInt(principal.getName());
			Account account = accountService.getAccountByAccountNumber(accountNumber);
			if(account != null){
				if(Constants.ACCOUNT_INACTIVE.equals(account.getStatus()))
					return Response.status(Response.Status.FORBIDDEN).entity("Account locked").build();
				if(account.getBalance() < amount.getAmount()){
					return Response.status(Response.Status.FORBIDDEN).entity("Insufficient balance").build();
				} else {
					if(amount.getAmount() % 100 != 0)
						return Response.status(Response.Status.FORBIDDEN).entity("Incorrect denomination(Please "
								+ "enter mutiples of 100)").build();
					account.setBalance(account.getBalance() - amount.getAmount());
					accountService.updateAccount(account);
					return Response.status(Response.Status.OK).entity(account.getBalance()).build();
				}
			} else {
				return Response.status(Response.Status.FORBIDDEN).entity(RESPONSE_MSG_ERROR).build();
			}
		} catch (Exception e) {
        	LOGGER.severe("Withdrawal failed. Error: "+e.getMessage());
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GET
	@Secured
	@Path("/done")
	@Override
	public Response done(@Context SecurityContext securityContext) {
		try {
			Principal principal = securityContext.getUserPrincipal();
			Integer accountNumber = Integer.parseInt(principal.getName());
			Account account = accountService.getAccountByAccountNumber(accountNumber);
			if(account != null){
				accountService.assignRandomTokenToAccount(account);
				return Response.status(Response.Status.OK).entity(RESPONSE_MSG_SUCCESS).build();
			} else {
				return Response.status(Response.Status.FORBIDDEN).entity(RESPONSE_MSG_INVALID).build();
			}	
		} catch (Exception e) {
        	LOGGER.severe("ATM validate PIN failed. Error: "+e.getMessage());
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	/**
	 * Transform {@link Account} object to {@link AccountDto}
	 * 
	 * @param account
	 * @return
	 */
	private AccountDto transformAccountToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAuthToken(account.getAuthToken());
		return accountDto;
	}
	
}
