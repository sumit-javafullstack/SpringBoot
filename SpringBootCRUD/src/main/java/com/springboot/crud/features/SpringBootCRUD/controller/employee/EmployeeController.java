package com.springboot.crud.features.SpringBootCRUD.controller.employee;

import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import com.springboot.crud.features.SpringBootCRUD.repository.employee.EmployeeRepository;
import com.springboot.crud.features.SpringBootCRUD.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
  private final EmployeeService employeeService;

  private final EmployeeRepository employeeRepository;

  public EmployeeController(EmployeeService employeService,EmployeeRepository employeeRepository) {
    this.employeeService = employeService;
    this.employeeRepository = employeeRepository;
  }

  @PostMapping("/save-employee")
  public ResponseEntity<?> saveEmployee(@RequestBody @Valid Employee employee) {
    return ResponseEntity.ok(employeeService.saveEmployee(employee));
  }

  @GetMapping("/get-employee")
  public ResponseEntity<List<Employee>> getEmployee() {
    LocalTime startTimeInSecond = LocalTime.now();
    log.info("startTimeInSecond " + startTimeInSecond);
    ResponseEntity<List<Employee>> listResponseEntity =
        new ResponseEntity<>(employeeService.getEmployee(), HttpStatus.OK);

    LocalTime endTimeInSecond = LocalTime.now();
    log.info("endTimeInSecond " + endTimeInSecond);
    return listResponseEntity;
  }

  @GetMapping("/get-employee-pageable")
  public List<Employee> getEmployeePageable() {

    int pageSize = 10000;
    List<Employee> allEntities = new ArrayList<>();
    int page = 0;
    Page<Employee> entityPage;

    LocalTime startTimeInSecond = LocalTime.now();
    log.info("startTimeInSecond with paging" + startTimeInSecond);
    do {
      Pageable pageable = PageRequest.of(page, pageSize);
      entityPage = employeeService.getEmployeeUsingPage(pageable);
      allEntities.addAll(entityPage.getContent());
      page++;
    } while (entityPage.hasNext());
    LocalTime endTimeInSecond = LocalTime.now();
    log.info("endTimeInSecond with paging" + endTimeInSecond);
    return allEntities;
  }

  @PutMapping("/update-employee")
  public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee employee) {
    // try{
    Employee empResponse = employeeService.updateEmployee(employee);
    return new ResponseEntity(empResponse, HttpStatus.OK);
    // NO need to populate the exception till controller class.
    // From service  class itself, we can throw the exception.
    //        } catch(RuntimeException e){
    //            throw new EmployeeException(e);
    //        }
  }

  @GetMapping("/test-performance")
  public Page<Employee> performanceComparision(
      @RequestParam("pageSize") int pageSize, @RequestParam("pageNo") int pageNo) {

  // Without Pagination
    LocalTime startTimeInSecond = LocalTime.now();
    log.info("startTimeInSecond " + startTimeInSecond);
    Employee emp = employeeRepository .findByName();
    log.info("emp "+emp);
    LocalTime endTimeInSecond = LocalTime.now();
    log.info("endTimeInSecond " + endTimeInSecond);
  // with pagination
    Pageable pageable = PageRequest.of(pageNo, pageSize);

    LocalTime startTimeInSecond1 = LocalTime.now();
    log.info("startTimeInSecond with paging" + startTimeInSecond1);
    Page<Employee> emp1 = employeeRepository.findByNameUsingPage(pageable);
    log.info("emp "+emp);
    LocalTime endTimeInSecond1 = LocalTime.now();
    log.info("endTimeInSecond with paging" + endTimeInSecond1);

    return (Page<Employee>) emp1.getContent();

  }

}
