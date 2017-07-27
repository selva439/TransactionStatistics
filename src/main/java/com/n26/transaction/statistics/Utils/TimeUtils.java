package com.n26.transaction.statistics.Utils;

import java.time.Instant;

public class TimeUtils {

	public static boolean isWithinLastMinute(Long timeStamp) {
		
		return timeStamp <= System.currentTimeMillis() && 
				(System.currentTimeMillis()-timeStamp)/1000 <= 60 ;
	}
	
	public static long timeBeforeSeconds(long time){
		return Instant.now().minusSeconds(time).toEpochMilli();
}

}
