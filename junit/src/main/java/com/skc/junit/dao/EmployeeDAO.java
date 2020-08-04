package com.skc.junit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skc.junit.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

}
