package com.love2code.springboot.cruddemo.dao;

import com.love2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAOJPAImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //JPQL
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee == null) {
            throw new RuntimeException("employee not found");
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {

        Employee dbEmployee = entityManager.merge(employee);
        //employee.setId(dbEmployee.getId());

    }

    @Override
    public void deleteById(int id) {

        Employee employee = entityManager.find(Employee.class, id);

        if (employee == null) {
            throw new RuntimeException("employee not found");
        }
        Query query = entityManager.createQuery("delete from Employee where id = :employeeId");
        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
