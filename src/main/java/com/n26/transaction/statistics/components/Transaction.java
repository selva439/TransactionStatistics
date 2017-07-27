package com.n26.transaction.statistics.components;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 4782612550980962226L;

	private Long timeStamp;
	private Double amount;
	
	public Transaction() {
		
	}
	
	public Transaction(Long timeStamp, Double amount) {
		this.timeStamp = timeStamp;
		this.amount = amount;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Transaction [timeStamp=" + timeStamp + ", amount=" + amount + "]";
	}
	
	
	
	
	
	
}
