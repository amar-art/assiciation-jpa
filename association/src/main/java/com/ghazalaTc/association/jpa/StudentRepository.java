package com.ghazalaTc.association.jpa;

import com.ghazalaTc.association.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

  public Student findByName(String name);
  public Student findByAge(int a);


}
