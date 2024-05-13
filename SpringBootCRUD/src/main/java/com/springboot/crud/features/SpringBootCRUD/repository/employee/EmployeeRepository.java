package com.springboot.crud.features.SpringBootCRUD.repository.employee;


import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select * from Employee where empName = 'Arun Mandal80850'",nativeQuery = true)
    public Employee findByName();

    @Query(value = "select * from Employee where empName = 'Arun Mandal80850'",nativeQuery = true)
    public Page<Employee> findByNameUsingPage(Pageable pageable);

}
