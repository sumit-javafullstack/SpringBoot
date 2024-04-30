package com.springboot.crud.features.SpringBootCRUD.repository.student;

import com.springboot.crud.features.SpringBootCRUD.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
