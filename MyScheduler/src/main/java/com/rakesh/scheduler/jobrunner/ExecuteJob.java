package com.rakesh.scheduler.jobrunner;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ExecuteJob implements Job{
	private static final Logger logger = Logger.getLogger(ExecuteJob.class.getName());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
