package com.shamesh.hierarchy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shamesh.hierarchy.entity.Employee;
import com.shamesh.hierarchy.repository.EmployeeRepository;

@RestController
public class EmployeeHandler {

	Logger logger = LoggerFactory.getLogger(EmployeeHandler.class);

	@Autowired
	private EmployeeRepository empRepo;

	@RequestMapping("/employees")
	public String employees(@RequestParam(value = "name", required = false) String name) {

		if (name == null)
			return "<pre>" + buildChart(empRepo.findByManagerIsNull(), 0) + "</pre>";
		else
			return "<pre>" + buildChart(empRepo.findByName(name), 0) + "</pre>";
	}

	@RequestMapping("/addemployees")
	public String addEmployees(@RequestParam String name, @RequestParam Integer id, @RequestParam Integer managerid) {

		Employee manager = empRepo.findOne(managerid);
		Employee emp;
		String output = "";
		if (manager != null) {
			emp = new Employee(id, name);
			emp.setManager(managerid);
			empRepo.save(emp);
			manager.addSubOrdinates(emp);
			empRepo.save(manager);
			output = organisation();
		} else {
			output = "ManagerId " + managerid + " Not Found. Employee " + name + " Not Added / Updated";
			logger.error("ManagerId {} Not Found. Employee {} Not Added / Updated", managerid, name);
		}
		return output;
	}

	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	@RequestMapping("/organisation")
	public String organisation() {

		return "<pre>" + buildChart(empRepo.findByManagerIsNull(), 0) + "</pre>";
	}

	private String buildChart(Iterable<Employee> employees, int level) {

		StringBuilder output = new StringBuilder();
		for (Employee emp : employees) {
			for (int i = 0; i < level; i++) {
				output.append("\t");
			}
			output.append(emp.getName() + "\n");

			if (!emp.getSubOrdinates().isEmpty())
				output.append(buildChart(emp.getSubOrdinates(), level + 1));
		}

		return output.toString();
	}
}
