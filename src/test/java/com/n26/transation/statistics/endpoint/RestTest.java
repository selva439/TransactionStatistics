package com.n26.transation.statistics.endpoint;

import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.n26.transaction.statistics.Utils.TimeUtils;
import com.n26.transaction.statistics.components.Statistics;
import com.n26.transaction.statistics.service.StatisticsService;
import com.n26.transaction.statistics.service.StatisticsServiceImpl;
import com.n26.transaction.statistics.service.TransactionService;
import com.n26.transaction.statistics.service.TransactionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TransactionServiceImpl.class,StatisticsServiceImpl.class})
public class RestTest {
	
	@Mock
	TestRestTemplate restTemplate;
	
	@Autowired
	StatisticsService statisticsService;
	
	@Autowired
	TransactionService transactionService;
	
	public RestTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void setUp()
	{
		when((restTemplate.getForEntity("/getStatistics",Statistics.class))).thenReturn((new ResponseEntity<Statistics>(statisticsService.getStatistics(),HttpStatus.OK)));

	}
	
	@Test
    public void testGet() {
		
        ResponseEntity<Statistics> response = restTemplate.getForEntity("/getStatistics", Statistics.class);
        
        Assert.assertTrue(response.getBody().equals(new Statistics()));
        
    }

}