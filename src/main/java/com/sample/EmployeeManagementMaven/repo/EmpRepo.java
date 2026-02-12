package com.sample.EmployeeManagementMaven.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.EmployeeManagementMaven.entity.Employee;
@Repository	
public interface EmpRepo extends JpaRepository<Employee,Integer> {

	List<Optional<Employee>> findByName(String name);
	
	

}
