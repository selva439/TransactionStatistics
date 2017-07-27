package com.n26.transation.statistics.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.n26.transaction.statistics.Utils.TimeUtils;
import com.n26.transaction.statistics.components.Transaction;
import com.n26.transaction.statistics.service.StatisticsServiceImpl;
import com.n26.transaction.statistics.service.TransactionService;
import com.n26.transaction.statistics.service.TransactionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TransactionServiceImpl.class,StatisticsServiceImpl.class})
public class TransactionServiceTest {
	
	   @Autowired 
	   private TransactionService transactionService;
	   
	   @Test
	   public void testInvalidTransactions()
	   {
		   //Not within 60 secs
		   boolean flag = transactionService.addTransactions(new Transaction(TimeUtils.timeBeforeSeconds(61),4d));
		   Assert.assertFalse(flag);
		   
		   //Timestamp null
		   flag = transactionService.addTransactions(new Transaction(null,4d));
		   Assert.assertFalse(flag);
		   
		   //Amount null
		   flag = transactionService.addTransactions(new Transaction(TimeUtils.timeBeforeSeconds(10),null));
		   Assert.assertFalse(flag);
		   
		   //Transactions null
		   flag = transactionService.addTransactions(null);
		   Assert.assertFalse(flag);
	   }
	   
	   @Test
	   public void testTransactions()
	   {
		   transactionService.clearTransactions();
		   
		   boolean flag = transactionService.addTransactions(new Transaction(TimeUtils.timeBeforeSeconds(2),4d));
		   Assert.assertTrue(flag);
		   
		   Assert.assertEquals(transactionService.getTransactions().size(),1);
		   
		   Long time = TimeUtils.timeBeforeSeconds(2)/1000;
		   
		   Assert.assertEquals(new Double(transactionService.getTransactions().get(time).get(0)),new Double(4d));
		   
		   
	   }
	   
		
		

}
