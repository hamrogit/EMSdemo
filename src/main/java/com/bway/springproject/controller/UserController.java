package com.bway.springproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService service;

	@GetMapping("/login")
	public String getLogin() {

		return "loginform";
	}

	@PostMapping("/home")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session) {

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User u = service.userLogin(user.getUserName(), user.getPassword());
		if (u != null) {
			// model.addAttribute("firstname", u.getFirstName());
			// model.addAttribute("lastname", u.getLastName());
			session.setAttribute("activeUser", u);
			session.setMaxInactiveInterval(60);
			return "home";
		} else {
			model.addAttribute("message", "user doesn't exist!");
			return "redirect:/";
		}
	}

	@GetMapping("/signup")
	public String getSignup() {

		return "signupform";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user) {

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		if (user != null) {
			service.userSignup(user);
			return "redirect:/";
		} else {
			return "redirect:/user/signup";
		}

	}
}
