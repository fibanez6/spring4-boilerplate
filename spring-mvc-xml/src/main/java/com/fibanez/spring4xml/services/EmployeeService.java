package com.fibanez.spring4xml.services;

import com.fibanez.spring4xml.rest.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO addEmployee(EmployeeDTO emp);

    EmployeeDTO updateEmployee(EmployeeDTO emp);

    void deleteEmployee(long empId);

    EmployeeDTO getEmployee(long empId);

    List<EmployeeDTO> getAllEmployees();
}