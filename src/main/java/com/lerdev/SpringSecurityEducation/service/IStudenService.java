package com.lerdev.SpringSecurityEducation.service;



import com.lerdev.SpringSecurityEducation.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudenService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student student);
    void deleteById(Long id);
     Student update(Long id, Student student);
}
