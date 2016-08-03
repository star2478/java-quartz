package com.pingan.quartz.testQuartz;

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

/**
 * 
 * @author heliuxing
 *
 */
public class TestQuartz {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		
		/**
		 * 创建调度器
		 */
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		// 启动调度器线程，并且注销其上的所有触发器对象，如果需要保留其上的触发器，应该调用standby()方法
		scheduler.start();

		/**
		 * 使用JobBuilder构建任务
		 *
		 * 这个任务通过“一个实现了Job接口的类”指定任务的执行实现类
		 * withIdentity(name, group)方法指定类任务的“名称”和“组名”，可以理解为key，用于标识这个任务
		 * build()方法执行构建，构建出的对象为JobDetail
		 */
		JobDetail jobDetail1 = JobBuilder.newJob(JobImpl1.class).withIdentity("myJob1", "jobGroup").build();
		JobDetail jobDetail2 = JobBuilder.newJob(JobImpl2.class).withIdentity("myJob2", "jobGroup").build();
		
		/**
		 * 使用TriggerBuilder构建触发器
		 *
		 * withIdentity(name, group)方法指定类触发器的“名称”和“组名”，用于标识这个触发器
		 * startAt()方法表示这个触发器在何时开始触发任务，不过，直到触发器（trigger）放进调度器（scheduler）才真正起效
		 * withSchedule(schedBuilder)方法指定这个触发器的调度规则，例子中的为“每2秒触发一次，永不停止”
		 */
		Date triggerDate = new Date();
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();//.repeatSecondlyForTotalCount(10);
		TriggerBuilder<Trigger> triggerBuilder1 = TriggerBuilder.newTrigger().withIdentity("myTrigger1", "triggerGroup");
		Trigger trigger1 = triggerBuilder1.startAt(triggerDate).withSchedule(schedBuilder).build();
		TriggerBuilder<Trigger> triggerBuilder2 = TriggerBuilder.newTrigger().withIdentity("myTrigger2", "triggerGroup");
		Trigger trigger2 = triggerBuilder2.startAt(triggerDate).withSchedule(schedBuilder).build();

		// 调度器真正开始调度任务
		scheduler.scheduleJob(jobDetail1, trigger1);
		scheduler.scheduleJob(jobDetail2, trigger2);
		
		TimeUnit.SECONDS.sleep(5);
		// 关闭调度器线程。如果没有调用shutdown()，且设置了触发器永久运行，则会在打印完finish后还会一直运行下去
//		scheduler.shutdown();
		System.out.println("finish!");
	}
}
