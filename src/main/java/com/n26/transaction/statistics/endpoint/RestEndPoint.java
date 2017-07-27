package com.n26.transaction.statistics.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import com.n26.transaction.statistics.components.Statistics;
import com.n26.transaction.statistics.components.Transaction;
import com.n26.transaction.statistics.service.StatisticsService;
import com.n26.transaction.statistics.service.TransactionService;

@Path("/")
public class RestEndPoint {

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	StatisticsService statsService;
	
	
	@Path("/transactions")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTransaction(Transaction transaction)
	{
		if( ! transactionService.addTransactions(transaction))
			return Response.status(204).build();
		
		return Response.status(201).build();
		
	}
	
	
	@GET
	@Path("/getStatistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Statistics getTransactions()
	{
		return statsService.getStatistics();
		
	}
}
