package com.fibanez.spring4xml.services;


import java.util.ArrayList;
import java.util.List;

import com.fibanez.spring4xml.dao.EmployeeDAO;
import com.fibanez.spring4xml.model.Employee;
import com.fibanez.spring4xml.rest.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public EmployeeDTO addEmployee(EmployeeDTO emp) {
        System.out.println("adding data in service"+emp.getDesignation());
        Employee employee = new Employee();
        employee.setDesignation(emp.getDesignation());
        employee.setEmail(emp.getEmail());
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setProjectName(emp.getProjectName());
        employee = employeeDAO.addEmployee(employee);
        emp.setEmpId(employee.getEmployeeId());
        return emp;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO emp) {

        Employee employee = new Employee();
        employee.setEmployeeId(emp.getEmpId());;
        employee.setDesignation(emp.getDesignation());
        employee.setEmail(emp.getEmail());
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setProjectName(emp.getProjectName());
        employee = employeeDAO.updateEmployee(employee);
        return emp;
    }

    public void deleteEmployee(long empId) {
        Employee employee =employeeDAO.getEmployee(empId);
        employeeDAO.deleteEmployee(employee);
    }

    public EmployeeDTO getEmployee(long empId) {

        Employee emp =employeeDAO.getEmployee(empId);
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setDesignation(emp.getDesignation());
        empDTO.setEmail(emp.getEmail());
        empDTO.setFirstName(emp.getFirstName());
        empDTO.setLastName(emp.getLastName());
        empDTO.setProjectName(emp.getProjectName());
        empDTO.setEmpId(emp.getEmployeeId());
        return empDTO;
    }

    public List<EmployeeDTO> getAllEmployees() {
        // Here you need to transform entities to DTO. If you use entities this is not required.
        List<Employee> list = employeeDAO.getAllEmployees();
        List<EmployeeDTO> dtoList = new ArrayList<EmployeeDTO>();
        for(Employee emp:list){
            EmployeeDTO empDTO = new EmployeeDTO();
            empDTO.setDesignation(emp.getDesignation());
            empDTO.setEmail(emp.getEmail());
            empDTO.setFirstName(emp.getFirstName());
            empDTO.setLastName(emp.getLastName());
            empDTO.setProjectName(emp.getProjectName());
            empDTO.setEmpId(emp.getEmployeeId());
            dtoList.add(empDTO);
        }

        return dtoList ;
    }

}