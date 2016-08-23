package com.pingan.quartz.testQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
//import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;

/**
 * 
 * @author heliuxing
 *
 */
public class JobImpl1 implements Job {
	private static int i = 0;
	public void execute(JobExecutionContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String curTime = sdf.format(new Date());
		JobDataMap dataMap = context.getMergedJobDataMap();//.getJobDetail().getJobDataMap();
        String value = dataMap.getString("key1");
        System.out.println("job impl running, value=" + value);
		System.out.println("[" + curTime + "] job1 count=" + (i++) + " [" + context.getTrigger().toString() + "]");
//		try {
//			Thread.currentThread().sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("End job1[" + context.getTrigger().toString() + "]");
	}
	class SubJobImpl1 {
		private void test() {
			System.out.println("sub job impl1");
		}
	}
}