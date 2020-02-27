package com.sapient.csvdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.csvdemo.model.Employee;
import com.sapient.csvdemo.service.CsvReadService;

@RestController
public class CsvReadController {

	@Autowired
	private CsvReadService csvReadService;

	@PutMapping(value = "/employee/place/{place}/salary/{percentage}")
	public List<Employee> getUpdatedEmployeeRecords(@PathVariable("place") String place,
			@PathVariable("percentage") double percentage) {
		List<Employee> employeeList = csvReadService.getEmployeesByPlace(place, percentage);
		return employeeList;

	}

}
