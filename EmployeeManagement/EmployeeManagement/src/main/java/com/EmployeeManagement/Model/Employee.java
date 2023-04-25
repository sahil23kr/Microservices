package com.EmployeeManagement.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

   @Column(name="FirstName")
   private String FirstName;
    @Column(name="LastName")
    private String lastName;
    //@Column(name="Emailid")
    @Email
    private String Emailid;
    @Column(name="DateTime")
    private LocalDateTime dateTime;
    @Column(name="Salary")
    private long salary;




    public Employee(List<Employee> emp) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailid() {
        return Emailid;
    }

    public void setEmailid(String emailid) {
        Emailid = emailid;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Employee(long id, String firstName, String lastName, String emailid, LocalDateTime dateTime) {
        this.id = id;
        FirstName = firstName;
        this.lastName = lastName;
        Emailid = emailid;
        this.dateTime = dateTime;
    }

    public Employee()
    {
        super();
    }
}
