/**
 * 
 */
package com.nusecond.suredeal.microservice.notification.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author krishna
 *
 */
@Service
public class SmsServiceProvider  {

	static Logger log = Logger.getLogger(SmsServiceProvider.class.getName());
	
	@Autowired
	SolutionInfiServericeProvider solutionInfo;
	
	public Boolean sendMessage(String to,String message)
	{
		log.info("fff");
		return solutionInfo.sendMessage(to, message);
	}
	
	
}
