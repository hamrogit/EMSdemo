package com.bway.springproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Employee;
import com.bway.springproject.repository.EmployeeRepository;
import com.bway.springproject.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {

		employeeRepository.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = employeeRepository.findAll();
		return list;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Employee e = employeeRepository.getById(id);
		return e;
	}

}
