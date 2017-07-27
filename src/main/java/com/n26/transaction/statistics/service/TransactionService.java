package com.n26.transaction.statistics.service;

import java.util.List;
import java.util.SortedMap;

import com.n26.transaction.statistics.components.Transaction;

public interface TransactionService {
	
	public boolean addTransactions(Transaction transaction);

	public SortedMap<Long,List<Double>> getTransactions();
	
	public void clearTransactions();

}
