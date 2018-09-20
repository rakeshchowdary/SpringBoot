package com.rakesh.scheduler.service;

import org.quartz.SchedulerException;

import com.rakesh.scheduler.model.JobInfo;

public interface SchedulerServiceInterface {
	
	public String weeklyJobTrigger();
	public String scheduleJob(JobInfo jobInfo,String repeatDays) throws SchedulerException;

}
