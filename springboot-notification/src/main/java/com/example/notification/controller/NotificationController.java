package com.example.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification.model.EmailModel;

@RestController
public class NotificationController {
	@Autowired
	JavaMailSender javaMailSender;

	@RequestMapping(value = "/notify/email", method = RequestMethod.POST)
	public String sendMail(@RequestBody EmailModel model) {

		return "Success";

	}

}
