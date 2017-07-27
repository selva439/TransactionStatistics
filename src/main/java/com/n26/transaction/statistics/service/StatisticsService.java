package com.n26.transaction.statistics.service;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.n26.transaction.statistics.components.Statistics;
import com.n26.transaction.statistics.components.Transaction;

public interface StatisticsService {
	
	public void computeStatistics(final SortedMap<Long, List<Double>> transaction);
	
	public Statistics getStatistics();
	
	
	
	
	
}
