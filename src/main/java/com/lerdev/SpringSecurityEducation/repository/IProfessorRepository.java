package com.lerdev.SpringSecurityEducation.repository;


import com.lerdev.SpringSecurityEducation.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository  extends JpaRepository<Professor, Long> {
}
