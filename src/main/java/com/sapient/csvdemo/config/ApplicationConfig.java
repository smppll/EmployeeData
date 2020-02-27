package com.sapient.csvdemo.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sapient.csvdemo.model.Employee;
import com.sapient.csvdemo.service.CsvReadService;

@Configuration
public class ApplicationConfig {

	@Autowired
	private CsvReadService csvReadService;

	private static Logger logger = LogManager.getLogger(ApplicationConfig.class);

	private final String FILE_NAME = "Employee.csv";

	@Bean
	public void readEmployeeRecord() {

		ClassLoader classLoader = getClass().getClassLoader();
		File csvFile = new File(classLoader.getResource(FILE_NAME).getFile());

		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();

		List<Employee> empList = new ArrayList<>();

		try {
			MappingIterator<Employee> mi = csvMapper.readerFor(Employee.class).with(schema).readValues(csvFile);
			while (mi.hasNext()) {
				Employee emp = mi.next();
				empList.add(emp);
				System.out.println(emp);
			}
			csvReadService.saveEmployeeList(empList);
		} catch (IOException e) {
			logger.error("Error occurred while reading from file = " + csvFile, e);
		}

	}

}
