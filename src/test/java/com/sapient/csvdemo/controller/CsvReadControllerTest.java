package com.sapient.csvdemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.csvdemo.model.Employee;
import com.sapient.csvdemo.service.CsvReadService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CsvReadController.class})
@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Writing test cases for CsvReadController class")
public class CsvReadControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CsvReadService csvReadService;

	@Autowired
	protected MockMvc mockMvc; 
	
	@Test
	public void testGetUpdatedEmployeeRecords() throws Exception {
		when(csvReadService.getEmployeesByPlace(anyString(), anyDouble())).thenReturn(getEmpList());
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/employee/place/" + "Bangalore" + "/salary/" + 10.0)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		
		TypeReference<List<Employee>> typeRef = new TypeReference<List<Employee>>() {};	
		List<Employee> empList = objectMapper.readValue(result, typeRef);
		assertNotNull(empList);
		assertTrue(empList.size() > 0);
		Employee emp = empList.get(0);
		assertEquals("John", emp.getEmployeeName());
		assertEquals("NY", emp.getPlace());
		assertEquals(100000.0, emp.getSalary());
				
	}

	private List<Employee> getEmpList() {
			List<Employee> empList = new ArrayList<>();
			Employee emp = new Employee();
			emp.setEmployeeID(125673);
			emp.setEmployeeName("John");
			emp.setPlace("NY");
			emp.setSalary(100000.0);
			empList.add(emp);
			return empList;
	}

}
