package com.love2code.springboot.cruddemo.dao;

import com.love2code.springboot.cruddemo.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Override
    public Employee findById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Employee theEmployee = session.get(Employee.class, id);

        return theEmployee;

    }

    @Override
    public void save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);


    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query theQuery = session.createQuery("delete from Employee where id = :employeeId");
        theQuery.setParameter("employeeId", id);

        theQuery.executeUpdate();


    }

    @Autowired
    //entity manager is automatically created by spring boot, we can simply inject it here
    //using constructor injection
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    //@Transactional - removed for now , will be handled by service later
    //transaction management - automatically done by spring
    public List<Employee> findAll() {

        //get current hibernate session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<Employee> query = session.createQuery("from Employee ", Employee.class);

        //not using Criteria

        //execute query  and get result list
        List<Employee> employees = query.getResultList();

        return employees;


    }
}
