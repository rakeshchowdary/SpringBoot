package com.h2.quartz.notification;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationJob implements Job{
	@Autowired
    private NotificationService notifierService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		notifierService.sendNotifications(context
                .getJobDetail()
                .getKey()
                .toString());
		
	}
	

}
