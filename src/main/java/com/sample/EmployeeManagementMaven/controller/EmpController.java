package com.sample.EmployeeManagementMaven.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.EmployeeManagementMaven.entity.Employee;
import com.sample.EmployeeManagementMaven.repo.EmpRepo;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class EmpController {
	@Autowired
	EmpRepo er;

	@PostMapping("insertEmp")
	Employee insertEmp(@RequestBody Employee emp) {
		return er.save(emp);
	}

	@GetMapping("getAllEmp")
	List<Employee> getAllEmp() {
		List<Employee> list = new ArrayList<>();

		list = er.findAll();
		return list;
	}

	@GetMapping("getById/{id}")
	Optional<Employee> getById(@PathVariable("id") int id) {
		return er.findById(id);
	}

	@GetMapping("getByName/{name}")
	List<Optional<Employee>> getByName(@PathVariable("name") String name) {
		return er.findByName(name);
	}

	@PostMapping("updateEmp/{id}")
	Employee updateStudent(@RequestBody Employee emp, @PathVariable("id") int sid) {
		Employee existingEmp = er.findById(sid)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + sid));

		existingEmp.setName(emp.getName());
		existingEmp.setDepartment(emp.getDepartment());
		existingEmp.setSalary(emp.getSalary());

		return er.save(existingEmp);
	}

	@PatchMapping("patchWork/{id}")
	Employee patchEmpInfo(@RequestBody Employee emp, @PathVariable("id") int id) {
		Employee existingEmp = er.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
		existingEmp.setDepartment(emp.getDepartment());
		return er.save(existingEmp);
	}

	@DeleteMapping("deleteById/{id}")
	String deleteemp(@PathVariable("id") int id) {
		er.deleteById(id);
		return "Employee details has been removed succesfully based on id " + id;
	}

}
