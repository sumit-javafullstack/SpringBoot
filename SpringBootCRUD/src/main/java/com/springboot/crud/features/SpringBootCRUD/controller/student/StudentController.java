package com.springboot.crud.features.SpringBootCRUD.controller.student;

import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import com.springboot.crud.features.SpringBootCRUD.model.student.Student;
import com.springboot.crud.features.SpringBootCRUD.repository.student.StudentRepository;
import com.springboot.crud.features.SpringBootCRUD.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StudentController {
  private final StudentRepository studentRepository;

  @PostMapping("/save-student")
  public Student saveStudent(@RequestBody Student student) {
    return studentRepository.save(student);
  }
}
