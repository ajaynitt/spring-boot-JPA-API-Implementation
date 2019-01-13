package com.love2code.springboot.cruddemo.service;

import com.love2code.springboot.cruddemo.dao.EmployeeDAO;
import com.love2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    //employeeDAOJPAImpl - name of iml calss starting with  lowercase
    public EmployeeServiceImpl(/*@Qualifier("employeeDAOJPAImpl")*/ EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        return employeeDAO.findById(id);

    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDAO.save(employee);

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);

    }
}
