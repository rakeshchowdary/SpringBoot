package com.h2.quartz.notification;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	private static final Logger LOGGER = Logger.getLogger(NotificationService.class);

    public void sendNotifications(String jobDetails) {
        LOGGER.info("[{}] Sending notifications...");
    }
}
