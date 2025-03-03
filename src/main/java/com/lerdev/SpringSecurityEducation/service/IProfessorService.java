package com.lerdev.SpringSecurityEducation.service;


import com.lerdev.SpringSecurityEducation.model.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorService {

    List<Professor> findAll();
    Optional<Professor> findById(Long id);
    Professor save(Professor professor);
    void deleteById(Long id);
    Professor update(Long id, Professor  professor);
}
