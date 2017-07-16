package com.shamesh.hierarchy.dataloader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shamesh.hierarchy.entity.Employee;
import com.shamesh.hierarchy.repository.EmployeeRepository;

@Component
public class LoadData {

	@Autowired
	private EmployeeRepository empRepo;
	private static Logger logger = LoggerFactory.getLogger(LoadData.class);

	public void loadData() {
		Map<Integer, Integer> data = new LinkedHashMap<>();
		try {
			Reader in = new FileReader("./employee.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				String name = record.get(0);
				Integer id = Integer.parseInt(record.get(1));
				Integer managerId = null;
				if (!record.get(2).isEmpty()) {
					managerId = Integer.parseInt(record.get(2));
				}
				data.put(id, managerId);
				empRepo.save(new Employee(id, name));
				logger.debug("New Employee added:{} ", name);
			}
		} catch (NumberFormatException | IOException e) {
			logger.error("Error in loading employee.csv ", e);
		}

		for (Employee emp : empRepo.findAll()) {
			if (data.containsKey((data.get(emp.getId())))) {
				emp.setManager(data.get(emp.getId()));
				empRepo.save(emp);
			} else
				logger.error("Manager not found for {} Entered Manager Id: {}", emp.getName(), data.get(emp.getId()));

		}

	}
}
