package com.EmployeeManagement.ServiceImpl;

import com.EmployeeManagement.DTO.EmpResponseDTO;
import com.EmployeeManagement.Model.Employee;
import com.EmployeeManagement.Repository.EmployeeRepository;
import com.EmployeeManagement.Service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmpService {

    private  Logger log= LoggerFactory.getLogger("EmployeeService.class");

    @Autowired
    private  EmployeeRepository employeeRepository;

    public  ResponseEntity<EmpResponseDTO> findAllEmp() {
        EmpResponseDTO dat = new EmpResponseDTO();
        try {
            log.info("checking service impl");
            List<Employee> emp = employeeRepository.findAll();
            if (!emp.isEmpty()) {
                dat.setMsg("successfull");
                dat.setData(emp);
                return new ResponseEntity<>(dat, HttpStatus.OK);
            } else {
                dat.setMsg("Please check database");
                return new ResponseEntity<>(dat, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            dat.setMsg("Something got wrong");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<EmpResponseDTO> findById(long id) {
        EmpResponseDTO dat = new EmpResponseDTO();
        try {
            Optional<Employee> emp = employeeRepository.findById(id);
            if (emp.isPresent()) {
                dat.setMsg("successful");
                dat.setData(emp);
                return new ResponseEntity<>(dat, HttpStatus.OK);
            } else {
                dat.setMsg("Id "+id+" does not exist");
                return new ResponseEntity<>(dat, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            dat.setMsg("Something got wrong");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

   public ResponseEntity<EmpResponseDTO> findBySalary(){

       EmpResponseDTO dat = new EmpResponseDTO();
       try {
           log.info("checking service impl");
           int i=2500;
           List<Employee> emp1 = employeeRepository.findAll();
           if (!emp1.isEmpty()) {
               List<Employee> emp=emp1.stream().filter(e->e.getSalary()>i).collect(Collectors.toList());
               dat.setMsg("successfully got salary greater than "+i);
               dat.setData(emp);
               return new ResponseEntity<>(dat, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(dat, HttpStatus.NOT_FOUND);
           }
       } catch (Exception e) {
           dat.setMsg("Something got wrong");
           return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
       }
   }

    public ResponseEntity<EmpResponseDTO> createNewEmployee(Employee empNew) {
        EmpResponseDTO dat = new EmpResponseDTO();
        try {
            List<Employee> emp = employeeRepository.findByEmail(empNew.getEmailid());
            if (!emp.isEmpty()){
                dat.setMsg("Email id "+empNew.getEmailid()+" is already exist");
                dat.setData(emp);
                return new ResponseEntity<>(dat, HttpStatus.ALREADY_REPORTED);
            } else {

                dat.setMsg("Successfully created for "+empNew.getFirstName()+" Employee");
                empNew.setDate(LocalDateTime.now());
                dat.setData(empNew);
                employeeRepository.save(empNew);
                return new ResponseEntity<>(dat, HttpStatus.CREATED);

            }
        } catch (Exception e) {
            dat.setMsg("Something got wrong");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<EmpResponseDTO> findById_v1(long id, String firstName) {
        EmpResponseDTO dat = new EmpResponseDTO();
        try {
            Optional<Employee> emp = employeeRepository.findById(id);
            if (emp.isPresent()) {
                dat.setMsg("successful");
                dat.setData(emp);
                return new ResponseEntity<>(dat, HttpStatus.OK);
            } else {
                dat.setMsg("Id "+id+" does not exist");
                return new ResponseEntity<>(dat, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            dat.setMsg("Something got wrong");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
    
    public  ResponseEntity<EmpResponseDTO> findAllPagination(int pageno,int pagesize) {
        EmpResponseDTO dat = new EmpResponseDTO();
        log.info("checking service impl");
        Pageable pageable = PageRequest.of(pageno, pagesize);
        try {
            
            Page<Employee> emp = employeeRepository.findAll(pageable);
            if (!emp.isEmpty()) {
                dat.setMsg("successfull");
                dat.setData(emp);
                return new ResponseEntity<>(dat, HttpStatus.OK);
            } else {
                dat.setMsg("Please check database");
                return new ResponseEntity<>(dat, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            dat.setMsg("Something got wrong");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY); 
        }
    }
}
