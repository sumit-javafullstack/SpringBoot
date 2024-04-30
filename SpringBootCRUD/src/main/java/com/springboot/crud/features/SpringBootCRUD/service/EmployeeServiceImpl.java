package com.springboot.crud.features.SpringBootCRUD.service;


import com.springboot.crud.features.SpringBootCRUD.hazelcast.HazelcastUtil;
import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import com.springboot.crud.features.SpringBootCRUD.repository.employee.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final HazelcastUtil hazelcastUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,HazelcastUtil hazelcastUtil) {
        this.employeeRepository = employeeRepository;
        this.hazelcastUtil = hazelcastUtil;
    }

    public Employee saveEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getEmployee() {

        List<Employee> employeeListFromCache = hazelcastUtil.getEmployeeDataFromCache("key");
        if(employeeListFromCache == null || employeeListFromCache.size() ==0 ){
            log.info("Session Data doesn't exist");
            List<Employee> employeeList = employeeRepository.findAll();
            hazelcastUtil.storeEmployeeDataInCache("key",employeeList);
            employeeListFromCache= hazelcastUtil.getEmployeeDataFromCache("key");
        }
        log.info("Session Data exist");
        return employeeListFromCache;
    }

}
