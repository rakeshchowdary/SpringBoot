package com.rakesh.scheduler.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rakesh.scheduler.jobrunner.ExecuteJob;
import com.rakesh.scheduler.model.JobInfo;
import com.rakesh.scheduler.model.YAMLConfig;
import com.rakesh.scheduler.utils.CronExpressionGenerator;
import com.rakesh.scheduler.utils.StringToDateFormater;

@Service
@Component
public class WeeklyJobTriggerService {

	
	String startDateTime;
	String starMin;
	String starHours;
	String endDateTime;

	Date startDate;
	Date endDate;

	String startMin;
	String startHours;

	YAMLConfig config = new YAMLConfig();
	StringToDateFormater dateFormat = new StringToDateFormater();
	CronExpressionGenerator generator = new CronExpressionGenerator();

	private static final Logger logger = Logger.getLogger(WeeklyJobTriggerService.class);
	SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	public String scheduleWeekly(JobInfo jobInfo, String repeatDays) {
		logger.info("---------->Expression----->" + "0 m h ? * d *");
		if (jobInfo.getStartDate() != null && !jobInfo.getStartDate().isEmpty()) {
			logger.info("---------->Expression----->333333333");
			startDateTime=jobInfo.getStartDate();
			logger.info("---------->Expression----->000000000"+startDateTime);
			startDate = dateFormat.returnDate(startDateTime);
			logger.info("---------->Expression----->444444444");
			startMin = String.valueOf(dateFormat.returnMinutesOfDay(startDate));
			logger.info("---------->Expression----->555555");
			startHours = String.valueOf(dateFormat.returnHoursOfDay(startDate));
			logger.info("---------->Expression----->66666666");

		}
		if (jobInfo.getEndDate() != null && !jobInfo.getEndDate().isEmpty()) {
			logger.info("---------->ExpressionEndDate----->");
			endDateTime=jobInfo.getEndDate();
			endDate = dateFormat.returnDate(endDateTime);

		}
		try {
			logger.info("---------->ExpressionEndDate----->");
			Scheduler scheduler = schedulerFactory.getScheduler();
		//	JobDataMap dataMap = new JobDataMap();
			JobDetail job = JobBuilder.newJob(ExecuteJob.class).requestRecovery(true)
					.withIdentity("WeeklyJob", "WeeklyJobGroup").build();
			logger.info("---------->ExpressionEndDate----->");
			Trigger trigger = TriggerBuilder.newTrigger().forJob("WeeklyJob")
					.withIdentity("WeeklyTrigger", "WeeklyJobTriggerGroup").startAt(startDate).endAt(endDate)
					.withSchedule(CronScheduleBuilder.cronSchedule(generator
							.generateWeeklyExpression("0 m h ? * d *", startMin, startHours, repeatDays)))
					.build();
			logger.info("---------->ExpressionEndDate----->");
			scheduler.scheduleJob(job, trigger);
			scheduler.start();

		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return "Scheduled succesful";

	}

}
