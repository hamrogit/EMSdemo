package com.bway.springproject.model;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_table")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String gender;
	private int age;
	private int salary;
	private String companyname;
	private String post;
	private String department;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joindate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_address_id")
	private Address address;

	@ElementCollection
	@CollectionTable
	private Set<String> projectname;
}
