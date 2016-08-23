package com.pingan.quartz.testQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import com.pingan.quartz.testQuartz.JobImpl1.SubJobImpl1;

/**
 * 从当前时间开始，每隔2s执行一次JobImpl#execute()
 * @author hlx
 */
public class TestQuartz {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
      // 创建调度器
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler = schedulerFactory.getScheduler();

      // 创建任务
      JobDetail jobDetail = JobBuilder.newJob(JobImpl.class).withIdentity("myJob", "jobGroup").build();

      // 创建触发器
      // withIntervalInSeconds(2)表示每隔2s执行任务
      Date triggerDate = new Date();
      SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
      TriggerBuilder<Trigger> triggerBuilder  = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup");
      Trigger trigger = triggerBuilder.startAt(triggerDate).withSchedule(schedBuilder).build();

      // 将任务及其触发器放入调度器
      scheduler.scheduleJob(jobDetail, trigger);
      // 调度器开始调度任务
      scheduler.start();
   }
}
