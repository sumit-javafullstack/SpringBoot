package com.springboot.crud.features.SpringBootCRUD.repository.employee;


import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
