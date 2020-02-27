package com.sapient.csvdemo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sapient.csvdemo.model.Employee;

public class CsvReadServiceTest {

	@Test
	public void testGetEmployeesByPlace() {
		
		CsvReadService mockedCsvReadService = Mockito.spy(CsvReadService.class);
		List<Employee> empList = getEmployeeList();
		mockedCsvReadService.saveEmployeeList(empList);
		List<Employee> resultList = mockedCsvReadService.getEmployeesByPlace("NY", 10.0);
		assertNotNull(resultList);
		
	}

	private List<Employee> getEmployeeList() {
		List<Employee> empList = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setEmployeeID(125673);
		emp1.setEmployeeName("John");
		emp1.setPlace("NY");
		emp1.setSalary(100000.0);
		Employee emp2 = new Employee();
		emp2.setEmployeeID(345532);
		emp2.setEmployeeName("Guenther");
		emp2.setPlace("Germany");
		emp2.setSalary(245000.0);
		Employee emp3 = new Employee();
		emp3.setEmployeeID(444344);
		emp3.setEmployeeName("Alex");
		emp3.setPlace("NY");
		emp3.setSalary(328480.0);
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		return empList;
	}
}
