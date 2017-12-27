package com.fibanez.spring4xml.dao;

import com.fibanez.spring4xml.model.Employee;

import java.util.List;

public interface EmployeeDAO {
   
    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee getEmployee(long empId);

    List<Employee> getAllEmployees();
}