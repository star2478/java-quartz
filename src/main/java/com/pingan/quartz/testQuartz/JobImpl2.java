package com.pingan.quartz.testQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
//import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;

/**
 * 
 * @author heliuxing
 *
 */
public class JobImpl2 implements Job {
	private static int i = 0;
	public void execute(JobExecutionContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String curTime = sdf.format(new Date());
		int j = i;
		System.out.println("(" + curTime + ") job2 count=" + (i++) + " [" + context.getTrigger().toString() + "]");
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("job2 End count=" + j);
	}
}