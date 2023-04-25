package com.EmployeeManagement;

import com.EmployeeManagement.Model.Employee;
import com.EmployeeManagement.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeManagementApplicationTests {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void create()
	{
		Employee emp1=new Employee();
		emp1.setFirstName("rahul");
		emp1.setLastName("kumar");
		emp1.setEmailid("rahul@gmail.com");
		employeeRepository.save(emp1);
	}

	@Test
	public void findAll()
	{
		employeeRepository.findAll();
	}

}
