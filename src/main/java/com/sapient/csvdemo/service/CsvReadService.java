package com.sapient.csvdemo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sapient.csvdemo.model.Employee;

@Service
@CacheConfig(cacheNames = { "employees" })
public class CsvReadService {

	List<Employee> employees;

	public void saveEmployeeList(List<Employee> employees) {
		this.employees = employees;
	}

	@Cacheable
	private List<Employee> getEmployeeList() {
		return this.employees;
	}

	@CachePut(key="#employeeID")
	private Employee updateEmployee(Employee emp, double salary) {
		emp.setSalary(salary);
		return emp;
	}

	public List<Employee> getEmployeesByPlace(String place, double percentage) {
		List<Employee> empList = getEmployeeList();

		Stream<Employee> empStream = empList.stream();

		empStream = empStream.filter(e -> e.getPlace().equals(place));

		List<Employee> filteredEmpList = empStream.collect(Collectors.toList());

		for (Employee emp : filteredEmpList) {
			double salary = emp.getSalary();
			salary += (salary * percentage) / 100;
			updateEmployee(emp, salary);
		}

		return this.employees;

	}

}
