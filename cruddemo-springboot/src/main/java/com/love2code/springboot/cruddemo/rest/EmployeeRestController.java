package com.love2code.springboot.cruddemo.rest;


import com.love2code.springboot.cruddemo.entity.Employee;
import com.love2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
// "/api" is base mapping
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    //autowired not required when we have only 1 argument
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {

        return employeeService.findAll();
    }


    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeBasedOnId(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("employee not found");
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        employeeService.save(employee);
        return employee;

    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("employee doesnot exist");
        }
        employeeService.deleteById(employeeId);
        return "deleted employee with id" + employee.getId();

    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        employeeService.save(employee);

        return employee;
    }
}
