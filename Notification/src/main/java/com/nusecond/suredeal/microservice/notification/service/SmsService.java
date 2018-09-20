/**
 * 
 */
package com.nusecond.suredeal.microservice.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author krishna
 *
 */
@Service
public class SmsService implements MessageService {
	
	@Autowired
	SmsServiceProvider smsProvider;
	@Override
	public Boolean sendMessage(String to, String body) {
		// TODO Auto-generated method stub
		return smsProvider.sendMessage(to, body);
	}

}
