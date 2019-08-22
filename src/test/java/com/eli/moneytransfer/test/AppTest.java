package com.eli.moneytransfer.test;

import java.util.concurrent.atomic.AtomicInteger;

import com.eli.moneytransfer.model.Account;
import com.eli.moneytransfer.service.AccountService;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	private static final AtomicInteger idCreator = new AtomicInteger();
	private Account acct1;
	private Account acct2;
	private Account acct3;
	private Account acct4;
	
	
	public void testAccountCreation1()
	{
		acct1 = new Account("Tony Montana", 12000000.0F);		
		System.out.println(acct1);
		assertNotNull(acct1);
		assertEquals("Tony Montana", acct1.getName());
		
		AccountService.createAccount(acct1);
		assertTrue(AccountService.getAccounts().size() > 0);
	}	
	
	public void testAccountCreation2()
	{
		acct2 = new Account("Tony Soprano", 4000000.0F);		
		assertNotNull(acct2);
		assertFalse("Tony Montana".equals(acct2.getName()));
	}	
	
	public void testAccountCreation3()
	{
		acct3 = new Account( "Tony Stark", 500000000.0F);		
		assertNotNull(acct3);
		assertEquals("Tony Stark", acct3.getName());
	}
	
	public void testServiceCreation()
	{
		// before accounts are added to the service
		AccountService.emtpyList();
		assertTrue(AccountService.getAccounts().size() == 0);
	}
	
	public void testAddingAccount()
	{
		AccountService.emtpyList();
		acct1 = new Account("Tony Montana", 12000000.0F);		
		AccountService.createAccount(acct1);
		System.out.println(AccountService.getAccounts().size());
		assertTrue(AccountService.getAccounts().size() == 1);
	}
	
	public void testAccountUpdate()
	{
		AccountService.emtpyList();
		acct1 = new Account("Tony Montana", 12000000.0F);		
		AccountService.createAccount(acct1);
		acct4 = new Account("Tony Montana", 24000000.0F);		
		Account updatedAcc = AccountService.updateAccount(acct1.getId(), acct4);
		System.out.println(AccountService.getAccounts().size());
		assertTrue(AccountService.getAccount(acct1.getId()).getBalance() == 24000000.0F );
	}
	
	public void testAddingMultipleAccounts() 
	{
		AccountService.emtpyList();
		acct1 = new Account("Tony Montana", 12000000.0F);	
		acct2 = new Account("Tony Soprano", 4000000.0F);	
		acct3 = new Account( "Tony Stark", 500000000.0F);
		AccountService.createAccount(acct1);
		AccountService.createAccount(acct2);
		AccountService.createAccount(acct3);
		System.out.println(AccountService.getAccounts().size() + " size");
		assertTrue(AccountService.getAccounts().size() == 3);		
	}
	
	
	public void testSimpleTransfer() 
	{
		acct1 = new Account("Tony Montana", 12000000.00F);	
		acct2 = new Account("Tony Soprano", 4000000.00F);	
		AccountService.createAccount(acct1);
		AccountService.createAccount(acct2);
		AccountService.transferMoney(acct1.getId(), acct2.getId(), 450000.00F);
		assertTrue(acct1.getBalance() == 11550000.00F);		
		assertTrue(acct2.getBalance() == 4450000.00F);		
	}
	
	
	public void testSimultaneousTransfers() throws InterruptedException
	{
		acct1 = new Account("Tony Montana", 12000000.0F);	
		acct2 = new Account("Tony Soprano", 4000000.0F);	
		acct3 = new Account( "Tony Stark", 500000000.0F);	
		AccountService.createAccount(acct1);
		AccountService.createAccount(acct2);
		AccountService.createAccount(acct3);	
		
		Thread t1 = new Thread() {
			public void run() {
				AccountService.transferMoney(acct1.getId(), acct2.getId(), 450000.00F);				
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				AccountService.transferMoney(acct2.getId(), acct3.getId(), 45000.00F);				
			}
		};
		
		Thread t3 = new Thread() {
			public void run() {
				AccountService.transferMoney(acct1.getId(), acct3.getId(), 1000000.00F);				
			}
		};
		
		Thread t4 = new Thread() {
			public void run() {
				AccountService.transferMoney(acct3.getId(), acct1.getId(), 20000.00F);				
			}
		};
		
		Thread t5 = new Thread() {
			public void run() {
				AccountService.transferMoney(acct1.getId(), acct3.getId(), 45000.08F);				
			}
		};
		
		Thread t6 = new Thread() {
			public void run() {
				AccountService.transferMoney(acct2.getId(), acct3.getId(), 2450.00F);				
			}
		};
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
		assertTrue(acct1.getBalance() == 10524999.92F);
		assertTrue(acct2.getBalance() == 4402550.00F);
		assertTrue(acct3.getBalance() == 501072450.08F);
		
	}
	
	
	public void testDeleteAccount()
	{
		acct1 = new Account("Tony Montana", 12000000.0F);	
		acct2 = new Account("Tony Soprano", 4000000.0F);	
		AccountService.createAccount(acct1);
		AccountService.createAccount(acct2);
		AccountService.deleteAccount(acct1.getId());
		assertTrue(AccountService.getAccount(acct1.getId()) == null);
	}
	
	
	
	
	
	
	
}
