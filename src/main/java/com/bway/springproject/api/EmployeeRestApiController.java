package com.bway.springproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.service.EmployeeService;

@RestController
public class EmployeeRestApiController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/api/emp/list")
	public List<Employee> getAllEmployee() {

		return service.getAllEmployees();
	}

	@PostMapping("/api/emp/add") // @RequestBody used to convert data from api to employee
	public String addEmployee(@RequestBody Employee employee) {

		service.addEmployee(employee);
		return "successfully added";
	}

	@DeleteMapping("/api/emp/delete/{id}")
	public String deleteEmp(@PathVariable Long id) {
		service.deleteEmployee(id);
		return "delete successfully.";
	}

	@PutMapping("/api/emp/update")
	public String updateEmployee(@RequestBody Employee employee) {
		service.updateEmployee(employee);
		return "updated successfully.";
	}

	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable Long id) {

		return service.getEmployeeById(id);
	}

	@GetMapping("/api/emp/json2obj")
	public String jsonToObjectMapping() {

		RestTemplate temp = new RestTemplate();
		Employee emp = temp.getForObject("http://localhost:8080/api/emp/1", Employee.class);
		return "First Name: " + emp.getFirstname();
	}

	@GetMapping("/api/emp/jsonArray2objectArray")
	public String jsonArrayToObjectArrayMapping() {
		RestTemplate temp = new RestTemplate();
		Employee[] employees = temp.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
		return "first name "+employees[1].getFirstname();
	}
	
	@GetMapping("/api/emp/products")
	public String jsonProductMapping() {
		RestTemplate temp = new RestTemplate();
		Product[] product = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		return "Title "+product[0].getTitle();
	}
}
