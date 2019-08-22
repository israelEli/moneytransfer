package com.eli.moneytransfer.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

	private static AtomicInteger idCreator = new AtomicInteger(1);

	private int id;
	private String name;
	private Float balance;

	public Account() {}

	public Account(String name, Float balance) {
		super();
		this.id = idCreator.getAndIncrement();
		this.name = name;
		this.balance = balance;

	}

	public void setId(int id) {
		this.id = idCreator.getAndIncrement();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public void deposit(Float amount) {
		balance += amount;
	}

	public void withdraw(Float amount) {
		balance -= amount;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Account other = (Account) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

}
