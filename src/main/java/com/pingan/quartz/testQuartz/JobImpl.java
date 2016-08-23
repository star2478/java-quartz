package com.pingan.quartz.testQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
//import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;

/**
 * @author hlx
 */
public class JobImpl implements Job {
  public void execute(JobExecutionContext context) {
    System.out.println("job impl running");
  }
}