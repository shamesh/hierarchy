package com.shamesh.hierarchy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shamesh.hierarchy.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
	public List<Employee> findByManagerIsNull();
	
	public List<Employee> findByName(String name);

}
