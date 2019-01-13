package com.love2code.springboot.cruddemo.service;

import com.love2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);
}
