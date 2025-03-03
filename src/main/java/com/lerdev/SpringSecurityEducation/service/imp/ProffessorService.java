package com.lerdev.SpringSecurityEducation.service.imp;


import com.lerdev.SpringSecurityEducation.model.Professor;
import com.lerdev.SpringSecurityEducation.repository.IProfessorRepository;
import com.lerdev.SpringSecurityEducation.service.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProffessorService implements IProfessorService {

    @Autowired
    private IProfessorRepository professorRepository;
    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> findById(Long id) {

        Optional<Professor> professor = professorRepository.findById(id);
        return professor;
    }

    @Override
    public Professor save(Professor professor) {
        Professor professorSaved=  professorRepository.save(professor);
        return professorSaved;
    }

    @Override
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Professor update(Long id, Professor professor) {
        Professor professorFound = this.findById(id).get();
        professorFound.setName(professor.getName());
        professorFound.setCourseList(professor.getCourseList());
        professorFound.setDni(professor.getDni());
        professorFound.setLastName(professor.getLastName());

        Professor professorUpdate= professorRepository.save(professorFound);
        return professorUpdate;
    }
}
