package com.shamesh.hierarchy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeHierarchyApplication {
	private static Logger logger = LoggerFactory.getLogger(EmployeeHierarchyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeHierarchyApplication.class, args);

		logger.debug("RUN Successfully");

	}

}
