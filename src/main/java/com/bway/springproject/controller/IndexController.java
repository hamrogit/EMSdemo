package com.bway.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(HttpSession session) {
		session.invalidate(); // session kill
		return "loginform";
	}
}
