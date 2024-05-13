package com.springboot.crud.features.SpringBootCRUD.controller.student;

import com.springboot.crud.features.SpringBootCRUD.model.student.Student;
import com.springboot.crud.features.SpringBootCRUD.repository.student.StudentRepository;
import lombok.AllArgsConstructor;
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
