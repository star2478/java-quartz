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
	public void execute(JobExecutionContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String curTime = sdf.format(new Date());
		System.out.println("[" + curTime + "] job2 [" + context.getTrigger().toString() + "]");
//		try {
//			Thread.currentThread().sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("End job2[" + context.getTrigger().toString() + "]");
	}
}