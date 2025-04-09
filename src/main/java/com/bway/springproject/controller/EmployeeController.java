package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employeeform")
	public String getEmployeeForm(HttpSession session) {

		if (session.getAttribute("activeUser") == null) {
			return "redirect:/";
		} else {
			return "employeeform";
		}
	}

	@PostMapping("/addEmployee")
	public String saveEmployee(@ModelAttribute Employee employee, HttpSession session) {

		employeeService.addEmployee(employee);

		return "redirect:/employee/list";
	}

	@GetMapping("/list")
	public String getAllEmployee(Model model, HttpSession session) {

		if (session.getAttribute("activeUser") == null) {
			return "redirect:/";
		}

		model.addAttribute("elist", employeeService.getAllEmployees());

		return "employeelist";
	}

	@GetMapping("/home")
	public String getHome(HttpSession session) {

		if (session.getAttribute("activeUser") == null) {
			return "redirect:/";
		}

		return "home";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam Long id, HttpSession session) {

		if (session.getAttribute("activeUser") == null) {
			return "redirect:/";
		}

		employeeService.deleteEmployee(id);
		return "redirect:/employee/list";
	}

	@GetMapping("/edit")
	public String editEmployee(@RequestParam Long id, Model model, HttpSession session) {

		if (session.getAttribute("activeUser") == null) {
			return "redirect:/";
		}

		model.addAttribute("empModel", employeeService.getEmployeeById(id));
		return "employeeEditForm";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee employee, HttpSession session) {

		if (session.getAttribute("activeUser") == null) {
			return "redirect:/";
		}

		employeeService.addEmployee(employee);
		return "redirect:/employee/list";
	}

}
