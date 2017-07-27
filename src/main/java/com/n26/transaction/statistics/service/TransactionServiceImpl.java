package com.n26.transaction.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.transaction.statistics.Utils.TimeUtils;
import com.n26.transaction.statistics.components.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	StatisticsService statService;
	
	private static final SortedMap<Long,List<Double>> transactionMap = new TreeMap<Long,List<Double>>();
	
	public TransactionServiceImpl() {
		
	}

	@Override
	public boolean addTransactions(Transaction transaction) {
		
		if(transaction == null || transaction.getAmount() == null ||
				transaction.getTimeStamp()==null ||
				!TimeUtils.isWithinLastMinute(transaction.getTimeStamp()))
		{
			return false;
		}
		
		transactionMap.compute(transaction.getTimeStamp()/1000, (key,value)-> {
			if(value==null)
				value = new ArrayList<Double>();
			value.add(transaction.getAmount());
			return value;
		});
		
		statService.computeStatistics(transactionMap);	
		
		return true;
		
	}
	
	@Override
	public SortedMap<Long, List<Double>> getTransactions() {
		
			return transactionMap;
		
	}
	
	@Override
	public void clearTransactions() {
		
		transactionMap.clear();
		
	}
}
