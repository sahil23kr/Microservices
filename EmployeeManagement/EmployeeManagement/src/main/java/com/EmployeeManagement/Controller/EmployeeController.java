package com.EmployeeManagement.Controller;

import com.EmployeeManagement.DTO.EmpResponseDTO;
import com.EmployeeManagement.Model.Employee;
import com.EmployeeManagement.Repository.EmployeeRepository;
import com.EmployeeManagement.Service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/empmng")
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;


@Autowired
private  EmpService empService;
    private static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    /*@PostMapping("/createNewEmployee")
    public Employee createNewEmployee(@RequestBody Employee emp) {
        log.info("<<<<<checking Employee Create method>>>>>");
        log.info("body request=" + emp.getFirstName() + " " + emp.getLastName() + " " + emp.getEmailid());

        EmpResponseDTO res=new EmpResponseDTO();
        //res.setLocalDateTime(LocalDateTime.now());
        emp.setDate(LocalDateTime.now());
        return employeeRepository.save(emp);
    }*/

    @PostMapping("/createNewEmployee")
    public ResponseEntity<EmpResponseDTO> createNewEmployee(@RequestBody Employee emp) {
        log.info("<<<<<checking Employee Create method>>>>>");
        log.info("body request=" + emp.getFirstName() + " " + emp.getLastName() + " " + emp.getEmailid());
        return empService.createNewEmployee(emp);
    }
        @GetMapping("/findAll")
    public List<Employee> findAll() {
        log.info("<<<<<<checking Employee findAll method>>>>>");
        return employeeRepository.findAll();
    }

   /* @GetMapping("/findById/{id}")
    public ResponseEntity<Employee> findById(@PathVariable(value = "id") long id) {

        EmpException exp=new EmpException("This "+ id+"does not exist");
        Optional<Employee> emp1 = employeeRepository.findById(id);//.orElseThrow(()->new EmpException("not found"));
        if (emp1.isEmpty()) {
            //String msg= exception.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(HttpStatus.FOUND);
    }*/

    @GetMapping("/findAllEmp")
    public ResponseEntity<EmpResponseDTO> findAllEmp()
    {
        log.info("checking controller method");
        return empService.findAllEmp();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<EmpResponseDTO> findById(@PathVariable(value = "id") long id) {
        log.info("checking controller method ");
        return empService.findById(id);
    }

    @GetMapping("/findBySalary")

        public ResponseEntity<EmpResponseDTO> findBySalary() {
        log.info("checking controller method ");
        return empService.findBySalary();
    }
    @GetMapping("/findByIdv1/{id}")
    public ResponseEntity<EmpResponseDTO> findById_v1(@PathVariable(value = "id") long id,@RequestParam("firstName") String firstName) {
        log.info("checking controller method");
        return empService.findById_v1(id,firstName);
    }
    
    @GetMapping("findAllPagination")
    public ResponseEntity<EmpResponseDTO> findAllPagination(@RequestParam int pageno,@RequestParam int size)
    {
        log.info("checking controller method");
        return empService.findAllPagination(pageno,size);
    }
    

}
