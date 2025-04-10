package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@GetMapping("/contact")
	public String getEmailForm() {

		return "emailform";
	}

	@PostMapping("/contact")
	public String postEmail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String message) {
		
		sendEmail(toEmail, subject, message);
		
		return "emailform";
	}

	void sendEmail(String toEmail, String subject, String message) {

	    SimpleMailMessage msg = new SimpleMailMessage();
	    msg.setTo(toEmail);
	    msg.setSubject(subject);
	    msg.setText(message);

	    javaMailSender.send(msg);

	}


}

