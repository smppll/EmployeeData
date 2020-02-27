package com.sapient.csvdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("EmployeeID")
	private int employeeID;

	@JsonProperty("EmployeeName")
	private String employeeName;

	@JsonProperty("Place")
	private String place;

	@JsonProperty("Salary")
	private double salary;

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", place=" + place
				+ ", salary=" + salary + "]";
	}

}
