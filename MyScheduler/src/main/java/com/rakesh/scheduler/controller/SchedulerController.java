package com.rakesh.scheduler.controller;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.scheduler.model.JobInfo;
import com.rakesh.scheduler.service.SchedulerServiceImpl;

@RestController
public class SchedulerController {
	@Autowired
	SchedulerServiceImpl schedulerServiceImpl;

	private static final Logger log = Logger.getLogger(SchedulerController.class);

	@PostMapping(value = "/startschedule")
	public String schedule(@RequestBody JobInfo jobInfo) throws SchedulerException {
		log.info("-----Scheduler Started-------");
		if (jobInfo != null && jobInfo.getRepeat() != null && !jobInfo.getRepeat().isEmpty()) {
			log.info("----------inside controller if-------");
			return schedulerServiceImpl.scheduleJob(jobInfo, jobInfo.getRepeat());
		}
		return "Job is Empty";

	}

	@GetMapping("/testssl")
	public String testHttps() {
		System.out.println("Success");
		return "Success";
	}
}
