package com.eli.moneytransfer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.eli.moneytransfer.model.Account;


public class AccountService {

	private static Map<Integer, Account> accounts = new HashMap<Integer, Account>();
	private static final AtomicInteger idCreator = new AtomicInteger();
	private static Object createLock = new Object();
	private static Object updateLock = new Object();
	private static Object deleteLock = new Object();
	private static Object transferLock = new Object();

	
	public static List<Account> getAccounts() { 
		//return accounts.values();
		
		/*
		 * ArrayList list = new ArrayList(); Iterator iterator =
		 * accounts.values().iterator(); while (iterator.hasNext()) {
		 * list.add(iterator.next()); } return list;
		 */
		 return new ArrayList(accounts.values());
		 //return Response.ok(accounts.values()).build();
	}

	public static Account getAccount(int id) {
		return accounts.get(id);
	}

	public static Account createAccount(Account newAccount) {
		Account acct;
		// default id if non is supplied, id == 0
		if (newAccount.getId() == 0) {
			acct = new Account(newAccount.getName(), newAccount.getBalance());
			System.out.println(acct.toString());
		}
		else {
			acct = newAccount;
		}
		// only one thread can perform this action at a time
		synchronized (createLock) {
			accounts.put(acct.getId(), acct);
		}
		return acct;
	}

	public static Account updateAccount(Account accountUpdate) {
		System.out.println(accountUpdate.toString());
		// only one thread can perform this action at a time
		synchronized (updateLock) {
			// only one thread can perform this action at a time
			accounts.put(accountUpdate.getId(), accountUpdate);
		}
		return accountUpdate;
	}

	public static Account deleteAccount(int id) {
		// only one thread can perform this action at a time
		synchronized (deleteLock) {
			return accounts.remove(id);
		}
	}
	
	public static void transferMoney(int idFrom, int idTo, Float amount) {
		System.out.println("Transfer the amount: " + amount + " from Account id: " + idFrom + " to Account id: " + idTo );
		
		
		  Account fromAccount = accounts.get(idFrom); 
		  Account toAccount =  accounts.get(idTo);
		  if (fromAccount == null) {
			  System.out.println("Account with id: " + fromAccount + " does not exist. Please use a valid account id");
			  return;
		  }
		  if (toAccount == null) {
			  System.out.println("Account with id: " + toAccount + " does not exist. Please use a valid account id");
			  return;
		  }
		  
		  synchronized(transferLock) 
		  { 
			  //accounts.remove(idFrom); 
			  //accounts.remove(idTo);
			  fromAccount.withdraw(amount); 
			  toAccount.deposit(amount); 
			  accounts.put(idFrom, fromAccount); 
			  accounts.put(idTo, toAccount); 
		  }
		  
		  //return accounts.values();		
	}
	
	public static void emtpyList()
	{
		accounts.clear();
	}
}
