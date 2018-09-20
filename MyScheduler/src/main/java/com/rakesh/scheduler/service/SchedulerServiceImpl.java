package com.rakesh.scheduler.service;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.scheduler.model.JobInfo;

@Service
public class SchedulerServiceImpl implements SchedulerServiceInterface {
	private static final Logger logger = Logger.getLogger(SchedulerServiceImpl.class.getName());
	@Autowired
	WeeklyJobTriggerService weeklyJobTrigger;
	SchedulerFactory schedFact = new StdSchedulerFactory();

	String[] repeat;
	String repeatDays;

	@Override
	public String scheduleJob(JobInfo jobInfo, String repeatDays) throws SchedulerException {
		try {
			if (jobInfo != null) {
				logger.info("----------inside service Impl if-------");
				repeat = jobInfo.getRepeat().split("\\|");
				if (repeat[0].equalsIgnoreCase(("Weekly"))) {
					logger.info("----------inside service Impl if-------2222222");
					repeatDays = repeat[1].toUpperCase();
					logger.info("----------inside service Impl if-------333333");
					return weeklyJobTrigger.scheduleWeekly(jobInfo, repeatDays);

				}

			}

			// Scheduler sched = schedFact.getScheduler();
			// JobDetail job = JobBuilder.newJob(JobImpl.class).withIdentity("SampleJob",
			// "WeeklyJob").storeDurably(true)
			// .build();
			// Trigger trigger = TriggerBuilder.newTrigger()
			// .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(5000).repeatForever())
			// .withIdentity("Simple Trigger", "WeeklyTrigger").build();
			//
			// sched.scheduleJob(job, trigger);
			// sched.start();
			// return "Success";

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "Job Not Found";
	}

	@Override
	public String weeklyJobTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
