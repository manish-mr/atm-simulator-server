package com.globant.trial.security;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.globant.trial.service.AccountService;
import com.globant.trial.service.impl.AccountServiceImpl;

/**
 * This filter takes care of the authentication criteria needed during API calls
 * 
 * @author Manish
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
	private AccountService accountService = new AccountServiceImpl();
	
	private final static Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getName());
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		// Get the HTTP Authorization header
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();
		try {
			com.globant.trial.dbobject.Account account = accountService.validateToken(token);
			if (account == null) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			} else {
				SecurityContext originalContext = requestContext.getSecurityContext();
		        Authorizer authorizer = new Authorizer(account.getAccountNumber(), originalContext.isSecure());
		        requestContext.setSecurityContext(authorizer);
			}
		} catch(Exception e) {
			LOGGER.severe("Error in authentication filter: "+e.getMessage());
		}
	}
	
	public static class Authorizer implements SecurityContext {
        
		Integer accountNumber;
        boolean isSecure;
        
        public Authorizer(final Integer accountNumber, boolean isSecure) {
            this.accountNumber = accountNumber;
            this.isSecure = isSecure;
        }

        @Override
        public Principal getUserPrincipal() {
            return new Account(accountNumber);
        }
        
        @Override
        public boolean isSecure() {
            return isSecure;
        }

        @Override
        public String getAuthenticationScheme() {
            return "";
        }

		@Override
		public boolean isUserInRole(String role) {
			return false;
		} 
    }
	
	public static class Account implements Principal{
		Integer accountNumber;
		
		public Account(Integer accountNumber){
			this.accountNumber = accountNumber;
		}

		@Override
		public String getName() {
			return accountNumber.toString();
		}
	}

}