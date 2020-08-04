package com.skc.junit.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeDAOTest {

	@Autowired
	EmployeeDAO empployee;
	
	@Test
	void test() {
		System.out.println(empployee.getOne(1).toString()+"My -------------------------------");
	}

}
