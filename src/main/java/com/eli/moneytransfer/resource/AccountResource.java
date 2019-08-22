package com.eli.moneytransfer.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.eli.moneytransfer.model.Account;
import com.eli.moneytransfer.service.AccountService;

@Path("/accounts")
public class AccountResource {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts() {  	  
	  // Response response = Response.ok(entity).build(); return response;
	  
	  	//return AccountService.getAccounts(); 
	  	return Response.ok(AccountService.getAccounts()).build();
	  }
	 

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("id") int accountId) {
		System.out.println(AccountService.getAccount(accountId) + " from json section");
		//return AccountService.getAccount(accountId);
		Account acc = AccountService.getAccount(accountId);
		if (acc != null)
			return Response.ok(acc).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account) {
		System.out.println("creating new account");
		Account acc = AccountService.createAccount(account);
		if (acc != null)
			return Response.ok(acc).status(Status.CREATED).build();
		else 
			return Response.status(Status.UNAUTHORIZED).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("id") int accountId, Account account) {
		System.out.println("in updateAccount");
		Account acc = AccountService.updateAccount(accountId, account);
		if (acc != null)
			return Response.ok(acc).status(Status.CREATED).build();
		else {
			return Response.status(Status.NOT_MODIFIED).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteAccount(@PathParam("id") int accountId) {
		Account acc = AccountService.deleteAccount(accountId);
		if (acc != null)
			return Response.ok(acc).status(Status.NO_CONTENT).build();
		else {
			return Response.status(Status.NOT_IMPLEMENTED).build();
		}
		
	}

	@POST
	@Path("/transfer/{fromId}/{toId}/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	public void tranferAmount(@PathParam("fromId") int fromId, @PathParam("toId") int toId,
			@PathParam("amount") Float amount) {
		AccountService.transferMoney(fromId, toId, amount);
	}

}