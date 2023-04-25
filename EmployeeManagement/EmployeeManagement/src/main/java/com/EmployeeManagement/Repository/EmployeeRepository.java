package com.EmployeeManagement.Repository;

import com.EmployeeManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long > {
    @Query(value = "select * from employee where emailid = :emailid",nativeQuery = true)
    List<Employee> findByEmail(@Param("emailid") String emailid);
}
