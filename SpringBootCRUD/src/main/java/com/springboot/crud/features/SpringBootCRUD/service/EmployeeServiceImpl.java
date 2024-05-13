package com.springboot.crud.features.SpringBootCRUD.service;

import com.springboot.crud.features.SpringBootCRUD.exception.EmployeeException;
import com.springboot.crud.features.SpringBootCRUD.hazelcast.HazelcastUtil;
import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import com.springboot.crud.features.SpringBootCRUD.repository.employee.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final HazelcastUtil hazelcastUtil;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository, HazelcastUtil hazelcastUtil) {
    this.employeeRepository = employeeRepository;
    this.hazelcastUtil = hazelcastUtil;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    Employee savedEmployee = employeeRepository.save(employee);

    List<Employee> employeeList = new ArrayList<>();
    for (int i = 1; i < 100000; i++) {
      Employee emp = new Employee();
      emp.setEmpSalary(employee.getEmpSalary() * i);
      emp.setEmpStatus(employee.getEmpStatus());
      emp.setEmpDob(employee.getEmpDob());
      emp.setEmpName(employee.getEmpName() + i);
      employeeList.add(emp);
    }
    employeeRepository.saveAll(employeeList);
    if (savedEmployee == null) {
      throw new EmployeeException(
          "Unable to insert data in the table", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    return savedEmployee;
  }

  @Override
  public Employee updateEmployee(Employee employee) {
    Employee emp = employeeRepository.findById(employee.getEmpId()).orElse(null);

    if (emp == null) {
      throw new EmployeeException(
          "Employee doesn't exit in the table", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    emp.setEmpDob(employee.getEmpDob());
    emp.setEmpName(employee.getEmpName());
    emp.setEmpSalary(employee.getEmpSalary());
    emp.setEmpStatus(employee.getEmpStatus());
    Employee savedEmployee = employeeRepository.save(emp);

    if (emp == null) {
      throw new EmployeeException(
          "Unable to update Employee Details", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    return emp;
  }

  @Override
  public Page<Employee> getEmployeeUsingPage(Pageable pageable) {
    Page<Employee> employeeList = employeeRepository.findAll(pageable);
    return employeeList;
  }

  @Override
  public List<Employee> getEmployee() {

    List<Employee> employeeListFromCache = hazelcastUtil.getEmployeeDataFromCache("key");
    if (employeeListFromCache == null || employeeListFromCache.size() == 0) {
      log.info("Session Data doesn't exist");
      List<Employee> employeeList = employeeRepository.findAll();
      hazelcastUtil.storeEmployeeDataInCache("key", employeeList);
      employeeListFromCache = hazelcastUtil.getEmployeeDataFromCache("key");
    }
    log.info("Session Data exist");
    return employeeListFromCache;
  }
}
