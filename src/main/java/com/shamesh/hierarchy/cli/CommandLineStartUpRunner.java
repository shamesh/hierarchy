package com.shamesh.hierarchy.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shamesh.hierarchy.dataloader.LoadData;
import com.shamesh.hierarchy.repository.EmployeeRepository;

@Component
public class CommandLineStartUpRunner implements CommandLineRunner {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	LoadData dataLoder;

	@Override
	public void run(String... arg0) throws Exception {

		dataLoder.loadData();

	}

}
