package com.n26.transaction.statistics.service;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.transaction.statistics.Utils.TimeUtils;
import com.n26.transaction.statistics.components.Statistics;


@Service
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired 
	TransactionService transactionService;
	
	private static Long transactionUpdateTime = 0L;
	
	public static final ConcurrentHashMap<Long,Statistics> statisticsMap = new ConcurrentHashMap<Long,Statistics>();
	
	public StatisticsServiceImpl() {
	}
	
	@Override
	public void computeStatistics(final SortedMap<Long, List<Double>> transactionMap) {
		
		transactionUpdateTime = System.currentTimeMillis()/1000;
		
		//From current time to future 60 seconds calculating Statistics
		LongStream.range(0,61).parallel().forEach(
				n -> statisticsMap.compute(60-n, (key,value)-> {
					
					DoubleSummaryStatistics stats = transactionMap.tailMap(transactionUpdateTime - n )
							.entrySet().parallelStream().map(elem -> elem.getValue()).flatMap(Collection::parallelStream)
							.flatMapToDouble(elem -> DoubleStream.of(elem.doubleValue())).summaryStatistics();
					
					if(value==null)
						value = new Statistics(0d,0d,0d,0d,0);
					
					value.setAvg(stats.getAverage());
					value.setMax(stats.getMax());
					value.setMin(stats.getMin());
					value.setSum(stats.getSum());
					value.setCount(stats.getCount());
					
					return value;
					
							
				}));
		
		
		
	}
	
	@Override
	public Statistics getStatistics() {
				
		if(transactionUpdateTime > 0L && transactionService.getTransactions().size()>0 && TimeUtils.isWithinLastMinute(transactionService.getTransactions().lastKey() *1000))
			return statisticsMap.get((System.currentTimeMillis()/1000)-transactionUpdateTime);
		else if(transactionUpdateTime>0L)
				transactionService.clearTransactions();  //This will clear the old transactions
		
		return new Statistics(0d,0d,0d,0d,0);
	}
	
	

}
