package com.n26.transation.statistics.service;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.n26.transaction.statistics.Utils.TimeUtils;
import com.n26.transaction.statistics.components.Statistics;
import com.n26.transaction.statistics.components.Transaction;
import com.n26.transaction.statistics.service.StatisticsService;
import com.n26.transaction.statistics.service.StatisticsServiceImpl;
import com.n26.transaction.statistics.service.TransactionService;
import com.n26.transaction.statistics.service.TransactionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TransactionServiceImpl.class,StatisticsServiceImpl.class})
public class StatisticServiceTest {
	
   @Autowired
   private StatisticsService statsService;
   
   @Autowired 
   private TransactionService transactionService;
   
   private Statistics statistics;
   
   private NumberFormat numberFormat;
   
   public StatisticServiceTest() {
	   numberFormat = NumberFormat.getInstance();
	   numberFormat.setMaximumFractionDigits(2);
   }
   
   @Before
   public void setUp(){
		createTransactions();
		statistics = statsService.getStatistics();
	}
   
   @Test
	public void testSum(){
		Assert.assertEquals(new Double(numberFormat.format(statistics.getSum())),new Double(14.00));
	}
   
    @Test
	public void testCountAndStatistics(){
    	
		Assert.assertEquals(statistics.getCount(),3L);
		
		try {
			
			//Test after 3 seconds
			Thread.sleep(3000);

			statistics = statsService.getStatistics();
			
			Assert.assertEquals(statistics.getCount(),2L);
			Assert.assertEquals(new Double(numberFormat.format(statistics.getSum())),new Double(10.00));
			Assert.assertEquals(new Double(numberFormat.format(statistics.getMin())),new Double(2.00));
			Assert.assertEquals(new Double(numberFormat.format(statistics.getMax())),new Double(8.00));
			Assert.assertEquals(new Double(numberFormat.format(statistics.getAvg())),new Double(5));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Test
	public void testAvg(){
	   Assert.assertEquals(new Double(numberFormat.format(statistics.getAvg())),new Double(4.67));
	}
		
	@Test
	public void testMax(){
		Assert.assertEquals(new Double(numberFormat.format(statistics.getMax())),new Double(8.00));
	}
		
	@Test
	public void testMin(){
		Assert.assertEquals(new Double(numberFormat.format(statistics.getMin())),new Double(2.00));
	}
		
		
	private void createTransactions() {
		
		transactionService.clearTransactions();
		
			List<Transaction> transactions = Arrays.asList(
					new Transaction(TimeUtils.timeBeforeSeconds(58),4d),
					new Transaction(TimeUtils.timeBeforeSeconds(2),8d),
					new Transaction(TimeUtils.timeBeforeSeconds(66),4d),
					new Transaction(TimeUtils.timeBeforeSeconds(0),2d));
			transactions.stream().forEach(t -> transactionService.addTransactions(t) );
	}


}
