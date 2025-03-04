package com.lerdev.SpringSecurityEducation.repository;


import com.lerdev.SpringSecurityEducation.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
