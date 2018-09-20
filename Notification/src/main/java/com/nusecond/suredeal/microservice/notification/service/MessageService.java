/**
 * 
 */
package com.nusecond.suredeal.microservice.notification.service;

/**
 * @author krishna
 *
 */
public interface MessageService {

	
   Boolean sendMessage(String to,String body);

}
