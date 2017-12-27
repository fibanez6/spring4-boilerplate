package com.fibanez.spring4xml.rest.controller;

import com.fibanez.spring4xml.rest.dto.EmployeeDTO;
import com.fibanez.spring4xml.rest.dto.StatusDTO;
import com.fibanez.spring4xml.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //empId is Path Variable and url is /rest/employee/id
    @GetMapping(value="/{empId}",produces={MediaType.APPLICATION_JSON_VALUE})
    public EmployeeDTO getEmployee(@PathVariable("empId") long empId){
        System.out.println("calling get Employee method");
        return employeeService.getEmployee(empId);

    }

    @RequestMapping(value="/add",method= RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO emp){

        System.out.println("calling add employee method"+emp.getEmail());
        emp = employeeService.addEmployee(emp);
        return emp;
    }

    @RequestMapping(value="/update",method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO emp){

        if(emp.getEmpId()>0){
            emp = employeeService.addEmployee(emp);
        }else{
            //throw error here
        }
        return emp;
    }
    // for Get Requests, you can use short form as @GetMapping
    @GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
    public List<EmployeeDTO> getAll(){
        return employeeService.getAllEmployees();

    }

    @GetMapping(value="/delete/{empId}",produces={MediaType.APPLICATION_JSON_VALUE})
    public StatusDTO deleteEmployee(@PathVariable("empId") long empId){

        employeeService.deleteEmployee(empId);
        StatusDTO status = new StatusDTO();
        status.setMessage("Employee Deleted Successfully");
        status.setStatus(200);
        return status;
    }

}
